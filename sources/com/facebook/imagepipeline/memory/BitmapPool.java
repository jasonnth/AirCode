package com.facebook.imagepipeline.memory;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.memory.MemoryTrimmableRegistry;

@TargetApi(21)
public class BitmapPool extends BasePool<Bitmap> {
    public BitmapPool(MemoryTrimmableRegistry memoryTrimmableRegistry, PoolParams poolParams, PoolStatsTracker poolStatsTracker) {
        super(memoryTrimmableRegistry, poolParams, poolStatsTracker);
        initialize();
    }

    /* access modifiers changed from: protected */
    public Bitmap alloc(int size) {
        return Bitmap.createBitmap(1, (int) Math.ceil(((double) size) / 2.0d), Config.RGB_565);
    }

    /* access modifiers changed from: protected */
    public void free(Bitmap value) {
        Preconditions.checkNotNull(value);
        value.recycle();
    }

    /* access modifiers changed from: protected */
    public int getBucketedSize(int requestSize) {
        return requestSize;
    }

    /* access modifiers changed from: protected */
    public int getBucketedSizeForValue(Bitmap value) {
        Preconditions.checkNotNull(value);
        return value.getAllocationByteCount();
    }

    /* access modifiers changed from: protected */
    public int getSizeInBytes(int bucketedSize) {
        return bucketedSize;
    }

    /* access modifiers changed from: protected */
    public boolean isReusable(Bitmap value) {
        Preconditions.checkNotNull(value);
        return !value.isRecycled() && value.isMutable();
    }
}
