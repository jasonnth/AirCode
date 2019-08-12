package com.appboy.p028ui.support;

import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.TranslateAnimation;

/* renamed from: com.appboy.ui.support.AnimationUtils */
public class AnimationUtils {
    private static Interpolator sAccelerateInterpolator = new AccelerateInterpolator();
    private static Interpolator sDecelerateInterpolator = new DecelerateInterpolator();

    public static Animation createVerticalAnimation(float fromY, float toY, long duration, boolean accelerate) {
        return setAnimationParams(new TranslateAnimation(2, 0.0f, 2, 0.0f, 1, fromY, 1, toY), duration, accelerate);
    }

    public static Animation createHorizontalAnimation(float fromX, float toX, long duration, boolean accelerate) {
        return setAnimationParams(new TranslateAnimation(1, fromX, 1, toX, 2, 0.0f, 2, 0.0f), duration, accelerate);
    }

    public static Animation setAnimationParams(Animation animation, long duration, boolean accelerate) {
        animation.setDuration(duration);
        if (accelerate) {
            animation.setInterpolator(sAccelerateInterpolator);
        } else {
            animation.setInterpolator(sDecelerateInterpolator);
        }
        return animation;
    }
}
