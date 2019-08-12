package com.facebook.imagepipeline.producers;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Rect;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore.Images.Thumbnails;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.logging.FLog;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.PooledByteBufferFactory;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imageutils.JfifUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.Executor;
import p005cn.jpush.android.data.DbHelper;

public class LocalContentUriThumbnailFetchProducer extends LocalFetchProducer implements ThumbnailProducer<EncodedImage> {
    private static final Rect MICRO_THUMBNAIL_DIMENSIONS = new Rect(0, 0, 96, 96);
    private static final Rect MINI_THUMBNAIL_DIMENSIONS = new Rect(0, 0, 512, 384);
    private static final int NO_THUMBNAIL = 0;
    @VisibleForTesting
    static final String PRODUCER_NAME = "LocalContentUriThumbnailFetchProducer";
    private static final String[] PROJECTION = {DbHelper.TABLE_ID, "_data"};
    private static final Class<?> TAG = LocalContentUriThumbnailFetchProducer.class;
    private static final String[] THUMBNAIL_PROJECTION = {"_data"};
    private final ContentResolver mContentResolver;

    public LocalContentUriThumbnailFetchProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory, ContentResolver contentResolver, boolean decodeFileDescriptorEnabled) {
        super(executor, pooledByteBufferFactory, decodeFileDescriptorEnabled);
        this.mContentResolver = contentResolver;
    }

    public boolean canProvideImageForSize(ResizeOptions resizeOptions) {
        return ThumbnailSizeChecker.isImageBigEnough(MINI_THUMBNAIL_DIMENSIONS.width(), MINI_THUMBNAIL_DIMENSIONS.height(), resizeOptions);
    }

    /* access modifiers changed from: protected */
    public EncodedImage getEncodedImage(ImageRequest imageRequest) throws IOException {
        Uri uri = imageRequest.getSourceUri();
        if (UriUtil.isLocalCameraUri(uri)) {
            EncodedImage cameraImage = getCameraImage(uri, imageRequest.getResizeOptions());
            if (cameraImage != null) {
                return cameraImage;
            }
        }
        return null;
    }

    private EncodedImage getCameraImage(Uri uri, ResizeOptions resizeOptions) throws IOException {
        Cursor cursor = this.mContentResolver.query(uri, PROJECTION, null, null, null);
        if (cursor == null) {
            return null;
        }
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            String pathname = cursor.getString(cursor.getColumnIndex("_data"));
            if (resizeOptions != null) {
                EncodedImage thumbnail = getThumbnail(resizeOptions, cursor.getInt(cursor.getColumnIndex(DbHelper.TABLE_ID)));
                if (thumbnail != null) {
                    thumbnail.setRotationAngle(getRotationAngle(pathname));
                    cursor.close();
                    return thumbnail;
                }
            }
            cursor.close();
            return null;
        } finally {
            cursor.close();
        }
    }

    private EncodedImage getThumbnail(ResizeOptions resizeOptions, int imageId) throws IOException {
        EncodedImage encodedImage = null;
        int thumbnailKind = getThumbnailKind(resizeOptions);
        if (thumbnailKind != 0) {
            Cursor thumbnailCursor = null;
            try {
                Cursor thumbnailCursor2 = Thumbnails.queryMiniThumbnail(this.mContentResolver, (long) imageId, thumbnailKind, THUMBNAIL_PROJECTION);
                if (thumbnailCursor2 != null) {
                    thumbnailCursor2.moveToFirst();
                    if (thumbnailCursor2.getCount() > 0) {
                        String thumbnailUri = thumbnailCursor2.getString(thumbnailCursor2.getColumnIndex("_data"));
                        if (new File(thumbnailUri).exists()) {
                            encodedImage = getEncodedImage(new FileInputStream(thumbnailUri), getLength(thumbnailUri));
                            if (thumbnailCursor2 != null) {
                                thumbnailCursor2.close();
                            }
                        }
                    }
                    if (thumbnailCursor2 != null) {
                        thumbnailCursor2.close();
                    }
                } else if (thumbnailCursor2 != null) {
                    thumbnailCursor2.close();
                }
            } catch (Throwable th) {
                if (thumbnailCursor != null) {
                    thumbnailCursor.close();
                }
                throw th;
            }
        }
        return encodedImage;
    }

    private static int getThumbnailKind(ResizeOptions resizeOptions) {
        if (ThumbnailSizeChecker.isImageBigEnough(MICRO_THUMBNAIL_DIMENSIONS.width(), MICRO_THUMBNAIL_DIMENSIONS.height(), resizeOptions)) {
            return 3;
        }
        if (ThumbnailSizeChecker.isImageBigEnough(MINI_THUMBNAIL_DIMENSIONS.width(), MINI_THUMBNAIL_DIMENSIONS.height(), resizeOptions)) {
            return 1;
        }
        return 0;
    }

    private static int getLength(String pathname) {
        if (pathname == null) {
            return -1;
        }
        return (int) new File(pathname).length();
    }

    /* access modifiers changed from: protected */
    public String getProducerName() {
        return PRODUCER_NAME;
    }

    private static int getRotationAngle(String pathname) {
        int i = 0;
        if (pathname == null) {
            return i;
        }
        try {
            return JfifUtil.getAutoRotateAngleFromOrientation(new ExifInterface(pathname).getAttributeInt("Orientation", 1));
        } catch (IOException ioe) {
            FLog.m1806e(TAG, (Throwable) ioe, "Unable to retrieve thumbnail rotation for %s", pathname);
            return i;
        }
    }
}
