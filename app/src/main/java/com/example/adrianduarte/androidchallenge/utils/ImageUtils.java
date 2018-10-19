package com.example.adrianduarte.androidchallenge.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.adrianduarte.androidchallenge.R;

public class ImageUtils {

    public static void loadGlideImage(Context context, String imageUrl, ImageView target) {
        RequestOptions requestOptions = new RequestOptions()
            .placeholder(R.drawable.ic_placeholder)
            .error(R.drawable.ic_error)
        ;
        Glide
            .with(context)
            .setDefaultRequestOptions(requestOptions)
            .load(imageUrl)
            .listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    return false;
                }
            })
            .into(target)
        ;
    }
}