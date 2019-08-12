package com.facebook.react.flat;

import android.graphics.Bitmap;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.datasource.DataSubscriber;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.image.CloseableBitmap;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.infer.annotation.Assertions;

final class PipelineRequestHelper implements DataSubscriber<CloseableReference<CloseableImage>> {
    private int mAttachCounter;
    private BitmapUpdateListener mBitmapUpdateListener;
    private DataSource<CloseableReference<CloseableImage>> mDataSource;
    private CloseableReference<CloseableImage> mImageRef;
    private final ImageRequest mImageRequest;

    PipelineRequestHelper(ImageRequest imageRequest) {
        this.mImageRequest = imageRequest;
    }

    /* access modifiers changed from: 0000 */
    public void attach(BitmapUpdateListener listener) {
        boolean z;
        boolean z2 = true;
        this.mBitmapUpdateListener = listener;
        this.mAttachCounter++;
        if (this.mAttachCounter != 1) {
            Bitmap bitmap = getBitmap();
            if (bitmap != null) {
                listener.onSecondaryAttach(bitmap);
                return;
            }
            return;
        }
        listener.onImageLoadEvent(4);
        if (this.mDataSource == null) {
            z = true;
        } else {
            z = false;
        }
        Assertions.assertCondition(z);
        if (this.mImageRef != null) {
            z2 = false;
        }
        Assertions.assertCondition(z2);
        this.mDataSource = ImagePipelineFactory.getInstance().getImagePipeline().fetchDecodedImage(this.mImageRequest, RCTImageView.getCallerContext());
        this.mDataSource.subscribe(this, UiThreadImmediateExecutorService.getInstance());
    }

    /* access modifiers changed from: 0000 */
    public void detach() {
        this.mAttachCounter--;
        if (this.mAttachCounter == 0) {
            if (this.mDataSource != null) {
                this.mDataSource.close();
                this.mDataSource = null;
            }
            if (this.mImageRef != null) {
                this.mImageRef.close();
                this.mImageRef = null;
            }
            this.mBitmapUpdateListener = null;
        }
    }

    /* access modifiers changed from: 0000 */
    public Bitmap getBitmap() {
        if (this.mImageRef == null) {
            return null;
        }
        CloseableImage closeableImage = (CloseableImage) this.mImageRef.get();
        if (closeableImage instanceof CloseableBitmap) {
            return ((CloseableBitmap) closeableImage).getUnderlyingBitmap();
        }
        this.mImageRef.close();
        this.mImageRef = null;
        return null;
    }

    /* access modifiers changed from: 0000 */
    public boolean isDetached() {
        return this.mAttachCounter == 0;
    }

    public void onNewResult(DataSource<CloseableReference<CloseableImage>> dataSource) {
        if (dataSource.isFinished()) {
            try {
                if (this.mDataSource == dataSource) {
                    this.mDataSource = null;
                    CloseableReference<CloseableImage> imageReference = (CloseableReference) dataSource.getResult();
                    if (imageReference == null) {
                        dataSource.close();
                    } else if (!(((CloseableImage) imageReference.get()) instanceof CloseableBitmap)) {
                        imageReference.close();
                        dataSource.close();
                    } else {
                        this.mImageRef = imageReference;
                        Bitmap bitmap = getBitmap();
                        if (bitmap == null) {
                            dataSource.close();
                            return;
                        }
                        BitmapUpdateListener listener = (BitmapUpdateListener) Assertions.assumeNotNull(this.mBitmapUpdateListener);
                        listener.onBitmapReady(bitmap);
                        listener.onImageLoadEvent(2);
                        listener.onImageLoadEvent(3);
                        dataSource.close();
                    }
                }
            } finally {
                dataSource.close();
            }
        }
    }

    public void onFailure(DataSource<CloseableReference<CloseableImage>> dataSource) {
        if (this.mDataSource == dataSource) {
            ((BitmapUpdateListener) Assertions.assumeNotNull(this.mBitmapUpdateListener)).onImageLoadEvent(1);
            ((BitmapUpdateListener) Assertions.assumeNotNull(this.mBitmapUpdateListener)).onImageLoadEvent(3);
            this.mDataSource = null;
        }
        dataSource.close();
    }

    public void onCancellation(DataSource<CloseableReference<CloseableImage>> dataSource) {
        if (this.mDataSource == dataSource) {
            this.mDataSource = null;
        }
        dataSource.close();
    }

    public void onProgressUpdate(DataSource<CloseableReference<CloseableImage>> dataSource) {
    }
}
