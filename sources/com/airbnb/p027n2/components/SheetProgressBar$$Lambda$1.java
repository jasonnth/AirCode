package com.airbnb.p027n2.components;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

/* renamed from: com.airbnb.n2.components.SheetProgressBar$$Lambda$1 */
final /* synthetic */ class SheetProgressBar$$Lambda$1 implements AnimatorUpdateListener {
    private final SheetProgressBar arg$1;

    private SheetProgressBar$$Lambda$1(SheetProgressBar sheetProgressBar) {
        this.arg$1 = sheetProgressBar;
    }

    public static AnimatorUpdateListener lambdaFactory$(SheetProgressBar sheetProgressBar) {
        return new SheetProgressBar$$Lambda$1(sheetProgressBar);
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        SheetProgressBar.lambda$setProgress$0(this.arg$1, valueAnimator);
    }
}
