package com.facebook.drawee.generic;

import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import com.facebook.common.internal.Preconditions;
import com.facebook.drawee.drawable.ArrayDrawable;
import com.facebook.drawee.drawable.ScalingUtils.ScaleType;
import java.util.Arrays;
import java.util.List;

public class GenericDraweeHierarchyBuilder {
    public static final ScaleType DEFAULT_ACTUAL_IMAGE_SCALE_TYPE = ScaleType.CENTER_CROP;
    public static final int DEFAULT_FADE_DURATION = 300;
    public static final ScaleType DEFAULT_SCALE_TYPE = ScaleType.CENTER_INSIDE;
    private ColorFilter mActualImageColorFilter;
    private PointF mActualImageFocusPoint;
    private Matrix mActualImageMatrix;
    private ScaleType mActualImageScaleType;
    private Drawable mBackground;
    private float mDesiredAspectRatio;
    private int mFadeDuration;
    private Drawable mFailureImage;
    private ScaleType mFailureImageScaleType;
    private List<Drawable> mOverlays;
    private Drawable mPlaceholderImage;
    private ScaleType mPlaceholderImageScaleType;
    private Drawable mPressedStateOverlay;
    private Drawable mProgressBarImage;
    private ScaleType mProgressBarImageScaleType;
    private Resources mResources;
    private Drawable mRetryImage;
    private ScaleType mRetryImageScaleType;
    private RoundingParams mRoundingParams;

    public GenericDraweeHierarchyBuilder(Resources resources) {
        this.mResources = resources;
        init();
    }

    public static GenericDraweeHierarchyBuilder newInstance(Resources resources) {
        return new GenericDraweeHierarchyBuilder(resources);
    }

    private void init() {
        this.mFadeDuration = 300;
        this.mDesiredAspectRatio = 0.0f;
        this.mPlaceholderImage = null;
        this.mPlaceholderImageScaleType = DEFAULT_SCALE_TYPE;
        this.mRetryImage = null;
        this.mRetryImageScaleType = DEFAULT_SCALE_TYPE;
        this.mFailureImage = null;
        this.mFailureImageScaleType = DEFAULT_SCALE_TYPE;
        this.mProgressBarImage = null;
        this.mProgressBarImageScaleType = DEFAULT_SCALE_TYPE;
        this.mActualImageScaleType = DEFAULT_ACTUAL_IMAGE_SCALE_TYPE;
        this.mActualImageMatrix = null;
        this.mActualImageFocusPoint = null;
        this.mActualImageColorFilter = null;
        this.mBackground = null;
        this.mOverlays = null;
        this.mPressedStateOverlay = null;
        this.mRoundingParams = null;
    }

    public GenericDraweeHierarchyBuilder reset() {
        init();
        return this;
    }

    public Resources getResources() {
        return this.mResources;
    }

    public GenericDraweeHierarchyBuilder setFadeDuration(int fadeDuration) {
        this.mFadeDuration = fadeDuration;
        return this;
    }

    public int getFadeDuration() {
        return this.mFadeDuration;
    }

    public GenericDraweeHierarchyBuilder setDesiredAspectRatio(float desiredAspectRatio) {
        this.mDesiredAspectRatio = desiredAspectRatio;
        return this;
    }

    public float getDesiredAspectRatio() {
        return this.mDesiredAspectRatio;
    }

    public GenericDraweeHierarchyBuilder setPlaceholderImage(Drawable placeholderDrawable) {
        this.mPlaceholderImage = placeholderDrawable;
        return this;
    }

    public GenericDraweeHierarchyBuilder setPlaceholderImage(int resourceId) {
        this.mPlaceholderImage = this.mResources.getDrawable(resourceId);
        return this;
    }

    public Drawable getPlaceholderImage() {
        return this.mPlaceholderImage;
    }

    public GenericDraweeHierarchyBuilder setPlaceholderImageScaleType(ScaleType placeholderImageScaleType) {
        this.mPlaceholderImageScaleType = placeholderImageScaleType;
        return this;
    }

