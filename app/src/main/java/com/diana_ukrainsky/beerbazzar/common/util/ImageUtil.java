package com.diana_ukrainsky.beerbazzar.common.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageUtil {
    public static void setImage(
            Context context,
            String imageUrl,
            ImageView imageView,
            int width,
            int height) {
        Glide.with(context)
                .load(imageUrl)
                .override(width, height)
                .into(imageView);
    }
}