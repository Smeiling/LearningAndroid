package com.smeiling.learning.lifecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.smeiling.learning.R;

@Route(path = "/app/defaultlifecycle")
public class DefaultLifecycleActivity extends AppCompatActivity {

    private IDefaultPresenter mDefaultPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_lifecycle);

        mDefaultPresenter = new IDefaultPresenter.DefaultPresenter();

        getLifecycle().addObserver(mDefaultPresenter);
    }
}
