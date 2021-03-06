package com.bumptech.glide.request.target;

import android.widget.ImageView;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;

public class GlideDrawableImageViewTarget extends ImageViewTarget<GlideDrawable> {
    private static final float SQUARE_RATIO_MARGIN = 0.05f;
    private int maxLoopCount;
    private GlideDrawable resource;

    public GlideDrawableImageViewTarget(ImageView view) {
        this(view, -1);
    }

    public GlideDrawableImageViewTarget(ImageView view, int maxLoopCount2) {
        super(view);
        this.maxLoopCount = maxLoopCount2;
    }

    public void onResourceReady(GlideDrawable resource2, GlideAnimation<? super GlideDrawable> animation, boolean fromMemoryCache) {
        if (!resource2.isAnimated()) {
            float drawableRatio = ((float) resource2.getIntrinsicWidth()) / ((float) resource2.getIntrinsicHeight());
            if (Math.abs((((float) ((ImageView) this.view).getWidth()) / ((float) ((ImageView) this.view).getHeight())) - 1.0f) <= 0.05f && Math.abs(drawableRatio - 1.0f) <= 0.05f) {
                resource2 = new SquaringDrawable(resource2, ((ImageView) this.view).getWidth());
            }
        }
        super.onResourceReady(resource2, animation, fromMemoryCache);
        this.resource = resource2;
        resource2.setLoopCount(this.maxLoopCount);
        resource2.start();
    }

    /* access modifiers changed from: protected */
    public void setResource(GlideDrawable resource2) {
        ((ImageView) this.view).setImageDrawable(resource2);
    }

    public void onStart() {
        if (this.resource != null) {
            this.resource.start();
        }
    }

    public void onStop() {
        if (this.resource != null) {
            this.resource.stop();
        }
    }
}
