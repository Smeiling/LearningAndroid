package com.smeiling.learning.lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

import com.smeiling.learning.Logg;

/**
 * @Author: Smeiling
 * @Date: 2019-02-20 10-56
 * @Description:
 */
public interface IPresenter extends LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate(LifecycleOwner owner);


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy(LifecycleOwner owner);

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    void onLifecycleChanged(LifecycleOwner owner,
                            Lifecycle.Event event);

    class BasePresenter implements IPresenter {

        @Override
        public void onCreate(LifecycleOwner owner) {
            Logg.debug("LifeCycle onCreate");
        }

        @Override
        public void onDestroy(LifecycleOwner owner) {
            Logg.debug("LifeCycle onDestroy");
        }

        @Override
        public void onLifecycleChanged(LifecycleOwner owner, Lifecycle.Event event) {
            Logg.debug("LifeCycle onLifeCycleChanged event = " + event);
        }
    }
}
