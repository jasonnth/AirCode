package com.airbnb.android.p011p3;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.graphics.Path;
import android.support.p000v4.view.animation.LinearOutSlowInInterpolator;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

@TargetApi(21)
/* renamed from: com.airbnb.android.p3.P3Transition */
public class P3Transition extends Visibility {
    public Animator onAppear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
        Animator initialAlpha = ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{0.0f, 0.0f});
        initialAlpha.setDuration(0);
        Animator slide = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{300.0f, 0.0f});
        slide.setStartDelay(150);
        slide.setDuration(150);
        Animator fade = ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{0.0f, 1.0f});
        fade.setStartDelay(150);
        fade.setDuration(150);
        AnimatorSet set = new AnimatorSet();
        set.setInterpolator(new LinearOutSlowInInterpolator());
        set.play(initialAlpha);
        set.play(slide);
        set.play(fade);
        return set;
    }

    public Animator onDisappear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
        Animator slide = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{0.0f, 300.0f});
        slide.setDuration(150);
        slide.setInterpolator(new LinearInterpolator());
        Animator fade = ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{1.0f, 0.0f});
        fade.setDuration(150);
        Path path = new Path();
        path.moveTo(1.0f, 1.0f);
        path.lineTo(0.9f, 0.9f);
        Animator scale = ObjectAnimator.ofFloat(view, View.SCALE_X, View.SCALE_Y, path);
        scale.setDuration(150);
        AnimatorSet set = new AnimatorSet();
        set.play(slide);
        set.play(fade);
        set.play(scale);
        return set;
    }
}
