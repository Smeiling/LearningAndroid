package com.smeiling.learning;

import android.util.Log;

/**
 * @Author: Smeiling
 * @Date: 2019-01-23 10-54
 * @Description:
 */
public class Logg {

    private static final String TAG = "SMLSMLSML";

    public static void error(String logContent) {
        Log.e(TAG, logContent);
    }

    public static void debug(String logContent) {
        Log.d(TAG, logContent);
    }

}
