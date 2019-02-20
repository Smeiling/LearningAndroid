package com.smeiling.learning.glide;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

/**
 * @Author: Smeiling
 * @Date: 2019-01-03 16-49
 * @Description: 在 Application 模块中，可创建一个添加有 @GlideModule 注解，继承自 AppGlideModule 的类。此类可生成出一个流式 API，内联了多种选项，和集成库中自定义的选项：
 */
//@GlideModule
//public class SmlAppGlideModule extends AppGlideModule {
//
//    @Override
//    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
//        super.applyOptions(context, builder);
//        builder.setDefaultRequestOptions(new RequestOptions().disallowHardwareConfig().circleCrop());
//    }
//}
