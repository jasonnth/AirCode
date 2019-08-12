package com.facebook.imagepipeline.producers;

import android.content.ContentResolver;
import android.database.Cursor;
import android.media.ExifInterface;
import android.net.Uri;
import android.util.Pair;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.UriUtil;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.imagepipeline.memory.PooledByteBufferFactory;
import com.facebook.imagepipeline.memory.PooledByteBufferInputStream;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imageutils.BitmapUtil;
import com.facebook.imageutils.JfifUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.Executor;

public class LocalExifThumbnailProducer implements ThumbnailProducer<EncodedImage> {
    private static final int COMMON_EXIF_THUMBNAIL_MAX_DIMENSION = 512;
    @VisibleForTesting
    static final String CREATED_THUMBNAIL = "createdThumbnail";
    @VisibleForTesting
    static final String PRODUCER_NAME = "LocalExifThumbnailProducer";
    private final ContentResolver mContentResolver;
    private final Executor mExecutor;
    /* access modifiers changed from: private */
    public final PooledByteBufferFactory mPooledByteBufferFactory;

    public LocalExifThumbnailProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory, ContentResolver contentResolver) {
        this.mExecutor = executor;
        this.mPooledByteBufferFactory = pooledByteBufferFactory;
        this.mContentResolver = contentResolver;
    }

    public boolean canProvideImageForSize(ResizeOptions resizeOptions) {
        return ThumbnailSizeChecker.isImageBigEnough(512, 512, resizeOptions);
    }

    public void produceResults(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        ProducerListener listener = producerContext.getListener();
        String requestId = producerContext.getId();
        final ImageRequest imageRequest = producerContext.getImageRequest();
        final StatefulProducerRunnable cancellableProducerRunnable = new StatefulProducerRunnable<EncodedImage>(consumer, listener, PRODUCER_NAME, requestId) {
            /* access modifiers changed from: protected */
            public EncodedImage getResult() throws Exception {
                ExifInterface exifInterface = LocalExifThumbnailProducer.this.getExifInterface(imageRequest.getSourceUri());
                if (exifInterface == null || !exifInterface.hasThumbnail()) {
                    return null;
                }
                return LocalExifThumbnailProducer.this.buildEncodedImage(LocalExifThumbnailProducer.this.mPooledByteBufferFactory.newByteBuffer(exifInterface.getThumbnail()), exifInterface);
            }

            /* access modifiers changed from: protected */
            public void disposeResult(EncodedImage result) {
                EncodedImage.closeSafely(result);
            }

            /* access modifiers changed from: protected */
            public Map<String, String> getExtraMapOnSuccess(EncodedImage result) {
                return ImmutableMap.m1780of(LocalExifThumbnailProducer.CREATED_THUMBNAIL, Boolean.toString(result != null));
            }
        };
        producerContext.addCallbacks(new BaseProducerContextCallbacks() {
            public void onCancellationRequested() {
                cancellableProducerRunnable.cancel();
            }
        });
        this.mExecutor.execute(cancellableProducerRunnable);
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public ExifInterface getExifInterface(Uri uri) throws IOException {
        String realPath = getRealPathFromUri(uri);
        if (canReadAsFile(realPath)) {
            return new ExifInterface(realPath);
        }
        return null;
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: private */
    public EncodedImage buildEncodedImage(PooledByteBuffer imageBytes, ExifInterface exifInterface) {
        int width;
        int height = -1;
        Pair<Integer, Integer> dimensions = BitmapUtil.decodeDimensions((InputStream) new PooledByteBufferInputStream(imageBytes));
        int rotationAngle = getRotationAngle(exifInterface);
        if (dimensions != null) {
            width = ((Integer) dimensions.first).intValue();
        } else {
            width = -1;
        }
        if (dimensions != null) {
            height = ((Integer) dimensions.second).intValue();
        }
        CloseableReference<PooledByteBuffer> closeableByteBuffer = CloseableReference.m1871of(imageBytes);
        try {
            EncodedImage encodedImage = new EncodedImage(closeableByteBuffer);
            CloseableReference.closeSafely(closeableByteBuffer);
            encodedImage.setImageFormat(ImageFormat.JPEG);
            encodedImage.setRotationAngle(rotationAngle);
            encodedImage.setWidth(width);
            encodedImage.setHeight(height);
            return encodedImage;
        } catch (Throwable th) {
            CloseableReference.closeSafely(closeableByteBuffer);
            throw th;
        }
    }

    private int getRotationAngle(ExifInterface exifInterface) {
        return JfifUtil.getAutoRotateAngleFromOrientation(Integer.parseInt(exifInterface.getAttribute("Orientation")));
    }

    /* JADX INFO: finally extract failed */
    private String getRealPathFromUri(Uri srcUri) {
        String result = null;
        if (UriUtil.isLocalContentUri(srcUri)) {
            Cursor cursor = null;
            try {
                Cursor cursor2 = this.mContentResolver.query(srcUri, null, null, null, null);
                if (cursor2 != null) {
                    cursor2.moveToFirst();
                    result = cursor2.getString(cursor2.getColumnIndex("_data"));
                }
                if (cursor2 == null) {
                    return result;
                }
                cursor2.close();
                return result;
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } else if (UriUtil.isLocalFileUri(srcUri)) {
            return srcUri.getPath();
        } else {
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public boolean canReadAsFile(String realPath) throws IOException {
        if (realPath == null) {
            return false;
        }
        File file = new File(realPath);
        if (!file.exists() || !file.canRead()) {
            return false;
        }
        return true;
    }
}
