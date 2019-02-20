package com.smeiling.learning.glide.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.smeiling.learning.R;

@Route(path = "/app/glide")
public class GlideTestActivity extends AppCompatActivity {

    private ImageView ivGlideImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivGlideImage = findViewById(R.id.iv_glide_image);
//        GlideApp.with(this)
//                .load("https://fs.esf.fangdd.com/fangc/FnHSpgJr4m1ajvocwkMv3DQtGQ0x.jpg")
//                .defaultImage(true)
//                .into(ivGlideImage);

        RequestManager glideRequestManager = Glide.with(this);
        RequestBuilder<Drawable> glideRequestBuilder = glideRequestManager.asDrawable();

        glideRequestBuilder = glideRequestBuilder.load("https://fs.esf.fangdd.com/fangc/FnHSpgJr4m1ajvocwkMv3DQtGQ0x.jpg");


        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.placeholder(R.mipmap.esf_house_noimage_holder_1);
        requestOptions = requestOptions.circleCrop();
        glideRequestBuilder = glideRequestBuilder.apply(requestOptions);


        glideRequestBuilder.into(ivGlideImage);


        Glide.with(this)
                .asBitmap()
                .load("https://fs.esf.fangdd.com/fangc/FnHSpgJr4m1ajvocwkMv3DQtGQ0x.jpg")
                .into(ivGlideImage);
    }
}