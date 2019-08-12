package com.airbnb.p027n2.primitives.imaging;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.p000v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.p000v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.TextUtils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.N2Context;
import com.airbnb.p027n2.utils.Base64Model;
import com.bumptech.glide.BitmapRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.stream.StreamModelLoader;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.DrawableCrossFadeFactory;
import com.bumptech.glide.request.animation.GlideAnimation.ViewAdapter;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.Target;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: com.airbnb.n2.primitives.imaging.AirImageViewGlideHelper */
public class AirImageViewGlideHelper {
    private static final NetworkDisablingLoader NETWORK_DISABLING_LOADER = new NetworkDisablingLoader();
    /* access modifiers changed from: private */
    public static ThumbnailHelper THUMBNAIL_HELPER;
    private final AirBitmapImageViewTarget bitmapTarget;
    /* access modifiers changed from: private */
    public final AirImageView imageView;
    private final SizeDeterminer imageViewSize;
    /* access modifiers changed from: private */
    public String url;

    /* renamed from: com.airbnb.n2.primitives.imaging.AirImageViewGlideHelper$NetworkDisablingLoader */
    private static class NetworkDisablingLoader implements StreamModelLoader<String> {
        private NetworkDisablingLoader() {
        }

        public DataFetcher<InputStream> getResourceFetcher(final String model, int width, int height) {
            return new DataFetcher<InputStream>() {
                public InputStream loadData(Priority priority) throws Exception {
                    throw new IOException("Forced Glide network failure");
                }

                public void cleanup() {
                }

                public String getId() {
                    return model;
                }

                public void cancel() {
                }
            };
        }
    }

    public AirImageViewGlideHelper(AirImageView airImageView, SizeDeterminer imageViewSize2) {
        if (THUMBNAIL_HELPER == null) {
            THUMBNAIL_HELPER = new ThumbnailHelper(airImageView.getContext());
        }
        this.imageView = airImageView;
        this.bitmapTarget = new AirBitmapImageViewTarget(airImageView);
        this.imageViewSize = imageViewSize2;
    }

    public void fetchImage(Image image, BitmapTransformation transformation, RequestListener<String, Bitmap> listener) {
        String url2 = image.getUrlForSize(ImageSize.getSizeForDimensions(this.imageViewSize.getViewWidth(), this.imageViewSize.getViewHeight()));
        if (!url2.equals(this.url)) {
            this.url = url2;
            RequestManager requestManager = getRequestManager(this.imageView.getContext());
            if (requestManager == null) {
                this.imageView.clear();
                return;
            }
            BitmapRequestBuilder<String, Bitmap> requestBuilder = requestManager.load(url2).asBitmap().diskCacheStrategy(DiskCacheStrategy.SOURCE);
            if (this.imageView.shouldUseARGB8888()) {
                requestBuilder.format(DecodeFormat.PREFER_ARGB_8888);
            }
            if (this.imageView.isFadeEnabled()) {
                requestBuilder.animate(R.anim.n2_fade_in_fast);
            }
            addPlaceholder(requestBuilder);
            addThumbnails(image, transformation, requestBuilder, requestManager);
            addRequestListener(image, listener, requestBuilder);
            applyTransformationIfNeeded(transformation, requestBuilder);
            requestBuilder.into(this.bitmapTarget);
        }
    }

