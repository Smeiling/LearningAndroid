package com.smeiling.learning.eventbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.smeiling.learning.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = "/app/eventbus")
public class EventBusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.base_event)
    public void onClickBaseEvent(View view) {
        EventBus.getDefault().post(new BaseBusEvent());
    }

    @OnClick(R.id.event)
    public void onClickEvent(View view) {
        EventBus.getDefault().post(new BusEvent());
    }
}
