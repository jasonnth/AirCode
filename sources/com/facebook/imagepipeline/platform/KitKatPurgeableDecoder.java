package com.facebook.imagepipeline.platform;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.FlexByteArrayPool;
import com.facebook.imagepipeline.memory.PooledByteBuffer;

@TargetApi(19)
public class KitKatPurgeableDecoder extends DalvikPurgeableDecoder {
    private final FlexByteArrayPool mFlexByteArrayPool;

    public /* bridge */ /* synthetic */ CloseableReference decodeFromEncodedImage(EncodedImage encodedImage, Config config) {
        return super.decodeFromEncodedImage(encodedImage, config);
    }

    public /* bridge */ /* synthetic */ CloseableReference decodeJPEGFromEncodedImage(EncodedImage encodedImage, Config config, int i) {
        return super.decodeJPEGFromEncodedImage(encodedImage, config, i);
    }

    public /* bridge */ /* synthetic */ CloseableReference pinBitmap(Bitmap bitmap) {
        return super.pinBitmap(bitmap);
    }

    public KitKatPurgeableDecoder(FlexByteArrayPool flexByteArrayPool) {
        this.mFlexByteArrayPool = flexByteArrayPool;
    }

    /* access modifiers changed from: protected */
    public Bitmap decodeByteArrayAsPurgeable(CloseableReference<PooledByteBuffer> bytesRef, Options options) {
        PooledByteBuffer pooledByteBuffer = (PooledByteBuffer) bytesRef.get();
        int length = pooledByteBuffer.size();
        CloseableReference<byte[]> encodedBytesArrayRef = this.mFlexByteArrayPool.get(length);
        try {
            byte[] encodedBytesArray = (byte[]) encodedBytesArrayRef.get();
            pooledByteBuffer.read(0, encodedBytesArray, 0, length);
            return (Bitmap) Preconditions.checkNotNull(BitmapFactory.decodeByteArray(encodedBytesArray, 0, length, options), "BitmapFactory returned null");
        } finally {
            CloseableReference.closeSafely(encodedBytesArrayRef);
        }
    }

    /* access modifiers changed from: protected */
    public Bitmap decodeJPEGByteArrayAsPurgeable(CloseableReference<PooledByteBuffer> bytesRef, int length, Options options) {
        boolean z = false;
        byte[] suffix = endsWithEOI(bytesRef, length) ? null : EOI;
        PooledByteBuffer pooledByteBuffer = (PooledByteBuffer) bytesRef.get();
        if (length <= pooledByteBuffer.size()) {
            z = true;
        }
        Preconditions.checkArgument(z);
        CloseableReference<byte[]> encodedBytesArrayRef = this.mFlexByteArrayPool.get(length + 2);
        try {
            byte[] encodedBytesArray = (byte[]) encodedBytesArrayRef.get();
            pooledByteBuffer.read(0, encodedBytesArray, 0, length);
            if (suffix != null) {
                putEOI(encodedBytesArray, length);
                length += 2;
            }
            return (Bitmap) Preconditions.checkNotNull(BitmapFactory.decodeByteArray(encodedBytesArray, 0, length, options), "BitmapFactory returned null");
        } finally {
            CloseableReference.closeSafely(encodedBytesArrayRef);
        }
    }

    private static void putEOI(byte[] imageBytes, int offset) {
        imageBytes[offset] = -1;
        imageBytes[offset + 1] = -39;
    }
}
