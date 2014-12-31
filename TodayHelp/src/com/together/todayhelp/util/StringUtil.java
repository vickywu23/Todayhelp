package com.together.todayhelp.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public final class StringUtil {

    private StringUtil () {
    }

    public static String encodeUrl(String requestUrl, Map<String, Object> params) {
        StringBuilder url = new StringBuilder(requestUrl);
        if (url.indexOf("?") < 0)
            url.append('?');
        if (params != null) {
            for (String name : params.keySet()) {
                url.append('&');
                url.append(name);
                url.append('=');
                url.append(String.valueOf(params.get(name)));
            }
        }
        String urlStr = url.toString().replace(" ", "+");
        urlStr = urlStr.replace("?&", "?");
        return urlStr;
    }

    public static boolean isEmpty(String message) {
        if (message == null || "".equals(message.trim())) {
            return true;
        }
        return false;
    }

    public static String formatTime(long timeStamp) {
        long currentTime = System.currentTimeMillis();
        long time = currentTime - timeStamp ;
        if (time < 60 * 1000) {
            return "刚刚";
        } else if (time < 60 * 60 * 1000) {
            return time / (60 * 1000) + "分钟";
        } else if (time < 24 * 60 * 60 * 1000) {
            return time / (60 *60 * 1000) + "小时";
        } else {
           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
           return sdf.format(new Date(timeStamp));
        }
    }

    public static String parseTencentWeiboString(String str) {
        if (!StringUtil.isEmpty(str)) {
            return str.replace("\\/", "/").replace("&quot;", "\"").replace("&#39;", "'");
        }
        return null;
    }

    public static String formatTimeTwo(long timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd");
        return sdf.format(new Date(timeStamp));
    }

}
