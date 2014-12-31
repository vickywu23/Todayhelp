package com.together.todayhelp;

import android.app.Application;

public class TodayHelpApplication extends Application {

    public static TodayHelpApplication eContext;
    
    @Override
    public void onCreate() {
        super.onCreate();
        eContext = this;

        if (Constants.IS_DEBUG) {
            CrashHandler crashHandler = CrashHandler.getInstance();
            crashHandler.init(getApplicationContext());
        }
    }

    public static TodayHelpApplication getInstance() {
        return eContext;
    }
}
