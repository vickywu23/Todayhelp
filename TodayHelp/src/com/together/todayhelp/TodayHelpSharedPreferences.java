package com.together.todayhelp;

import com.together.todayhelp.util.SharedPreferencesUtil;

public class TodayHelpSharedPreferences {

    static final String LOGIN_USERNAME = "user_name";
    static final String IS_LOGIN = "is_login";
    static final String USER_CODE = "user_code";

    private TodayHelpSharedPreferences() {

    }

    public static void setUsername(String name) {
        SharedPreferencesUtil.setStringValue(LOGIN_USERNAME, name);
    }

    public static String getUsername() {
        return SharedPreferencesUtil.getStringValue(LOGIN_USERNAME);
    }

    public static void setIsLogin(boolean isLogin) {
        SharedPreferencesUtil.setBooleanValue(IS_LOGIN, isLogin);
    }

    public static boolean getIsLogin() {
        return SharedPreferencesUtil.getBooleanValue(IS_LOGIN);
    }

    public static void setUserCode(String code) {
        SharedPreferencesUtil.setStringValue(USER_CODE, code);
    }

    public static String getUserCode() {
        return SharedPreferencesUtil.getStringValue(USER_CODE);
    }

}
