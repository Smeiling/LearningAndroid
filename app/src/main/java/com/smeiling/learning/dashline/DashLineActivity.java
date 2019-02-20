package com.smeiling.learning.dashline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.smeiling.learning.R;

/**
 * @Author Smeiling
 */
@Route(path = "/app/dashline")
public class DashLineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_line);
    }
}
