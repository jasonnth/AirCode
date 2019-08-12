package com.airbnb.p027n2.primitives.imaging;

import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.widget.ImageView;
import com.airbnb.p027n2.N2Context;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ImageViewTarget;

/* renamed from: com.airbnb.n2.primitives.imaging.AirBaseImageViewTarget */
public class AirBaseImageViewTarget<Z> extends ImageViewTarget<Z> {
    private long loadStartTime;

    public AirBaseImageViewTarget(ImageView view) {
        super(view);
    }

    public void onLoadStarted(Drawable placeholder) {
        super.onLoadStarted(placeholder);
        this.loadStartTime = SystemClock.elapsedRealtime();
    }

    public void onLoadFailed(Exception e, Drawable errorDrawable) {
        super.onLoadFailed(e, errorDrawable);
        if (this.loadStartTime != 0) {
            N2Context.instance().graph().mo11971n2().onImageLoadError(getLoadTimeAndResetLoadStartTime(), e);
        }
    }

    public void onLoadCleared(Drawable placeholder) {
        super.onLoadCleared(placeholder);
        if (this.loadStartTime != 0) {
            N2Context.instance().graph().mo11971n2().onImageLoadCleared(getLoadTimeAndResetLoadStartTime());
        }
    }

    /* access modifiers changed from: protected */
    public void setResource(Z z) {
    }

    public void onResourceReady(Z resource, GlideAnimation<? super Z> glideAnimation, boolean fromMemoryCache) {
        super.onResourceReady(resource, glideAnimation, fromMemoryCache);
        if (this.loadStartTime != 0) {
            N2Context.instance().graph().mo11971n2().onImageLoaded(getLoadTimeAndResetLoadStartTime(), fromMemoryCache);
        }
    }

    private long getLoadTimeAndResetLoadStartTime() {
        long loadTime = SystemClock.elapsedRealtime() - this.loadStartTime;
        this.loadStartTime = 0;
        return loadTime;
    }
}
