package com.smeiling.learning.lifecycle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.smeiling.learning.Logg;
import com.smeiling.learning.R;

@Route(path = "/app/lifecycle")
public class ObservableActivity extends AppCompatActivity {

    private IPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observable);
        Logg.debug("onCreate " + this.getClass().toString());
        mPresenter = new IPresenter.BasePresenter();

        getLifecycle().addObserver(mPresenter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logg.debug("onDestroy " + this.getClass().toString());
    }
}
