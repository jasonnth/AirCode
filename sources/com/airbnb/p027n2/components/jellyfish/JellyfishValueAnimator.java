package com.airbnb.p027n2.components.jellyfish;

import android.view.animation.Interpolator;
import java.util.Random;

/* renamed from: com.airbnb.n2.components.jellyfish.JellyfishValueAnimator */
class JellyfishValueAnimator {
    private long animationStartTime = System.currentTimeMillis();
    private final long duration;
    private float endValue = generateRandomFloat();
    private final Interpolator interpolator;
    private float maxValue;
    private float minValue;
    private final Random random;
    private float startValue = generateRandomFloat();
    private final UpdateListener updateListener;

    /* renamed from: com.airbnb.n2.components.jellyfish.JellyfishValueAnimator$UpdateListener */
    interface UpdateListener {
        void onUpdate(float f);
    }

    public JellyfishValueAnimator(long duration2, Interpolator interpolator2, Random random2, float minValue2, float maxValue2, UpdateListener updateListener2) {
        this.duration = duration2;
        this.interpolator = interpolator2;
        this.minValue = minValue2;
        this.maxValue = maxValue2;
        this.updateListener = updateListener2;
        this.random = random2;
    }

    public void tick() {
        float fraction = ((float) (System.currentTimeMillis() - this.animationStartTime)) / ((float) this.duration);
        if (fraction > 1.0f) {
            restart();
            fraction = 0.0f;
        }
        this.updateListener.onUpdate(lerp(this.interpolator.getInterpolation(fraction)));
    }

    private void restart() {
        this.animationStartTime = System.currentTimeMillis();
        this.startValue = this.endValue;
        this.endValue = generateRandomFloat();
    }

    private float lerp(float f) {
        return this.startValue + ((this.endValue - this.startValue) * f);
    }

    private float generateRandomFloat() {
        return this.minValue + (this.random.nextFloat() * (this.maxValue - this.minValue));
    }
}