    public ScaleType getPlaceholderImageScaleType() {
        return this.mPlaceholderImageScaleType;
    }

    public GenericDraweeHierarchyBuilder setPlaceholderImage(Drawable placeholderDrawable, ScaleType placeholderImageScaleType) {
        this.mPlaceholderImage = placeholderDrawable;
        this.mPlaceholderImageScaleType = placeholderImageScaleType;
        return this;
    }

    public GenericDraweeHierarchyBuilder setPlaceholderImage(int resourceId, ScaleType placeholderImageScaleType) {
        this.mPlaceholderImage = this.mResources.getDrawable(resourceId);
        this.mPlaceholderImageScaleType = placeholderImageScaleType;
        return this;
    }

    public GenericDraweeHierarchyBuilder setRetryImage(Drawable retryDrawable) {
        this.mRetryImage = retryDrawable;
        return this;
    }

    public GenericDraweeHierarchyBuilder setRetryImage(int resourceId) {
        this.mRetryImage = this.mResources.getDrawable(resourceId);
        return this;
    }

    public Drawable getRetryImage() {
        return this.mRetryImage;
    }

    public GenericDraweeHierarchyBuilder setRetryImageScaleType(ScaleType retryImageScaleType) {
        this.mRetryImageScaleType = retryImageScaleType;
        return this;
    }

    public ScaleType getRetryImageScaleType() {
        return this.mRetryImageScaleType;
    }

    public GenericDraweeHierarchyBuilder setRetryImage(Drawable retryDrawable, ScaleType retryImageScaleType) {
        this.mRetryImage = retryDrawable;
        this.mRetryImageScaleType = retryImageScaleType;
        return this;
    }

    public GenericDraweeHierarchyBuilder setRetryImage(int resourceId, ScaleType retryImageScaleType) {
        this.mRetryImage = this.mResources.getDrawable(resourceId);
        this.mRetryImageScaleType = retryImageScaleType;
        return this;
    }

    public GenericDraweeHierarchyBuilder setFailureImage(Drawable failureDrawable) {
        this.mFailureImage = failureDrawable;
        return this;
    }

    public GenericDraweeHierarchyBuilder setFailureImage(int resourceId) {
        this.mFailureImage = this.mResources.getDrawable(resourceId);
        return this;
    }

    public Drawable getFailureImage() {
        return this.mFailureImage;
    }

    public GenericDraweeHierarchyBuilder setFailureImageScaleType(ScaleType failureImageScaleType) {
        this.mFailureImageScaleType = failureImageScaleType;
        return this;
    }

    public ScaleType getFailureImageScaleType() {
        return this.mFailureImageScaleType;
    }

    public GenericDraweeHierarchyBuilder setFailureImage(Drawable failureDrawable, ScaleType failureImageScaleType) {
        this.mFailureImage = failureDrawable;
        this.mFailureImageScaleType = failureImageScaleType;
        return this;
    }

    public GenericDraweeHierarchyBuilder setFailureImage(int resourceId, ScaleType failureImageScaleType) {
        this.mFailureImage = this.mResources.getDrawable(resourceId);
        this.mFailureImageScaleType = failureImageScaleType;
        return this;
    }

    public GenericDraweeHierarchyBuilder setProgressBarImage(Drawable progressBarDrawable) {
        this.mProgressBarImage = progressBarDrawable;
        return this;
    }

    public GenericDraweeHierarchyBuilder setProgressBarImage(int resourceId) {
        this.mProgressBarImage = this.mResources.getDrawable(resourceId);
        return this;
    }

    public Drawable getProgressBarImage() {
        return this.mProgressBarImage;
    }

    public GenericDraweeHierarchyBuilder setProgressBarImageScaleType(ScaleType progressBarImageScaleType) {
        this.mProgressBarImageScaleType = progressBarImageScaleType;
        return this;
    }

