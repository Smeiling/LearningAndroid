package com.smeiling.learning.lifecycle;

import android.arch.lifecycle.DefaultLifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;

import com.smeiling.learning.Logg;

/**
 * @Author: Smeiling
 * @Date: 2019-02-20 16-08
 * @Description: 继承DefaultLifecycleObserver实现对被观察者生命周期的监听（不使用注解）
 */
public interface IDefaultPresenter extends DefaultLifecycleObserver {

    class DefaultPresenter implements IDefaultPresenter {
        @Override
        public void onCreate(@NonNull LifecycleOwner owner) {
            Logg.debug("Default Lifecycle Observer -> onCreate");
        }

        @Override
        public void onDestroy(@NonNull LifecycleOwner owner) {
            Logg.debug("Default Lifecycle Observer -> onDestroy");
        }
    }
}
