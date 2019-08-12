package com.airbnb.p027n2.primitives.imaging;

import android.content.Context;
import android.graphics.Bitmap;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

/* renamed from: com.airbnb.n2.primitives.imaging.CacheOnlyBitmapTarget */
public class CacheOnlyBitmapTarget extends SimpleTarget<Bitmap> {
    private Bitmap bitmap;

    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation, boolean loadedFromMemoryCache) {
        this.bitmap = resource;
    }

    public Bitmap getBitmapFromCache(Context context, String url) {
        Glide.with(context).load(url).asBitmap().into(this);
        if (this.bitmap == null) {
            getRequest().clear();
        }
        return this.bitmap;
    }
}