    public ScaleType getProgressBarImageScaleType() {
        return this.mProgressBarImageScaleType;
    }

    public GenericDraweeHierarchyBuilder setProgressBarImage(Drawable progressBarDrawable, ScaleType progressBarImageScaleType) {
        this.mProgressBarImage = progressBarDrawable;
        this.mProgressBarImageScaleType = progressBarImageScaleType;
        return this;
    }

    public GenericDraweeHierarchyBuilder setProgressBarImage(int resourceId, ScaleType progressBarImageScaleType) {
        this.mProgressBarImage = this.mResources.getDrawable(resourceId);
        this.mProgressBarImageScaleType = progressBarImageScaleType;
        return this;
    }

    public GenericDraweeHierarchyBuilder setActualImageScaleType(ScaleType actualImageScaleType) {
        this.mActualImageScaleType = actualImageScaleType;
        this.mActualImageMatrix = null;
        return this;
    }

    public ScaleType getActualImageScaleType() {
        return this.mActualImageScaleType;
    }

    @Deprecated
    public GenericDraweeHierarchyBuilder setActualImageMatrix(Matrix actualImageMatrix) {
        this.mActualImageMatrix = actualImageMatrix;
        this.mActualImageScaleType = null;
        return this;
    }

    public Matrix getActualImageMatrix() {
        return this.mActualImageMatrix;
    }

    public GenericDraweeHierarchyBuilder setActualImageFocusPoint(PointF focusPoint) {
        this.mActualImageFocusPoint = focusPoint;
        return this;
    }

    public PointF getActualImageFocusPoint() {
        return this.mActualImageFocusPoint;
    }

    public GenericDraweeHierarchyBuilder setActualImageColorFilter(ColorFilter colorFilter) {
        this.mActualImageColorFilter = colorFilter;
        return this;
    }

    public ColorFilter getActualImageColorFilter() {
        return this.mActualImageColorFilter;
    }

    @Deprecated
    public GenericDraweeHierarchyBuilder setBackgrounds(List<Drawable> backgrounds) {
        if (backgrounds == null) {
            this.mBackground = null;
        } else {
            this.mBackground = new ArrayDrawable((Drawable[]) backgrounds.toArray(new Drawable[backgrounds.size()]));
        }
        return this;
    }

    public GenericDraweeHierarchyBuilder setBackground(Drawable background) {
        this.mBackground = background;
        return this;
    }

    public Drawable getBackground() {
        return this.mBackground;
    }

    public GenericDraweeHierarchyBuilder setOverlays(List<Drawable> overlays) {
        this.mOverlays = overlays;
        return this;
    }

    public GenericDraweeHierarchyBuilder setOverlay(Drawable overlay) {
        if (overlay == null) {
            this.mOverlays = null;
        } else {
            this.mOverlays = Arrays.asList(new Drawable[]{overlay});
        }
        return this;
    }

    public List<Drawable> getOverlays() {
        return this.mOverlays;
    }

    public GenericDraweeHierarchyBuilder setPressedStateOverlay(Drawable drawable) {
        if (drawable == null) {
            this.mPressedStateOverlay = null;
        } else {
            StateListDrawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(new int[]{16842919}, drawable);
            this.mPressedStateOverlay = stateListDrawable;
        }
        return this;
    }

    public Drawable getPressedStateOverlay() {
        return this.mPressedStateOverlay;
    }

    public GenericDraweeHierarchyBuilder setRoundingParams(RoundingParams roundingParams) {
        this.mRoundingParams = roundingParams;
        return this;
    }

    public RoundingParams getRoundingParams() {
        return this.mRoundingParams;
    }

    private void validate() {
        if (this.mOverlays != null) {
            for (Drawable overlay : this.mOverlays) {
                Preconditions.checkNotNull(overlay);
            }
        }
    }

    public GenericDraweeHierarchy build() {
        validate();
        return new GenericDraweeHierarchy(this);
    }
}
