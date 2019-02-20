package com.smeiling.learning;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.smeiling.learning.eventbus.BaseBusEvent;
import com.smeiling.learning.eventbus.BusEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class LauncherActivity extends AppCompatActivity {

    private Button btnRedirect;
    private EditText etRouterPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        btnRedirect = findViewById(R.id.btn_redirect);
        etRouterPath = findViewById(R.id.et_router_path);

        btnRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etRouterPath.getText().toString())) {
                    Toast.makeText(LauncherActivity.this, "请输入跳转URL", Toast.LENGTH_SHORT).show();
                } else {
                    ARouter.getInstance()
                            .build("/app/" + etRouterPath.getText().toString())
                            .navigation(getBaseContext(), new NavigationCallback() {
                                @Override
                                public void onFound(Postcard postcard) {

                                }

                                @Override
                                public void onLost(Postcard postcard) {
                                    Toast.makeText(getBaseContext(), "跳转URL错误", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onArrival(Postcard postcard) {

                                }

                                @Override
                                public void onInterrupt(Postcard postcard) {

                                }
                            });
                }
            }
        });

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onBaseEvent(BaseBusEvent event) {
        Toast.makeText(this, "base event received", Toast.LENGTH_SHORT).show();
    }

    @Subscribe
    public void onEvent(BusEvent event){
        Toast.makeText(this, "event received", Toast.LENGTH_SHORT).show();
    }
}
