package com.together.todayhelp.util;

import com.together.todayhelp.Constants;

import android.util.Log;


public final class LogUtil {

    private LogUtil () {
    }

    public static void d(String tag, String message) {
        if (StringUtil.isEmpty(message)) {
            return;
        }
        if (Constants.IS_DEBUG) {
            Log.d(tag, message);
        }
    }

    public static void d(String tag, String messageTitle, Object object) {
        if (Constants.IS_DEBUG) {
            Log.d(tag, messageTitle);
        }
    }

    public static void e(String tag, String message) {
        if (StringUtil.isEmpty(message)) {
            return;
        }
        if (Constants.IS_DEBUG) {
            Log.e(tag, message);
        }
    }

    public static void e(String tag, String messageTitle, Object object) {
        if (Constants.IS_DEBUG) {
            Log.e(tag, messageTitle);
        }
    }

    public static void i(String tag, String message) {
        if (Constants.IS_DEBUG) {
            Log.i(tag, message);
        }
    }

    public static void i(String tag, String messageTitle, Object object) {
        if (Constants.IS_DEBUG) {
            Log.i(tag, messageTitle);
        }
    }

    public static void v(String tag, String message) {
        if (Constants.IS_DEBUG) {
            Log.v(tag, message);
        }
    }

    public static void v(String tag, String messageTitle, Object object) {
        if (Constants.IS_DEBUG) {
            Log.v(tag, messageTitle);
        }
    }

    public static void w(String tag, String message) {
        if (StringUtil.isEmpty(message)) {
            return;
        }
        if (Constants.IS_DEBUG) {
            Log.w(tag, message);
        }
    }

    public static void w(String tag, String messageTitle, Object object) {
        if (Constants.IS_DEBUG) {
            Log.w(tag, messageTitle);
        }
    }

}
