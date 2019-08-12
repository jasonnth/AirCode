package com.airbnb.lottie;

import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import java.util.HashSet;
import java.util.Set;

public class LottieDrawable extends Drawable implements Callback {
    private static final String TAG = LottieDrawable.class.getSimpleName();
    private int alpha = 255;
    /* access modifiers changed from: private */
    public final ValueAnimator animator = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
    private final Set<ColorFilterData> colorFilterData = new HashSet();
    private LottieComposition composition;
    private CompositionLayer compositionLayer;
    private boolean enableMergePaths;
    private ImageAssetBitmapManager imageAssetBitmapManager;
    private ImageAssetDelegate imageAssetDelegate;
    private String imageAssetsFolder;
    private final Matrix matrix = new Matrix();
    private boolean playAnimationWhenCompositionAdded;
    private float progress = 0.0f;
    private boolean reverseAnimationWhenCompositionAdded;
    private float scale = 1.0f;
    private float speed = 1.0f;
    /* access modifiers changed from: private */
    public boolean systemAnimationsAreDisabled;

    private static class ColorFilterData {
        final ColorFilter colorFilter;
        final String contentName;
        final String layerName;

        ColorFilterData(String layerName2, String contentName2, ColorFilter colorFilter2) {
            this.layerName = layerName2;
            this.contentName = contentName2;
            this.colorFilter = colorFilter2;
        }

