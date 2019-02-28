package com.smeiling.learning.proxy;

import com.smeiling.learning.Logg;

/**
 * @Author: Smeiling
 * @Date: 2019-02-28 16-36
 * @Description:
 */
public class MonitorUtil {

    private static ThreadLocal<Long> t1 = new ThreadLocal<>();

    public static void start() {
        t1.set(System.currentTimeMillis());
    }

    public static void finish(String methodName) {
        long finishTime = System.currentTimeMillis();
        Logg.debug(methodName + " 方法耗时 " + String.valueOf(finishTime - t1.get()) + "ms");
    }

}
