package com.airbnb.android.listyourspacedls;

import android.animation.Animator;
import android.view.View;
import com.airbnb.p027n2.utils.ObjectAnimatorFactory.AnimatorStepListener;

final /* synthetic */ class LYSHomeActivity$1$$Lambda$1 implements AnimatorStepListener {
    private final View arg$1;

    private LYSHomeActivity$1$$Lambda$1(View view) {
        this.arg$1 = view;
    }

    public static AnimatorStepListener lambdaFactory$(View view) {
        return new LYSHomeActivity$1$$Lambda$1(view);
    }

    public void onAnimatorEvent(Animator animator) {
        this.arg$1.setVisibility(0);
    }
}
