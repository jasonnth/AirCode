package com.airbnb.android.listyourspacedls;

import android.animation.Animator;
import android.view.View;
import com.airbnb.p027n2.utils.ObjectAnimatorFactory.AnimatorStepListener;

final /* synthetic */ class LYSHomeActivity$1$$Lambda$2 implements AnimatorStepListener {
    private final View arg$1;
    private final int arg$2;

    private LYSHomeActivity$1$$Lambda$2(View view, int i) {
        this.arg$1 = view;
        this.arg$2 = i;
    }

    public static AnimatorStepListener lambdaFactory$(View view, int i) {
        return new LYSHomeActivity$1$$Lambda$2(view, i);
    }

    public void onAnimatorEvent(Animator animator) {
        this.arg$1.setVisibility(this.arg$2);
    }
}
