package com.airbnb.p027n2.utils;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import com.airbnb.p027n2.utils.ValueAnimatorFactory.Listener;

/* renamed from: com.airbnb.n2.utils.ValueAnimatorFactory$$Lambda$1 */
final /* synthetic */ class ValueAnimatorFactory$$Lambda$1 implements AnimatorUpdateListener {
    private final Listener arg$1;

    private ValueAnimatorFactory$$Lambda$1(Listener listener) {
        this.arg$1 = listener;
    }

    public static AnimatorUpdateListener lambdaFactory$(Listener listener) {
        return new ValueAnimatorFactory$$Lambda$1(listener);
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.arg$1.valueUpdated((Float) valueAnimator.getAnimatedValue());
    }
}
