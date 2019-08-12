package com.airbnb.p027n2.components.photorearranger;

import android.animation.ValueAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;

/* renamed from: com.airbnb.n2.components.photorearranger.PhotoRearrangerAnimator */
class PhotoRearrangerAnimator {

    /* renamed from: com.airbnb.n2.components.photorearranger.PhotoRearrangerAnimator$AnimationListener */
    interface AnimationListener {
        void valueUpdated(Float f);
    }

    static ValueAnimator create(int durationMs, float start, float finish, AnimationListener listener) {
        ValueAnimator animator = ValueAnimator.ofFloat(new float[]{start, finish});
        animator.setDuration((long) durationMs);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addUpdateListener(PhotoRearrangerAnimator$$Lambda$1.lambdaFactory$(listener));
        return animator;
    }
}
