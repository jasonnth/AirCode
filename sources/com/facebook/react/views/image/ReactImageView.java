package com.facebook.react.views.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.controller.ForwardingControllerListener;
import com.facebook.drawee.drawable.AutoRotateDrawable;
import com.facebook.drawee.drawable.ScalingUtils.ScaleType;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.generic.RoundingParams.RoundingMethod;
import com.facebook.drawee.view.GenericDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.request.Postprocessor;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.FloatUtil;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.imagehelper.ImageSource;
import com.facebook.react.views.imagehelper.MultiSourceHelper;
import com.facebook.react.views.imagehelper.MultiSourceHelper.MultiSourceResult;
import com.facebook.react.views.imagehelper.ResourceDrawableIdHelper;
import com.facebook.yoga.YogaConstants;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ReactImageView extends GenericDraweeView {
    public static final int REMOTE_IMAGE_FADE_DURATION_MS = 300;
    /* access modifiers changed from: private */
    public static float[] sComputedCornerRadii = new float[4];
    /* access modifiers changed from: private */
    public static final Matrix sInverse = new Matrix();
    /* access modifiers changed from: private */
    public static final Matrix sMatrix = new Matrix();
    private int mBorderColor;
    private float[] mBorderCornerRadii;
    private float mBorderRadius = Float.NaN;
    private float mBorderWidth;
    private ImageSource mCachedImageSource;
    private final Object mCallerContext;
    private ControllerListener mControllerForTesting;
    private ControllerListener mControllerListener;
    private final AbstractDraweeControllerBuilder mDraweeControllerBuilder;
    private int mFadeDurationMs = -1;
    /* access modifiers changed from: private */
    public ImageSource mImageSource;
    private boolean mIsDirty;
    private Drawable mLoadingImageDrawable;
    private int mOverlayColor;
    private boolean mProgressiveRenderingEnabled;
    private ImageResizeMethod mResizeMethod = ImageResizeMethod.AUTO;
    private final RoundedCornerPostprocessor mRoundedCornerPostprocessor;
    /* access modifiers changed from: private */
    public ScaleType mScaleType = ImageResizeMode.defaultValue();
    private final List<ImageSource> mSources;

    private class RoundedCornerPostprocessor extends BasePostprocessor {
        private RoundedCornerPostprocessor() {
        }

        /* access modifiers changed from: 0000 */
        public void getRadii(Bitmap source, float[] computedCornerRadii, float[] mappedRadii) {
            ReactImageView.this.mScaleType.getTransform(ReactImageView.sMatrix, new Rect(0, 0, source.getWidth(), source.getHeight()), source.getWidth(), source.getHeight(), 0.0f, 0.0f);
            ReactImageView.sMatrix.invert(ReactImageView.sInverse);
            mappedRadii[0] = ReactImageView.sInverse.mapRadius(computedCornerRadii[0]);
            mappedRadii[1] = mappedRadii[0];
            mappedRadii[2] = ReactImageView.sInverse.mapRadius(computedCornerRadii[1]);
            mappedRadii[3] = mappedRadii[2];
            mappedRadii[4] = ReactImageView.sInverse.mapRadius(computedCornerRadii[2]);
            mappedRadii[5] = mappedRadii[4];
            mappedRadii[6] = ReactImageView.sInverse.mapRadius(computedCornerRadii[3]);
            mappedRadii[7] = mappedRadii[6];
        }

        public void process(Bitmap output, Bitmap source) {
            ReactImageView.this.cornerRadii(ReactImageView.sComputedCornerRadii);
            output.setHasAlpha(true);
            if (!FloatUtil.floatsEqual(ReactImageView.sComputedCornerRadii[0], 0.0f) || !FloatUtil.floatsEqual(ReactImageView.sComputedCornerRadii[1], 0.0f) || !FloatUtil.floatsEqual(ReactImageView.sComputedCornerRadii[2], 0.0f) || !FloatUtil.floatsEqual(ReactImageView.sComputedCornerRadii[3], 0.0f)) {
                Paint paint = new Paint();
                paint.setAntiAlias(true);
                paint.setShader(new BitmapShader(source, TileMode.CLAMP, TileMode.CLAMP));
                Canvas canvas = new Canvas(output);
                float[] radii = new float[8];
                getRadii(source, ReactImageView.sComputedCornerRadii, radii);
                Path pathForBorderRadius = new Path();
                pathForBorderRadius.addRoundRect(new RectF(0.0f, 0.0f, (float) source.getWidth(), (float) source.getHeight()), radii, Direction.CW);
                canvas.drawPath(pathForBorderRadius, paint);
                return;
            }
            super.process(output, source);
        }
    }

    private static GenericDraweeHierarchy buildHierarchy(Context context) {
        return new GenericDraweeHierarchyBuilder(context.getResources()).setRoundingParams(RoundingParams.fromCornersRadius(0.0f)).build();
    }

    public ReactImageView(Context context, AbstractDraweeControllerBuilder draweeControllerBuilder, Object callerContext) {
        super(context, buildHierarchy(context));
        this.mDraweeControllerBuilder = draweeControllerBuilder;
        this.mRoundedCornerPostprocessor = new RoundedCornerPostprocessor();
        this.mCallerContext = callerContext;
        this.mSources = new LinkedList();
    }

    public void setShouldNotifyLoadEvents(boolean shouldNotify) {
        if (!shouldNotify) {
            this.mControllerListener = null;
        } else {
            final EventDispatcher mEventDispatcher = ((UIManagerModule) ((ReactContext) getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher();
            this.mControllerListener = new BaseControllerListener<ImageInfo>() {
                public void onSubmit(String id, Object callerContext) {
                    mEventDispatcher.dispatchEvent(new ImageLoadEvent(ReactImageView.this.getId(), 4));
                }

                public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                    if (imageInfo != null) {
                        mEventDispatcher.dispatchEvent(new ImageLoadEvent(ReactImageView.this.getId(), 2, ReactImageView.this.mImageSource.getSource(), imageInfo.getWidth(), imageInfo.getHeight()));
                        mEventDispatcher.dispatchEvent(new ImageLoadEvent(ReactImageView.this.getId(), 3));
                    }
                }

                public void onFailure(String id, Throwable throwable) {
                    mEventDispatcher.dispatchEvent(new ImageLoadEvent(ReactImageView.this.getId(), 1));
                    mEventDispatcher.dispatchEvent(new ImageLoadEvent(ReactImageView.this.getId(), 3));
                }
            };
        }
        this.mIsDirty = true;
    }

    public void setBorderColor(int borderColor) {
        this.mBorderColor = borderColor;
        this.mIsDirty = true;
    }

    public void setOverlayColor(int overlayColor) {
        this.mOverlayColor = overlayColor;
        this.mIsDirty = true;
    }

    public void setBorderWidth(float borderWidth) {
        this.mBorderWidth = PixelUtil.toPixelFromDIP(borderWidth);
        this.mIsDirty = true;
    }

    public void setBorderRadius(float borderRadius) {
        if (!FloatUtil.floatsEqual(this.mBorderRadius, borderRadius)) {
            this.mBorderRadius = borderRadius;
            this.mIsDirty = true;
        }
    }

    public void setBorderRadius(float borderRadius, int position) {
        if (this.mBorderCornerRadii == null) {
            this.mBorderCornerRadii = new float[4];
            Arrays.fill(this.mBorderCornerRadii, Float.NaN);
        }
        if (!FloatUtil.floatsEqual(this.mBorderCornerRadii[position], borderRadius)) {
            this.mBorderCornerRadii[position] = borderRadius;
            this.mIsDirty = true;
        }
    }

    public void setScaleType(ScaleType scaleType) {
        this.mScaleType = scaleType;
        this.mIsDirty = true;
    }

    public void setResizeMethod(ImageResizeMethod resizeMethod) {
        this.mResizeMethod = resizeMethod;
        this.mIsDirty = true;
    }

    public void setSource(ReadableArray sources) {
        this.mSources.clear();
        if (!(sources == null || sources.size() == 0)) {
            if (sources.size() == 1) {
                String uri = sources.getMap(0).getString("uri");
                ImageSource imageSource = new ImageSource(getContext(), uri);
                this.mSources.add(imageSource);
                if (Uri.EMPTY.equals(imageSource.getUri())) {
                    warnImageSource(uri);
                }
            } else {
                for (int idx = 0; idx < sources.size(); idx++) {
                    ReadableMap source = sources.getMap(idx);
                    String uri2 = source.getString("uri");
                    ImageSource imageSource2 = new ImageSource(getContext(), uri2, source.getDouble("width"), source.getDouble("height"));
                    this.mSources.add(imageSource2);
                    if (Uri.EMPTY.equals(imageSource2.getUri())) {
                        warnImageSource(uri2);
                    }
                }
            }
        }
        this.mIsDirty = true;
    }

    public void setLoadingIndicatorSource(String name) {
        Drawable drawable = ResourceDrawableIdHelper.getInstance().getResourceDrawable(getContext(), name);
        this.mLoadingImageDrawable = drawable != null ? new AutoRotateDrawable(drawable, 1000) : null;
        this.mIsDirty = true;
    }

    public void setProgressiveRenderingEnabled(boolean enabled) {
        this.mProgressiveRenderingEnabled = enabled;
    }

    public void setFadeDuration(int durationMs) {
        this.mFadeDurationMs = durationMs;
    }

    /* access modifiers changed from: private */
    public void cornerRadii(float[] computedCorners) {
        float f;
        float f2;
        float f3;
        float defaultBorderRadius = !YogaConstants.isUndefined(this.mBorderRadius) ? this.mBorderRadius : 0.0f;
        if (this.mBorderCornerRadii == null || YogaConstants.isUndefined(this.mBorderCornerRadii[0])) {
            f = defaultBorderRadius;
        } else {
            f = this.mBorderCornerRadii[0];
        }
        computedCorners[0] = f;
        if (this.mBorderCornerRadii == null || YogaConstants.isUndefined(this.mBorderCornerRadii[1])) {
            f2 = defaultBorderRadius;
        } else {
            f2 = this.mBorderCornerRadii[1];
        }
        computedCorners[1] = f2;
        if (this.mBorderCornerRadii == null || YogaConstants.isUndefined(this.mBorderCornerRadii[2])) {
            f3 = defaultBorderRadius;
        } else {
            f3 = this.mBorderCornerRadii[2];
        }
        computedCorners[2] = f3;
        if (this.mBorderCornerRadii != null && !YogaConstants.isUndefined(this.mBorderCornerRadii[3])) {
            defaultBorderRadius = this.mBorderCornerRadii[3];
        }
        computedCorners[3] = defaultBorderRadius;
    }

    public void maybeUpdateView() {
        if (this.mIsDirty) {
            if (!hasMultipleSources() || (getWidth() > 0 && getHeight() > 0)) {
                setSourceImage();
                if (this.mImageSource != null) {
                    boolean doResize = shouldResize(this.mImageSource);
                    if (!doResize || (getWidth() > 0 && getHeight() > 0)) {
                        GenericDraweeHierarchy hierarchy = (GenericDraweeHierarchy) getHierarchy();
                        hierarchy.setActualImageScaleType(this.mScaleType);
                        if (this.mLoadingImageDrawable != null) {
                            hierarchy.setPlaceholderImage(this.mLoadingImageDrawable, ScaleType.CENTER);
                        }
                        boolean usePostprocessorScaling = (this.mScaleType == ScaleType.CENTER_CROP || this.mScaleType == ScaleType.FOCUS_CROP) ? false : true;
                        RoundingParams roundingParams = hierarchy.getRoundingParams();
                        if (usePostprocessorScaling) {
                            roundingParams.setCornersRadius(0.0f);
                        } else {
                            cornerRadii(sComputedCornerRadii);
                            roundingParams.setCornersRadii(sComputedCornerRadii[0], sComputedCornerRadii[1], sComputedCornerRadii[2], sComputedCornerRadii[3]);
                        }
                        roundingParams.setBorder(this.mBorderColor, this.mBorderWidth);
                        if (this.mOverlayColor != 0) {
                            roundingParams.setOverlayColor(this.mOverlayColor);
                        } else {
                            roundingParams.setRoundingMethod(RoundingMethod.BITMAP_ONLY);
                        }
                        hierarchy.setRoundingParams(roundingParams);
                        int i = this.mFadeDurationMs >= 0 ? this.mFadeDurationMs : this.mImageSource.isResource() ? 0 : 300;
                        hierarchy.setFadeDuration(i);
                        Postprocessor postprocessor = usePostprocessorScaling ? this.mRoundedCornerPostprocessor : null;
                        ResizeOptions resizeOptions = doResize ? new ResizeOptions(getWidth(), getHeight()) : null;
                        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(this.mImageSource.getUri()).setPostprocessor(postprocessor).setResizeOptions(resizeOptions).setAutoRotateEnabled(true).setProgressiveRenderingEnabled(this.mProgressiveRenderingEnabled).build();
                        this.mDraweeControllerBuilder.reset();
                        this.mDraweeControllerBuilder.setAutoPlayAnimations(true).setCallerContext(this.mCallerContext).setOldController(getController()).setImageRequest(imageRequest);
                        if (this.mCachedImageSource != null) {
                            this.mDraweeControllerBuilder.setLowResImageRequest(ImageRequestBuilder.newBuilderWithSource(this.mCachedImageSource.getUri()).setPostprocessor(postprocessor).setResizeOptions(resizeOptions).setAutoRotateEnabled(true).setProgressiveRenderingEnabled(this.mProgressiveRenderingEnabled).build());
                        }
                        if (this.mControllerListener != null && this.mControllerForTesting != null) {
                            ForwardingControllerListener combinedListener = new ForwardingControllerListener();
                            combinedListener.addListener(this.mControllerListener);
                            combinedListener.addListener(this.mControllerForTesting);
                            this.mDraweeControllerBuilder.setControllerListener(combinedListener);
                        } else if (this.mControllerForTesting != null) {
                            this.mDraweeControllerBuilder.setControllerListener(this.mControllerForTesting);
                        } else if (this.mControllerListener != null) {
                            this.mDraweeControllerBuilder.setControllerListener(this.mControllerListener);
                        }
                        setController(this.mDraweeControllerBuilder.build());
                        this.mIsDirty = false;
                    }
                }
            }
        }
    }

    public void setControllerListener(ControllerListener controllerListener) {
        this.mControllerForTesting = controllerListener;
        this.mIsDirty = true;
        maybeUpdateView();
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w > 0 && h > 0) {
            this.mIsDirty = this.mIsDirty || hasMultipleSources();
            maybeUpdateView();
        }
    }

    public boolean hasOverlappingRendering() {
        return false;
    }

    private boolean hasMultipleSources() {
        return this.mSources.size() > 1;
    }

    private void setSourceImage() {
        this.mImageSource = null;
        if (!this.mSources.isEmpty()) {
            if (hasMultipleSources()) {
                MultiSourceResult multiSource = MultiSourceHelper.getBestSourceForSize(getWidth(), getHeight(), this.mSources);
                this.mImageSource = multiSource.getBestResult();
                this.mCachedImageSource = multiSource.getBestResultInCache();
                return;
            }
            this.mImageSource = (ImageSource) this.mSources.get(0);
        }
    }

    private boolean shouldResize(ImageSource imageSource) {
        if (this.mResizeMethod == ImageResizeMethod.AUTO) {
            if (UriUtil.isLocalContentUri(imageSource.getUri()) || UriUtil.isLocalFileUri(imageSource.getUri())) {
                return true;
            }
            return false;
        } else if (this.mResizeMethod == ImageResizeMethod.RESIZE) {
            return true;
        } else {
            return false;
        }
    }

    private void warnImageSource(String uri) {
    }
}
