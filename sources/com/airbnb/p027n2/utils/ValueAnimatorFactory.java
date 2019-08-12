package com.airbnb.p027n2.utils;

import android.animation.ValueAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

/* renamed from: com.airbnb.n2.utils.ValueAnimatorFactory */
public class ValueAnimatorFactory {
    private final ValueAnimator animator;

    /* renamed from: com.airbnb.n2.utils.ValueAnimatorFactory$Listener */
    public interface Listener {
        void valueUpdated(Float f);
    }

    public static ValueAnimatorFactory ofFloat(float... values) {
        return new ValueAnimatorFactory(ValueAnimator.ofFloat(values));
    }

    private ValueAnimatorFactory(ValueAnimator animator2) {
        this.animator = animator2;
    }

    public ValueAnimatorFactory setDuration(long durationMs) {
        this.animator.setDuration(durationMs);
        return this;
    }

    public ValueAnimatorFactory setInterpolator(Interpolator interpolator) {
        this.animator.setInterpolator(interpolator);
        return this;
    }

    public ValueAnimatorFactory setAccelerateDecelerateInterpolator() {
        return setInterpolator(new AccelerateDecelerateInterpolator());
    }

    public ValueAnimatorFactory addListener(Listener listener) {
        this.animator.addUpdateListener(ValueAnimatorFactory$$Lambda$1.lambdaFactory$(listener));
        return this;
    }

    public ValueAnimator build() {
        return this.animator;
    }
}
