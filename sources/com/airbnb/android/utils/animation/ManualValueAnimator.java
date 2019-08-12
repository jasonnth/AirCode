package com.airbnb.android.utils.animation;

import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.List;

public class ManualValueAnimator {
    protected final float end;
    private float endFraction;
    protected Interpolator interpolator;
    protected final float start;
    private float startFraction;
    private UpdateListener updateListener;

    public static final class Set extends ManualValueAnimator {
        private final List<ManualValueAnimator> animators;

        public Set() {
            this(0.0f, 1.0f);
        }

        private Set(float start, float end) {
            super(start, end);
            this.animators = new ArrayList();
        }

        public void add(ManualValueAnimator animator) {
            this.animators.add(animator);
        }

        public Set updateListener(UpdateListener updateListener) {
            return (Set) ManualValueAnimator.super.updateListener(updateListener);
        }

        public void setAnimatedFraction(float fraction) {
            for (ManualValueAnimator animator : this.animators) {
                animator.setAnimatedFraction(fraction);
            }
        }
    }

    public interface UpdateListener {
        void onUpdate(float f, float f2);
    }

    public ManualValueAnimator(float start2, float end2) {
        this.start = start2;
        this.end = end2;
    }

    public ManualValueAnimator runFrom(float startFraction2, float endFraction2) {
        if (endFraction2 < startFraction2) {
            throw new IllegalArgumentException("Animator must end after it starts.");
        }
        this.startFraction = startFraction2;
        this.endFraction = endFraction2;
        return this;
    }

    public ManualValueAnimator updateListener(UpdateListener updateListener2) {
        this.updateListener = updateListener2;
        return this;
    }

    public ManualValueAnimator interpolator(Interpolator interpolator2) {
        this.interpolator = interpolator2;
        return this;
    }

    public void setAnimatedFraction(float fraction) {
        if (!(this.startFraction == 0.0f && this.endFraction == 0.0f)) {
            if (fraction <= this.startFraction) {
                fraction = 0.0f;
            } else if (fraction >= this.endFraction) {
                fraction = 1.0f;
            } else {
                fraction = (fraction - this.startFraction) / (this.endFraction - this.startFraction);
            }
        }
        float interpolatedFraction = this.interpolator == null ? fraction : this.interpolator.getInterpolation(fraction);
        if (this.updateListener != null) {
            this.updateListener.onUpdate(fraction, this.start + ((this.end - this.start) * interpolatedFraction));
        }
    }
}
