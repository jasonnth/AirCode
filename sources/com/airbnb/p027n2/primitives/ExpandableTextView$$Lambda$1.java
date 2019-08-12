package com.airbnb.p027n2.primitives;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

/* renamed from: com.airbnb.n2.primitives.ExpandableTextView$$Lambda$1 */
final /* synthetic */ class ExpandableTextView$$Lambda$1 implements AnimatorUpdateListener {
    private final ExpandableTextView arg$1;

    private ExpandableTextView$$Lambda$1(ExpandableTextView expandableTextView) {
        this.arg$1 = expandableTextView;
    }

    public static AnimatorUpdateListener lambdaFactory$(ExpandableTextView expandableTextView) {
        return new ExpandableTextView$$Lambda$1(expandableTextView);
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        ExpandableTextView.lambda$animateHeightChange$0(this.arg$1, valueAnimator);
    }
}
