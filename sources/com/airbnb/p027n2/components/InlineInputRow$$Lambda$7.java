package com.airbnb.p027n2.components;

import android.animation.Animator;
import com.airbnb.p027n2.utils.ObjectAnimatorFactory.AnimatorStepListener;

/* renamed from: com.airbnb.n2.components.InlineInputRow$$Lambda$7 */
final /* synthetic */ class InlineInputRow$$Lambda$7 implements AnimatorStepListener {
    private final InlineInputRow arg$1;

    private InlineInputRow$$Lambda$7(InlineInputRow inlineInputRow) {
        this.arg$1 = inlineInputRow;
    }

    public static AnimatorStepListener lambdaFactory$(InlineInputRow inlineInputRow) {
        return new InlineInputRow$$Lambda$7(inlineInputRow);
    }

    public void onAnimatorEvent(Animator animator) {
        this.arg$1.tip.setAlpha(1.0f);
    }
}
