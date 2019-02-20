package com.smeiling.learning.livedata;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.smeiling.learning.Logg;
import com.smeiling.learning.R;

@Route(path = "/app/livedata")
public class LiveDataActivity extends AppCompatActivity {

    private UpdateStrVM updateStrVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data);

        updateStrVM = ViewModelProviders.of(this).get(UpdateStrVM.class);

        updateStrVM.getCurrentStr().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Logg.debug("updateStrVM.currentStr onChanged = " + s);
            }
        });

        findViewById(R.id.btn_click).setOnClickListener(v -> {
            updateStrVM.getCurrentStr().setValue("Current time = " + System.currentTimeMillis());
        });

        findViewById(R.id.btn_start_dup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LiveDataActivity.this, DupLiveDataActivity.class));
            }
        });
    }
}
