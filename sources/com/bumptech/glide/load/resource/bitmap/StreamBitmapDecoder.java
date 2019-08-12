package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import java.io.InputStream;

public class StreamBitmapDecoder implements ResourceDecoder<InputStream, Bitmap> {
    private BitmapPool bitmapPool;
    private DecodeFormat decodeFormat;
    private final Downsampler downsampler;

    /* renamed from: id */
    private String f2936id;

    public StreamBitmapDecoder(BitmapPool bitmapPool2, DecodeFormat decodeFormat2) {
        this(Downsampler.AT_MOST, bitmapPool2, decodeFormat2);
    }

    public StreamBitmapDecoder(Downsampler downsampler2, BitmapPool bitmapPool2, DecodeFormat decodeFormat2) {
        this.downsampler = downsampler2;
        this.bitmapPool = bitmapPool2;
        this.decodeFormat = decodeFormat2;
    }

    public Resource<Bitmap> decode(InputStream source, int width, int height) {
        Bitmap bitmap = null;
        try {
            bitmap = this.downsampler.decode(source, this.bitmapPool, width, height, this.decodeFormat);
        } catch (OutOfMemoryError e) {
            Glide.get(null).trimMemory(40);
        }
        return BitmapResource.obtain(bitmap, this.bitmapPool);
    }

    public String getId() {
        if (this.f2936id == null) {
            this.f2936id = "StreamBitmapDecoder.com.bumptech.glide.load.resource.bitmap" + this.downsampler.getId() + this.decodeFormat.name();
        }
        return this.f2936id;
    }
}
