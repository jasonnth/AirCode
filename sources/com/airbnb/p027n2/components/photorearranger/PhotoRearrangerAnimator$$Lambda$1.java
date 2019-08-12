package com.airbnb.p027n2.components.photorearranger;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

/* renamed from: com.airbnb.n2.components.photorearranger.PhotoRearrangerAnimator$$Lambda$1 */
final /* synthetic */ class PhotoRearrangerAnimator$$Lambda$1 implements AnimatorUpdateListener {
    private final AnimationListener arg$1;

    private PhotoRearrangerAnimator$$Lambda$1(AnimationListener animationListener) {
        this.arg$1 = animationListener;
    }

    public static AnimatorUpdateListener lambdaFactory$(AnimationListener animationListener) {
        return new PhotoRearrangerAnimator$$Lambda$1(animationListener);
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.arg$1.valueUpdated((Float) valueAnimator.getAnimatedValue());
    }
}
