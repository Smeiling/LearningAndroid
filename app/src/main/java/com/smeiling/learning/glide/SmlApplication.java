package com.smeiling.learning.glide;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.smeiling.learning.uncaughtexception.UncaughtCrashHandler;

/**
 * @Author: Smeiling
 * @Date: 2019-01-07 20-12
 * @Description:
 */
public class SmlApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ARouter.openLog();     // Print log
        ARouter.openDebug();   // Turn on debugging mode (If you are running in InstantRun mode, you must turn on debug mode! Online version needs to be closed, otherwise there is a security risk)
        ARouter.init(this); // As early as possible, it is recommended to initialize in the Application


        UncaughtCrashHandler.getInstance().init();
    }
}
