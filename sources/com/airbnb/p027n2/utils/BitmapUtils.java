package com.airbnb.p027n2.utils;

import android.content.Context;
import android.graphics.Bitmap;
import com.airbnb.p027n2.primitives.imaging.CacheOnlyBitmapTarget;

/* renamed from: com.airbnb.n2.utils.BitmapUtils */
public final class BitmapUtils {
    public static Bitmap getBitmapFromCacheOnly(Context context, String url) {
        return new CacheOnlyBitmapTarget().getBitmapFromCache(context, url);
    }
}
