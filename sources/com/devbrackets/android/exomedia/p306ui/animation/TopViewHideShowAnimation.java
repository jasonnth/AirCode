package com.devbrackets.android.exomedia.p306ui.animation;

import android.support.p000v4.view.animation.FastOutLinearInInterpolator;
import android.support.p000v4.view.animation.LinearOutSlowInInterpolator;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;

/* renamed from: com.devbrackets.android.exomedia.ui.animation.TopViewHideShowAnimation */
public class TopViewHideShowAnimation extends AnimationSet {
    /* access modifiers changed from: private */
    public View animationView;
    /* access modifiers changed from: private */
    public boolean toVisible;

    /* renamed from: com.devbrackets.android.exomedia.ui.animation.TopViewHideShowAnimation$Listener */
    private class Listener implements AnimationListener {
        private Listener() {
        }

        public void onAnimationStart(Animation animation) {
            TopViewHideShowAnimation.this.animationView.setVisibility(0);
        }

        public void onAnimationEnd(Animation animation) {
            TopViewHideShowAnimation.this.animationView.setVisibility(TopViewHideShowAnimation.this.toVisible ? 0 : 8);
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    public TopViewHideShowAnimation(View view, boolean toVisible2, long duration) {
        float startAlpha;
        int startY;
        float endAlpha = 1.0f;
        int endY = 0;
        super(false);
        this.toVisible = toVisible2;
        this.animationView = view;
        if (toVisible2) {
            startAlpha = 0.0f;
        } else {
            startAlpha = 1.0f;
        }
        if (!toVisible2) {
            endAlpha = 0.0f;
        }
        AlphaAnimation alphaAnimation = new AlphaAnimation(startAlpha, endAlpha);
        alphaAnimation.setDuration(duration);
        if (toVisible2) {
            startY = -view.getHeight();
        } else {
            startY = 0;
        }
        if (!toVisible2) {
            endY = -view.getHeight();
        }
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, (float) startY, (float) endY);
        translateAnimation.setInterpolator(toVisible2 ? new LinearOutSlowInInterpolator() : new FastOutLinearInInterpolator());
        translateAnimation.setDuration(duration);
        addAnimation(alphaAnimation);
        addAnimation(translateAnimation);
        setAnimationListener(new Listener());
    }
}
