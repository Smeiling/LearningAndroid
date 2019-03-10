package com.smeiling.learning.uncaughtexception;

import com.smeiling.learning.Logg;

/**
 * @Author: Smeiling
 * @Date: 2019-03-10 15-05
 * @Description: 未捕获异常捕获
 */
public class UncaughtCrashHandler implements Thread.UncaughtExceptionHandler {

    private static volatile UncaughtCrashHandler instance;

    private UncaughtCrashHandler() {

    }

    public static UncaughtCrashHandler getInstance() {
        if (instance == null) {
            synchronized (UncaughtCrashHandler.class) {
                if (instance == null) {
                    instance = new UncaughtCrashHandler();
                }
            }
        }
        return instance;
    }


    public void init() {
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Logg.error("Exception thread = " + t.getName() + " Exception = " + e.getMessage());
    }
}