    private void addRequestListener(final Image image, final RequestListener<String, Bitmap> listener, BitmapRequestBuilder<String, Bitmap> requestBuilder) {
        requestBuilder.listener((RequestListener<? super ModelType, TranscodeType>) new RequestListener<String, Bitmap>() {
            public boolean onException(Exception e, String url, Target<Bitmap> target, boolean isFirstResource) {
                AirImageViewGlideHelper.this.url = null;
                return listener != null && listener.onException(e, url, target, isFirstResource);
            }

            public boolean onResourceReady(Bitmap resource, String url, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                AirImageViewGlideHelper.THUMBNAIL_HELPER.setImageDetails(image, url, target);
                return (listener != null && listener.onResourceReady(resource, url, target, isFromMemoryCache, isFirstResource)) || AirImageViewGlideHelper.this.handleThumbnailCrossfade(resource, isFirstResource, target);
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean handleThumbnailCrossfade(Bitmap resource, boolean isFirstResource, Target<Bitmap> target) {
        if (isFirstResource) {
            return false;
        }
        new DrawableCrossFadeFactory(300).build(false, false).animate(new BitmapDrawable(this.imageView.getResources(), resource), (ViewAdapter) target);
        return true;
    }

    private void addPlaceholder(BitmapRequestBuilder<String, Bitmap> requestBuilder) {
        if (this.imageView.getPlaceholderDrawable() != null) {
            requestBuilder.placeholder(this.imageView.getPlaceholderDrawable());
        } else if (this.imageView.getPlaceholderResId() != -1) {
            requestBuilder.placeholder(this.imageView.getPlaceholderResId());
        }
    }

    private void addThumbnails(Image image, BitmapTransformation transformation, BitmapRequestBuilder<String, Bitmap> requestBuilder, RequestManager requestManager) {
        BitmapRequestBuilder bitmapRequestBuilder = null;
        String base64Preview = image.getBase64Preview();
        if (!TextUtils.isEmpty(base64Preview)) {
            bitmapRequestBuilder = requestManager.load(new Base64Model(base64Preview)).asBitmap().priority(Priority.IMMEDIATE).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true);
            applyTransformationIfNeeded(transformation, bitmapRequestBuilder);
        }
        ImageData imageData = THUMBNAIL_HELPER.getImageDetails(image);
        if (imageData != null) {
            BitmapRequestBuilder<String, Bitmap> thumbnailRequest = requestManager.using(NETWORK_DISABLING_LOADER).load(imageData.url).asBitmap().override(imageData.size.x, imageData.size.y).diskCacheStrategy(DiskCacheStrategy.SOURCE).priority(Priority.IMMEDIATE);
            applyTransformationIfNeeded(transformation, thumbnailRequest);
            if (bitmapRequestBuilder != null) {
                thumbnailRequest.thumbnail(bitmapRequestBuilder);
            }
            bitmapRequestBuilder = thumbnailRequest;
        }
        if (bitmapRequestBuilder != null) {
            requestBuilder.thumbnail(bitmapRequestBuilder);
        }
    }

    private void applyTransformationIfNeeded(BitmapTransformation transformation, BitmapRequestBuilder requestBuilder) {
        if (transformation == null) {
            requestBuilder.dontTransform();
            return;
        }
        requestBuilder.transform(transformation);
    }

    public static void getImage(Context context, String url2, final AirImageListener listener) {
        RequestListener<String, Bitmap> requestListener = new RequestListener<String, Bitmap>() {
            public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                listener.onErrorResponse(e);
                return true;
            }

            public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                listener.onResponse(resource, isFromMemoryCache);
                return true;
            }
        };
        RequestManager requestManager = getRequestManager(context);
        if (requestManager != null) {
            requestManager.load(url2).asBitmap().atMost().listener(requestListener).into(Integer.MIN_VALUE, Integer.MIN_VALUE);
        }
    }

    public void fetchImageWithRoundedCorners(String url2) {
        fetchImageWithRoundedCorners(url2, 15.0f);
    }

    public void fetchImageWithRoundedCorners(String url2, final float radius) {
        BitmapImageViewTarget target = new BitmapImageViewTarget(this.imageView) {
            /* access modifiers changed from: protected */
            public void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(AirImageViewGlideHelper.this.imageView.getContext().getResources(), resource);
                circularBitmapDrawable.setCornerRadius(radius);
                AirImageViewGlideHelper.this.imageView.setImageDrawable(circularBitmapDrawable);
            }
        };
        RequestManager requestManager = getRequestManager(this.imageView.getContext());
        if (requestManager != null) {
            requestManager.load(url2).asBitmap().atMost().into(target);
        }
    }

    public void clear() {
        this.url = null;
        Glide.clear(this.bitmapTarget);
    }

    private static RequestManager getRequestManager(Context context) {
        try {
            return Glide.with(context);
        } catch (IllegalArgumentException | IllegalStateException e) {
            N2Context.instance().graph().mo11971n2().onNotifyException(e);
            return null;
        }
    }
}
