package com.smeiling.learning.ipc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.smeiling.learning.R;

public class AIDLActivity extends AppCompatActivity {

    private IBookManager bookManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
    }
}
