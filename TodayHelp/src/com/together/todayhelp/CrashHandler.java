package com.together.todayhelp;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import com.together.todayhelp.util.LogUtil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.widget.Toast;


public class CrashHandler implements UncaughtExceptionHandler {

    public static final String TAG = CrashHandler.class.getName();
    private static CrashHandler mccCrashHandler = new CrashHandler();
    private Context mContext;
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private Map<String, String> infos = new HashMap<String, String>();

    private static final String FILE_NAME = "filename";
    private static final String VERSION_NAME = "versionname";
    private static final String VERSION_CODE = "versioncode";
    private static final String STACK_TRACE = "stacktrace";

    private CrashHandler () {

    }

    public void init(Context context) {
        mContext = context;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public static CrashHandler getInstance() {
        return mccCrashHandler;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        ex.printStackTrace(printWriter);
        final String stacktrace = result.toString();
        printWriter.close();

        collectCrashDeviceInfo(mContext);
        saveCrashInfoToFile(ex);

        String timestamp = getCurrentTime();
        String version = getCurrentVersion();
        final String filename = timestamp + "_" + version + ".stacktrace";

        new Thread(new Runnable() {

            @Override
            public void run() {
                sendCrashReportsToServer(stacktrace, filename);
            }
        }).start();
        Toast.makeText(mContext, "很抱歉，程序出现异常，即将�?�?", Toast.LENGTH_LONG).show();
        mDefaultHandler.uncaughtException(thread, ex);
    }

    public void collectCrashDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                infos.put(VERSION_NAME, pi.versionName == null ? "not set" : pi.versionName);
                infos.put(VERSION_CODE, pi.versionCode + "");
            }
        } catch (NameNotFoundException e) {
            LogUtil.e(TAG, "Error while collect package info", e);
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
                LogUtil.d(TAG, field.getName() + " : " + field.get(null));

            } catch (Exception e) {
                LogUtil.e(TAG, "Error while collect crash info", e);
            }
        }
    }

    private void sendCrashReportsToServer(String stacktrace, String filename) {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://172.20.248.172/upload.php");

        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair(FILE_NAME, filename));
        nvps.add(new BasicNameValuePair(STACK_TRACE, stacktrace));
        nvps.add(new BasicNameValuePair(VERSION_CODE, getCurrentVersion()));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
            httpClient.execute(httpPost);
        } catch (IOException e) {
            LogUtil.e(TAG, e.getMessage());
        }
    }

    private void saveCrashInfoToFile(Throwable ex) {

        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + ": " + value + "\n");
        }

        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);
        LogUtil.e(TAG, "heapSize: " + android.os.Debug.getNativeHeapAllocatedSize());
        LogUtil.e(TAG, sb.toString());
    }

    @SuppressLint("SimpleDateFormat")
	private String getCurrentTime() {
        String currentTime = null;
        SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd_hh_mm_ss");
        currentTime = sfd.format(new java.util.Date());

        return currentTime;
    }

    private String getCurrentVersion() {
        String currentVersion = null;
        currentVersion = infos.get(VERSION_NAME);
        return currentVersion;
    }

}