        public int hashCode() {
            int hashCode = 17;
            if (this.layerName != null) {
                hashCode = this.layerName.hashCode() * 527;
            }
            if (this.contentName != null) {
                return hashCode * 31 * this.contentName.hashCode();
            }
            return hashCode;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ColorFilterData)) {
                return false;
            }
            ColorFilterData other = (ColorFilterData) obj;
            if (hashCode() != other.hashCode()) {
                return false;
            }
            if (this.colorFilter != other.colorFilter) {
                return false;
            }
            return true;
        }
    }

    public LottieDrawable() {
        this.animator.setRepeatCount(0);
        this.animator.setInterpolator(new LinearInterpolator());
        this.animator.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                if (LottieDrawable.this.systemAnimationsAreDisabled) {
                    LottieDrawable.this.animator.cancel();
                    LottieDrawable.this.setProgress(1.0f);
                    return;
                }
                LottieDrawable.this.setProgress(((Float) animation.getAnimatedValue()).floatValue());
            }
        });
    }

    public boolean hasMasks() {
        return this.compositionLayer != null && this.compositionLayer.hasMasks();
    }

    public boolean hasMatte() {
        return this.compositionLayer != null && this.compositionLayer.hasMatte();
    }

    /* access modifiers changed from: 0000 */
    public boolean enableMergePathsForKitKatAndAbove() {
        return this.enableMergePaths;
    }

    public void enableMergePathsForKitKatAndAbove(boolean enable) {
        if (VERSION.SDK_INT < 19) {
            Log.w(TAG, "Merge paths are not supported pre-Kit Kat.");
            return;
        }
        this.enableMergePaths = enable;
        if (this.composition != null) {
            buildCompositionLayer();
        }
    }

    public void setImagesAssetsFolder(String imageAssetsFolder2) {
        this.imageAssetsFolder = imageAssetsFolder2;
    }

    public void recycleBitmaps() {
        if (this.imageAssetBitmapManager != null) {
            this.imageAssetBitmapManager.recycleBitmaps();
        }
    }

    public boolean setComposition(LottieComposition composition2) {
        if (getCallback() == null) {
            throw new IllegalStateException("You or your view must set a Drawable.Callback before setting the composition. This gets done automatically when added to an ImageView. Either call ImageView.setImageDrawable() before setComposition() or call setCallback(yourView.getCallback()) first.");
        } else if (this.composition == composition2) {
            return false;
        } else {
            clearComposition();
            this.composition = composition2;
            setSpeed(this.speed);
            setScale(1.0f);
            updateBounds();
            buildCompositionLayer();
            applyColorFilters();
            setProgress(this.progress);
            if (this.playAnimationWhenCompositionAdded) {
                this.playAnimationWhenCompositionAdded = false;
                playAnimation();
            }
            if (this.reverseAnimationWhenCompositionAdded) {
                this.reverseAnimationWhenCompositionAdded = false;
                reverseAnimation();
            }
            return true;
        }
    }

    private void buildCompositionLayer() {
        this.compositionLayer = new CompositionLayer(this, Factory.newInstance(this.composition), this.composition.getLayers(), this.composition);
    }

    private void applyColorFilters() {
        if (this.compositionLayer != null) {
            for (ColorFilterData data : this.colorFilterData) {
                this.compositionLayer.addColorFilter(data.layerName, data.contentName, data.colorFilter);
            }
        }
    }

    private void clearComposition() {
        recycleBitmaps();
        this.compositionLayer = null;
        this.imageAssetBitmapManager = null;
        invalidateSelf();
    }

    public void invalidateSelf() {
        Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    public void setAlpha(int alpha2) {
        this.alpha = alpha2;
    }

    public int getAlpha() {
        return this.alpha;
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    public void addColorFilterToContent(String layerName, String contentName, ColorFilter colorFilter) {
        addColorFilterInternal(layerName, contentName, colorFilter);
    }

    public void addColorFilterToLayer(String layerName, ColorFilter colorFilter) {
        addColorFilterInternal(layerName, null, colorFilter);
    }

    public void addColorFilter(ColorFilter colorFilter) {
        addColorFilterInternal(null, null, colorFilter);
    }

    public void clearColorFilters() {
        this.colorFilterData.clear();
        addColorFilterInternal(null, null, null);
    }

    private void addColorFilterInternal(String layerName, String contentName, ColorFilter colorFilter) {
        ColorFilterData data = new ColorFilterData(layerName, contentName, colorFilter);
        if (colorFilter != null || !this.colorFilterData.contains(data)) {
            this.colorFilterData.add(new ColorFilterData(layerName, contentName, colorFilter));
        } else {
            this.colorFilterData.remove(data);
        }
        if (this.compositionLayer != null) {
            this.compositionLayer.addColorFilter(layerName, contentName, colorFilter);
        }
    }

    public int getOpacity() {
        return -3;
    }

    public void draw(Canvas canvas) {
        if (this.compositionLayer != null) {
            this.matrix.reset();
            this.matrix.preScale(this.scale, this.scale);
            this.compositionLayer.draw(canvas, this.matrix, this.alpha);
        }
    }

    /* access modifiers changed from: 0000 */
    public void systemAnimationsAreDisabled() {
        this.systemAnimationsAreDisabled = true;
    }

    public void loop(boolean loop) {
        this.animator.setRepeatCount(loop ? -1 : 0);
    }

    public boolean isLooping() {
        return this.animator.getRepeatCount() == -1;
    }

    public boolean isAnimating() {
        return this.animator.isRunning();
    }

    public void playAnimation() {
        playAnimation(((double) this.progress) > 0.0d && ((double) this.progress) < 1.0d);
    }

    public void resumeAnimation() {
        playAnimation(true);
    }

    private void playAnimation(boolean setStartTime) {
        if (this.compositionLayer == null) {
            this.playAnimationWhenCompositionAdded = true;
            this.reverseAnimationWhenCompositionAdded = false;
            return;
        }
        if (setStartTime) {
            this.animator.setCurrentPlayTime((long) (this.progress * ((float) this.animator.getDuration())));
        }
        this.animator.start();
    }

    public void resumeReverseAnimation() {
        reverseAnimation(true);
    }

    public void reverseAnimation() {
        reverseAnimation(((double) this.progress) > 0.0d && ((double) this.progress) < 1.0d);
    }

    private void reverseAnimation(boolean setStartTime) {
        if (this.compositionLayer == null) {
            this.playAnimationWhenCompositionAdded = false;
            this.reverseAnimationWhenCompositionAdded = true;
            return;
        }
        if (setStartTime) {
            this.animator.setCurrentPlayTime((long) (this.progress * ((float) this.animator.getDuration())));
        }
        this.animator.reverse();
    }

    public void setSpeed(float speed2) {
        this.speed = speed2;
        if (speed2 < 0.0f) {
            this.animator.setFloatValues(new float[]{1.0f, 0.0f});
        } else {
            this.animator.setFloatValues(new float[]{0.0f, 1.0f});
        }
        if (this.composition != null) {
            this.animator.setDuration((long) (((float) this.composition.getDuration()) / Math.abs(speed2)));
        }
    }

    public void setProgress(float progress2) {
        this.progress = progress2;
        if (this.compositionLayer != null) {
            this.compositionLayer.setProgress(progress2);
        }
    }

    public float getProgress() {
        return this.progress;
    }

    public void setScale(float scale2) {
        this.scale = scale2;
        updateBounds();
    }

    public void setImageAssetDelegate(ImageAssetDelegate assetDelegate) {
        this.imageAssetDelegate = assetDelegate;
        if (this.imageAssetBitmapManager != null) {
            this.imageAssetBitmapManager.setAssetDelegate(assetDelegate);
        }
    }

    public float getScale() {
        return this.scale;
    }

    public LottieComposition getComposition() {
        return this.composition;
    }

    private void updateBounds() {
        if (this.composition != null) {
            setBounds(0, 0, (int) (((float) this.composition.getBounds().width()) * this.scale), (int) (((float) this.composition.getBounds().height()) * this.scale));
        }
    }

    public void cancelAnimation() {
        this.playAnimationWhenCompositionAdded = false;
        this.reverseAnimationWhenCompositionAdded = false;
        this.animator.cancel();
    }

    public void addAnimatorUpdateListener(AnimatorUpdateListener updateListener) {
        this.animator.addUpdateListener(updateListener);
    }

    public void removeAnimatorUpdateListener(AnimatorUpdateListener updateListener) {
        this.animator.removeUpdateListener(updateListener);
    }

    public void addAnimatorListener(AnimatorListener listener) {
        this.animator.addListener(listener);
    }

    public void removeAnimatorListener(AnimatorListener listener) {
        this.animator.removeListener(listener);
    }

    public int getIntrinsicWidth() {
        if (this.composition == null) {
            return -1;
        }
        return (int) (((float) this.composition.getBounds().width()) * this.scale);
    }

    public int getIntrinsicHeight() {
        if (this.composition == null) {
            return -1;
        }
        return (int) (((float) this.composition.getBounds().height()) * this.scale);
    }

    /* access modifiers changed from: 0000 */
    public Bitmap getImageAsset(String id) {
        return getImageAssetBitmapManager().bitmapForId(id);
    }

    private ImageAssetBitmapManager getImageAssetBitmapManager() {
        if (this.imageAssetBitmapManager != null && !this.imageAssetBitmapManager.hasSameContext(getContext())) {
            this.imageAssetBitmapManager.recycleBitmaps();
            this.imageAssetBitmapManager = null;
        }
        if (this.imageAssetBitmapManager == null) {
            this.imageAssetBitmapManager = new ImageAssetBitmapManager(getCallback(), this.imageAssetsFolder, this.imageAssetDelegate, this.composition.getImages());
        }
        return this.imageAssetBitmapManager;
    }

    private Context getContext() {
        Callback callback = getCallback();
        if (callback != null && (callback instanceof View)) {
            return ((View) callback).getContext();
        }
        return null;
    }

    public void invalidateDrawable(Drawable who) {
        Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    public void scheduleDrawable(Drawable who, Runnable what, long when) {
        Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, what, when);
        }
    }

    public void unscheduleDrawable(Drawable who, Runnable what) {
        Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, what);
        }
    }
}
