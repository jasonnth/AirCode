package com.airbnb.p027n2.primitives.imaging;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader.TileMode;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/* renamed from: com.airbnb.n2.primitives.imaging.GlideCircleTransform */
public class GlideCircleTransform extends BitmapTransformation {
    private static final Paint paint = new Paint();
    private final int inset;

    public GlideCircleTransform(Context context) {
        this(context, 0);
    }

    public GlideCircleTransform(Context context, int inset2) {
        super(context);
        this.inset = inset2;
    }

    /* access modifiers changed from: protected */
    public Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return circleCrop(pool, toTransform);
    }

    private Bitmap circleCrop(BitmapPool pool, Bitmap source) {
        if (source == null) {
            return null;
        }
        int width = source.getWidth();
        int height = source.getHeight();
        int size = Math.min(width, height);
        Bitmap squared = Bitmap.createBitmap(source, (width - size) / 2, (height - size) / 2, size, size);
        Bitmap result = pool.get(size, size, Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(size, size, Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(result);
        paint.setShader(new BitmapShader(squared, TileMode.CLAMP, TileMode.CLAMP));
        paint.setAntiAlias(true);
        float r = (((float) size) / 2.0f) - ((float) this.inset);
        int drawPoint = Math.min(width / 2, height / 2);
        canvas.drawCircle((float) drawPoint, (float) drawPoint, r, paint);
        return result;
    }

    public String getId() {
        return getClass().getName();
    }
}
