package com.smeiling.learning.retrofit.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.smeiling.learning.R;
import com.smeiling.learning.retrofit.service.GitHubService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Route(path = "/app/retrofit")
public class RetrofitActivity extends AppCompatActivity {

    @BindView(R.id.btn_redirect)
    public Button btnRedirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_redirect)
    public void onRedirectClick(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GitHubService service = retrofit.create(GitHubService.class);
        Call<List<Object>> repos = service.listRepos("octocat");
        repos.enqueue(new Callback<List<Object>>() {
            @Override
            public void onResponse(Call<List<Object>> call, Response<List<Object>> response) {
                Log.d(RetrofitActivity.class.getSimpleName(), response.toString() + " Thread = " + Thread.currentThread().getName());
            }

            @Override
            public void onFailure(Call<List<Object>> call, Throwable t) {
                Log.e(RetrofitActivity.class.getSimpleName(), t.getMessage());
            }
        });
//        Call<Object> time = service.getTime("http://bjtime.cn");
//        time.enqueue(new Callback<Object>() {
//            @Override
//            public void onResponse(Call<Object> call, Response<Object> response) {
//                Log.d(RetrofitActivity.class.getSimpleName(), "response = " + response.toString() + " Thread = " + Thread.currentThread().getName());
//            }
//
//            @Override
//            public void onFailure(Call<Object> call, Throwable t) {
//                Log.d(RetrofitActivity.class.getSimpleName(), "t = " + t.getMessage());
//            }
//        });

    }
}
