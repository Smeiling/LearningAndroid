package com.smeiling.learning.proxy;

import com.smeiling.learning.Logg;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: Smeiling
 * @Date: 2019-02-28 16-38
 * @Description:
 */
public class StuInvocationHandler<T> implements InvocationHandler {

    T target;

    public StuInvocationHandler(T target) {
        this.target = target;
    }

    /**
     * @param proxy  动态代理对象
     * @param method 正在执行的方法
     * @param args   调用目标方法时传入的实参
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Logg.debug("代理执行" + method.getName() + "方法");
        // 代理过程中插入监测方法，计算该方法耗时
        MonitorUtil.start();
        Object result = method.invoke(target, args);
        MonitorUtil.finish(method.getName());
        return result;
    }
}
