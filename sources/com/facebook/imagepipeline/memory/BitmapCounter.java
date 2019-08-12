package com.facebook.imagepipeline.memory;

import android.graphics.Bitmap;
import android.os.Build.VERSION;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Throwables;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.references.ResourceReleaser;
import com.facebook.imagepipeline.common.TooManyBitmapsException;
import com.facebook.imagepipeline.nativecode.Bitmaps;
import com.facebook.imageutils.BitmapUtil;
import java.util.ArrayList;
import java.util.List;

public class BitmapCounter {
    private int mCount;
    private final int mMaxCount;
    private final int mMaxSize;
    private long mSize;
    private final ResourceReleaser<Bitmap> mUnpooledBitmapsReleaser;

    public BitmapCounter(int maxCount, int maxSize) {
        boolean z = true;
        Preconditions.checkArgument(maxCount > 0);
        if (maxSize <= 0) {
            z = false;
        }
        Preconditions.checkArgument(z);
        this.mMaxCount = maxCount;
        this.mMaxSize = maxSize;
        this.mUnpooledBitmapsReleaser = new ResourceReleaser<Bitmap>() {
            public void release(Bitmap value) {
                try {
                    BitmapCounter.this.decrease(value);
                } finally {
                    value.recycle();
                }
            }
        };
    }

    public synchronized boolean increase(Bitmap bitmap) {
        boolean z;
        int bitmapSize = BitmapUtil.getSizeInBytes(bitmap);
        if (this.mCount >= this.mMaxCount || this.mSize + ((long) bitmapSize) > ((long) this.mMaxSize)) {
            z = false;
        } else {
            this.mCount++;
            this.mSize += (long) bitmapSize;
            z = true;
        }
        return z;
    }

    public synchronized void decrease(Bitmap bitmap) {
        boolean z = true;
        synchronized (this) {
            int bitmapSize = BitmapUtil.getSizeInBytes(bitmap);
            Preconditions.checkArgument(this.mCount > 0, "No bitmaps registered.");
            if (((long) bitmapSize) > this.mSize) {
                z = false;
            }
            Preconditions.checkArgument(z, "Bitmap size bigger than the total registered size: %d, %d", Integer.valueOf(bitmapSize), Long.valueOf(this.mSize));
            this.mSize -= (long) bitmapSize;
            this.mCount--;
        }
    }

    public synchronized int getCount() {
        return this.mCount;
    }

    public synchronized long getSize() {
        return this.mSize;
    }

    public ResourceReleaser<Bitmap> getReleaser() {
        return this.mUnpooledBitmapsReleaser;
    }

    public List<CloseableReference<Bitmap>> associateBitmapsWithBitmapCounter(List<Bitmap> bitmaps) {
        int countedBitmaps = 0;
        while (countedBitmaps < bitmaps.size()) {
            try {
                Bitmap bitmap = (Bitmap) bitmaps.get(countedBitmaps);
                if (VERSION.SDK_INT < 21) {
                    Bitmaps.pinBitmap(bitmap);
                }
                if (!increase(bitmap)) {
                    throw new TooManyBitmapsException();
                }
                countedBitmaps++;
            } catch (Exception exception) {
                if (bitmaps != null) {
                    for (Bitmap bitmap2 : bitmaps) {
                        int countedBitmaps2 = countedBitmaps - 1;
                        if (countedBitmaps > 0) {
                            decrease(bitmap2);
                        }
                        bitmap2.recycle();
                        countedBitmaps = countedBitmaps2;
                    }
                }
                throw Throwables.propagate(exception);
            }
        }
        List<CloseableReference<Bitmap>> ret = new ArrayList<>();
        for (Bitmap bitmap3 : bitmaps) {
            ret.add(CloseableReference.m1872of(bitmap3, this.mUnpooledBitmapsReleaser));
        }
        return ret;
    }
}
