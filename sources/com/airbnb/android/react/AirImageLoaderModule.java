package com.airbnb.android.react;

import android.graphics.Bitmap;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.p027n2.primitives.imaging.AirImageListener;
import com.airbnb.p027n2.primitives.imaging.AirImageViewGlideHelper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;

public class AirImageLoaderModule extends VersionedReactModuleBase {
    private static final String ERROR_GET_SIZE_FAILURE = "E_GET_SIZE_FAILURE";
    private static final String ERROR_INVALID_URI = "E_INVALID_URI";
    private static final int VERSION = 1;

    AirImageLoaderModule(ReactApplicationContext reactContext) {
        super(reactContext, 1);
    }

    public String getName() {
        return "ImageLoader";
    }

    @ReactMethod
    public void prefetchImage(String uriString, int requestId, final Promise promise) {
        AirImageViewGlideHelper.getImage(getReactApplicationContext(), uriString, new AirImageListener() {
            public void onResponse(Bitmap response, boolean isImmediate) {
                promise.resolve(Boolean.valueOf(true));
            }

            public void onErrorResponse(Exception exception) {
                promise.resolve(Boolean.valueOf(false));
            }
        });
    }

    @ReactMethod
    public void getSize(final String uriString, final Promise promise) {
        if (uriString == null || uriString.isEmpty()) {
            promise.reject(ERROR_INVALID_URI, "Cannot get the size of an image for an empty URI");
            return;
        }
        try {
            Glide.with(getCurrentActivity()).load(uriString).diskCacheStrategy(DiskCacheStrategy.SOURCE).skipMemoryCache(false).listener((RequestListener<? super ModelType, GlideDrawable>) new RequestListener<Object, GlideDrawable>() {
                public boolean onException(Exception e, Object model, Target<GlideDrawable> target, boolean isFirstResource) {
                    Exception exception = e;
                    if (exception == null) {
                        exception = new Exception("Image request failed for unknown reason, but exception was null. Url: " + uriString);
                        BugsnagWrapper.notify((Throwable) exception);
                    }
                    promise.reject(AirImageLoaderModule.ERROR_GET_SIZE_FAILURE, (Throwable) exception);
                    return false;
                }

                public boolean onResourceReady(GlideDrawable resource, Object model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                    WritableMap sizes = Arguments.createMap();
                    sizes.putInt("width", resource.getIntrinsicWidth());
                    sizes.putInt("height", resource.getIntrinsicHeight());
                    promise.resolve(sizes);
                    return true;
                }
            }).into(Integer.MIN_VALUE, Integer.MIN_VALUE);
        } catch (IllegalArgumentException e) {
            BugsnagWrapper.notify((Throwable) e);
            promise.reject(ERROR_GET_SIZE_FAILURE, (Throwable) e);
        }
    }
}
