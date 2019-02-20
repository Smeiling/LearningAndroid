package com.smeiling.learning.butterknife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.smeiling.learning.R;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = "/app/butterknife")
public class ButterKnifeActivity extends AppCompatActivity {

    @BindView(R.id.tv_first)
    public TextView tvFirst;
    @BindView(R.id.tv_second)
    public TextView tvSecond;
    @BindView(R.id.et_first)
    public EditText etFirst;
    @BindView(R.id.et_second)
    public EditText etSecond;
    @BindViews({R.id.cb_first, R.id.cb_second, R.id.cb_third})
    public List<CheckBox> cbGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butter_knife);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.tv_first)
    public void onFirstClick(View view) {
        Toast.makeText(this, "First TextView Click", Toast.LENGTH_SHORT).show();
    }
}
