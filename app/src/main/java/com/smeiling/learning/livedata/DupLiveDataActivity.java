package com.smeiling.learning.livedata;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.smeiling.learning.Logg;
import com.smeiling.learning.R;

public class DupLiveDataActivity extends AppCompatActivity {

    private UpdateStrVM updateStrVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dup_live_data);

        updateStrVM = ViewModelProviders.of(this).get(UpdateStrVM.class);

        updateStrVM.getCurrentStr().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Logg.debug("DupLiveDataActivity onChanged = " + s);
            }
        });

        findViewById(R.id.btn_update).setOnClickListener(v -> {
            updateStrVM.getCurrentStr().setValue("updated str = " + "smlsmlsml");
        });
    }
}
