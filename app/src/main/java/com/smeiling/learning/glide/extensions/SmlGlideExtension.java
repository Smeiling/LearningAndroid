package com.smeiling.learning.glide.extensions;

import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.request.RequestOptions;
import com.smeiling.learning.R;

/**
 * @Author: Smeiling
 * @Date: 2019-01-03 16-56
 * @Description:
 */
@GlideExtension
public class SmlGlideExtension {
    // Size of mini thumb in pixels.
    private static final int MINI_THUMB_SIZE = 100;

    private SmlGlideExtension() {
    } // utility class

    @GlideOption
    public static void miniThumb(RequestOptions options) {
        options.fitCenter()
                .override(MINI_THUMB_SIZE);
    }

    @GlideOption
    public static void defaultImage(RequestOptions options, boolean isAvatar) {
        if (isAvatar) {
            options.placeholder(R.mipmap.icon_jingjirennan);
        } else {
            options.placeholder(R.mipmap.esf_house_noimage_holder_1);
        }
    }

}
