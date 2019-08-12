package com.airbnb.android.photomarkupeditor;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.p000v4.view.animation.FastOutSlowInInterpolator;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import com.airbnb.android.utils.animation.SimpleAnimatorListener;
import java.util.Arrays;
import java.util.List;

public class ColorPickerAnimationManager {
    private final int ANIMATION_DURATION_MS = 50;
    /* access modifiers changed from: private */
    public final FrameLayout colorPicker;
    private final List<ObjectAnimator> colorPickerAnimators;
    private final Interpolator interpolator = new FastOutSlowInInterpolator();

    public ColorPickerAnimationManager(Context context, FrameLayout colorPicker2, View colorHof, View colorBeach, View colorBabu, View colorRausch) {
        this.colorPicker = colorPicker2;
        int hofAnimationHeight = context.getResources().getDimensionPixelSize(C0904R.dimen.photo_editor_color_picker_hof_height);
        int beachAnimationHeight = context.getResources().getDimensionPixelSize(C0904R.dimen.photo_editor_color_picker_beach_height);
        int babuAnimationHeight = context.getResources().getDimensionPixelSize(C0904R.dimen.photo_editor_color_picker_babu_height);
        int rauschAnimationHeight = context.getResources().getDimensionPixelSize(C0904R.dimen.photo_editor_color_picker_rausch_height);
        this.colorPickerAnimators = Arrays.asList(new ObjectAnimator[]{ObjectAnimator.ofFloat(colorHof, View.TRANSLATION_Y, new float[]{(float) hofAnimationHeight}), ObjectAnimator.ofFloat(colorBeach, View.TRANSLATION_Y, new float[]{(float) beachAnimationHeight}), ObjectAnimator.ofFloat(colorBabu, View.TRANSLATION_Y, new float[]{(float) babuAnimationHeight}), ObjectAnimator.ofFloat(colorRausch, View.TRANSLATION_Y, new float[]{(float) rauschAnimationHeight})});
    }

    public void animateColorPickerUp() {
        for (ObjectAnimator animator : this.colorPickerAnimators) {
            animateColorOptionUp(animator);
        }
    }

    public void animateColorPickerDown() {
        for (ObjectAnimator animator : this.colorPickerAnimators) {
            animateColorOptionDown(animator);
        }
    }

    private void animateColorOptionUp(ObjectAnimator objectAnimator) {
        objectAnimator.cancel();
        objectAnimator.setDuration(50);
        objectAnimator.setInterpolator(this.interpolator);
        objectAnimator.start();
    }

    private void animateColorOptionDown(final ObjectAnimator objectAnimator) {
        objectAnimator.cancel();
        objectAnimator.setDuration(50);
        objectAnimator.setInterpolator(this.interpolator);
        objectAnimator.addListener(new SimpleAnimatorListener() {
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                ColorPickerAnimationManager.this.colorPicker.setVisibility(8);
                objectAnimator.removeAllListeners();
            }
        });
        objectAnimator.reverse();
    }
}
