package com.airbnb.android.react;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.p002v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.ImageView.ScaleType;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.glide.RoundedCornersTransformation;
import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.Headers;
import com.bumptech.glide.load.model.LazyHeaders.Builder;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.DrawableCrossFadeFactory;
import com.bumptech.glide.request.animation.GlideAnimation.ViewAdapter;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.views.imagehelper.ResourceDrawableIdHelper;
import com.google.common.collect.Iterables;
import java.util.ArrayList;
import java.util.List;

public class ReactAirImageView extends AppCompatImageView {
    private int borderColor;
    private float borderRadius;
    private float borderWidth;
    private int fadeDurationMs = -1;
    private ReadableMap headers;
    private boolean isDirty;
    private Drawable loadingImageDrawable;
    private String previousSource;
    private String source;

    public ReactAirImageView(Context context) {
        super(context);
        init();
    }

    public ReactAirImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ReactAirImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setScaleType(ScaleType.CENTER_CROP);
    }

    public void setSource(ReadableArray sources) {
        if (sources != null && sources.size() != 0) {
            this.previousSource = this.source;
            this.source = sources.getMap(0).getString("uri");
            this.isDirty = true;
        }
    }

    public void setHeaders(ReadableMap headers2) {
        this.headers = headers2;
    }

    public void setLoadingIndicatorSource(String name) {
        this.loadingImageDrawable = ResourceDrawableIdHelper.getInstance().getResourceDrawable(getContext(), name);
        this.isDirty = true;
    }

    public void setScaleType(ScaleType scaleType) {
        super.setScaleType(scaleType);
        this.isDirty = true;
    }

    public void setBorderColor(int borderColor2) {
        this.borderColor = borderColor2;
        this.isDirty = true;
    }

    public void setOverlayColor(int overlayColor) {
        this.isDirty = true;
    }

    public void setBorderWidth(float borderWidth2) {
        this.borderWidth = PixelUtil.toPixelFromDIP(borderWidth2);
        this.isDirty = true;
    }

    public void setBorderRadius(float borderRadius2) {
        this.borderRadius = PixelUtil.toPixelFromDIP(borderRadius2);
        this.isDirty = true;
    }

    public void setProgressiveRenderingEnabled(boolean enabled) {
    }

    public void setFadeDuration(int fadeDurationMs2) {
        this.fadeDurationMs = fadeDurationMs2;
    }

    public void setShouldNotifyLoadEvents(boolean shouldNotifyLoadEvents) {
    }

    private boolean hasScheme(String source2) {
        try {
            if (Uri.parse(source2).getScheme() != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public DrawableRequestBuilder<?> buildRequestForSource(Context context, String source2) {
        DrawableRequestBuilder<?> request;
        if (!hasScheme(source2)) {
            request = Glide.with(context).load(Integer.valueOf(ResourceDrawableIdHelper.getInstance().getResourceDrawableId(getContext(), source2)));
        } else if (this.headers != null) {
            Builder builder = new Builder();
            ReadableMapKeySetIterator iterator = this.headers.keySetIterator();
            while (iterator.hasNextKey()) {
                String key = iterator.nextKey();
                builder.addHeader(key, this.headers.getString(key));
            }
            request = Glide.with(context).load(new GlideUrl(source2, (Headers) builder.build()));
        } else {
            request = Glide.with(context).load(source2);
        }
        List<BitmapTransformation> transformations = new ArrayList<>(2);
        if (getScaleType() == ScaleType.CENTER_CROP) {
            transformations.add(new CenterCrop(getContext()));
        } else if (getScaleType() == ScaleType.FIT_CENTER) {
            transformations.add(new FitCenter(getContext()));
        }
        if (this.borderRadius > 0.0f || this.borderWidth > 0.0f) {
            transformations.add(new RoundedCornersTransformation(getContext(), (int) this.borderRadius, (int) this.borderWidth, this.borderColor));
        }
        if (!transformations.isEmpty()) {
            request = request.transform((BitmapTransformation[]) Iterables.toArray((Iterable<? extends T>) transformations, BitmapTransformation.class));
        }
        if (this.fadeDurationMs > 0) {
            request = request.crossFade(this.fadeDurationMs);
        }
        if (this.loadingImageDrawable != null) {
            request.placeholder(this.loadingImageDrawable);
        }
        return request.dontAnimate().diskCacheStrategy(DiskCacheStrategy.SOURCE).skipMemoryCache(false);
    }

    public void maybeUpdateView() {
        if (this.isDirty) {
            Context context = getContext();
            if (context == null) {
                return;
            }
            if (VERSION.SDK_INT < 17 || !(context instanceof Activity) || !((Activity) context).isDestroyed()) {
                try {
                    DrawableRequestBuilder<?> request = buildRequestForSource(context, this.source);
                    if (this.previousSource != null) {
                        try {
                            request.thumbnail(buildRequestForSource(context, this.previousSource).crossFade(0));
                        } catch (IllegalArgumentException e) {
                            BugsnagWrapper.notify((Throwable) e);
                        }
                        request.listener((RequestListener<? super ModelType, GlideDrawable>) new RequestListener<Object, GlideDrawable>() {
                            public boolean onException(Exception e, Object model, Target<GlideDrawable> target, boolean isFirstResource) {
                                return false;
                            }

                            public boolean onResourceReady(GlideDrawable resource, Object model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                if (isFirstResource) {
                                    return false;
                                }
                                return new DrawableCrossFadeFactory().build(false, false).animate(resource, (ViewAdapter) target);
                            }
                        });
                    }
                    request.into(new GlideDrawableImageViewTarget(this) {
                        public void setDrawable(Drawable drawable) {
                            if (drawable instanceof TransitionDrawable) {
                                ((TransitionDrawable) drawable).setCrossFadeEnabled(false);
                            }
                            super.setDrawable(drawable);
                        }
                    });
                } catch (IllegalArgumentException e2) {
                    BugsnagWrapper.notify((Throwable) e2);
                }
            }
        }
    }
}
