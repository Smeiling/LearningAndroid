package com.smeiling.learning.livedata;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.smeiling.learning.R;

@Route(path = "/app/fragment_livedata")
public class LiveDataFragmentActivity extends AppCompatActivity {

    private AccountVM mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data_fragment);
        final TextView mText = findViewById(R.id.textView);
        // 添加Fragment试图
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_1, new TopFragment()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_2, new BottomFragment()).commit();
        // 初始化ViewModel
        mModel = ViewModelProviders.of(this).get(AccountVM.class);
        findViewById(R.id.main_set_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 设置一条数据
                // mModel.setAccount("秦川小将", "182*****008", "http://blog.csdn.net/mjb00000");
                // post一条数据
                mModel.getAccount().postValue(new AccountBean("秦川小将", "182*****008", "http://blog.csdn.net/mjb00000"));
            }
        });
        mModel.getAccount().observe(this, new Observer<AccountBean>() {
            @Override
            public void onChanged(@Nullable AccountBean accountBean) {
                mText.setText(accountBean.toString());
            }
        });
    }
}
