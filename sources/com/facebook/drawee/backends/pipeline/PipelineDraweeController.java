package com.facebook.drawee.backends.pipeline;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawable.base.DrawableWithCaches;
import com.facebook.drawee.components.DeferredReleaser;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.drawable.LightBitmapDrawable;
import com.facebook.drawee.drawable.OrientedDrawable;
import com.facebook.imagepipeline.animated.factory.AnimatedDrawableFactory;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.image.ImageInfo;
import java.util.concurrent.Executor;

public class PipelineDraweeController extends AbstractDraweeController<CloseableReference<CloseableImage>, ImageInfo> {
    private static final Class<?> TAG = PipelineDraweeController.class;
    private static boolean sIsLightEnabled;
    private static boolean sIsReuseEnabled;
    private final AnimatedDrawableFactory mAnimatedDrawableFactory;
    private Supplier<DataSource<CloseableReference<CloseableImage>>> mDataSourceSupplier;
    private LightBitmapDrawable mLightBitmapDrawable;
    private final Resources mResources;

    public PipelineDraweeController(Resources resources, DeferredReleaser deferredReleaser, AnimatedDrawableFactory animatedDrawableFactory, Executor uiThreadExecutor, Supplier<DataSource<CloseableReference<CloseableImage>>> dataSourceSupplier, String id, Object callerContext) {
        super(deferredReleaser, uiThreadExecutor, id, callerContext);
        this.mResources = resources;
        this.mAnimatedDrawableFactory = animatedDrawableFactory;
        init(dataSourceSupplier);
    }

    public void initialize(Supplier<DataSource<CloseableReference<CloseableImage>>> dataSourceSupplier, String id, Object callerContext) {
        super.initialize(id, callerContext);
        init(dataSourceSupplier);
    }

    private void init(Supplier<DataSource<CloseableReference<CloseableImage>>> dataSourceSupplier) {
        this.mDataSourceSupplier = dataSourceSupplier;
    }

    /* access modifiers changed from: protected */
    public Resources getResources() {
        return this.mResources;
    }

    /* access modifiers changed from: protected */
    public DataSource<CloseableReference<CloseableImage>> getDataSource() {
        if (FLog.isLoggable(2)) {
            FLog.m1828v(TAG, "controller %x: getDataSource", (Object) Integer.valueOf(System.identityHashCode(this)));
        }
        return (DataSource) this.mDataSourceSupplier.get();
    }

    /* access modifiers changed from: protected */
    public Drawable createDrawable(CloseableReference<CloseableImage> image) {
        Drawable bitmapDrawable;
        Preconditions.checkState(CloseableReference.isValid(image));
        CloseableImage closeableImage = (CloseableImage) image.get();
        if (closeableImage instanceof CloseableStaticBitmap) {
            CloseableStaticBitmap closeableStaticBitmap = (CloseableStaticBitmap) closeableImage;
            if (sIsLightEnabled) {
                if (!sIsReuseEnabled || this.mLightBitmapDrawable == null) {
                    this.mLightBitmapDrawable = new LightBitmapDrawable(this.mResources, closeableStaticBitmap.getUnderlyingBitmap());
                } else {
                    this.mLightBitmapDrawable.setBitmap(closeableStaticBitmap.getUnderlyingBitmap());
                }
                bitmapDrawable = this.mLightBitmapDrawable;
            } else {
                bitmapDrawable = new BitmapDrawable(this.mResources, closeableStaticBitmap.getUnderlyingBitmap());
            }
            if (closeableStaticBitmap.getRotationAngle() == 0 || closeableStaticBitmap.getRotationAngle() == -1) {
                return bitmapDrawable;
            }
            return new OrientedDrawable(bitmapDrawable, closeableStaticBitmap.getRotationAngle());
        } else if (this.mAnimatedDrawableFactory != null) {
            return this.mAnimatedDrawableFactory.create(closeableImage);
        } else {
            throw new UnsupportedOperationException("Unrecognized image class: " + closeableImage);
        }
    }

    /* access modifiers changed from: protected */
    public ImageInfo getImageInfo(CloseableReference<CloseableImage> image) {
        Preconditions.checkState(CloseableReference.isValid(image));
        return (ImageInfo) image.get();
    }

    /* access modifiers changed from: protected */
    public int getImageHash(CloseableReference<CloseableImage> image) {
        if (image != null) {
            return image.getValueHash();
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public void releaseImage(CloseableReference<CloseableImage> image) {
        CloseableReference.closeSafely(image);
    }

    /* access modifiers changed from: protected */
    public void releaseDrawable(Drawable drawable) {
        if (drawable instanceof DrawableWithCaches) {
            ((DrawableWithCaches) drawable).dropCaches();
        }
    }

    public String toString() {
        return Objects.toStringHelper((Object) this).add("super", (Object) super.toString()).add("dataSourceSupplier", (Object) this.mDataSourceSupplier).toString();
    }

    protected static void setLightBitmapDrawableExperiment(boolean lightEnabled, boolean reuseEnabled) {
        sIsLightEnabled = lightEnabled;
        sIsReuseEnabled = reuseEnabled;
    }
}
