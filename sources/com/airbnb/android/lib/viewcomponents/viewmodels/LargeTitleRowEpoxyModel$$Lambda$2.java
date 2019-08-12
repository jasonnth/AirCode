package com.airbnb.android.lib.viewcomponents.viewmodels;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import com.airbnb.android.lib.viewcomponents.viewmodels.LargeTitleRowEpoxyModel.TitleFormatter;

final /* synthetic */ class LargeTitleRowEpoxyModel$$Lambda$2 implements AnimatorUpdateListener {
    private final LargeTitleRowEpoxyModel arg$1;
    private final TitleFormatter arg$2;

    private LargeTitleRowEpoxyModel$$Lambda$2(LargeTitleRowEpoxyModel largeTitleRowEpoxyModel, TitleFormatter titleFormatter) {
        this.arg$1 = largeTitleRowEpoxyModel;
        this.arg$2 = titleFormatter;
    }

    public static AnimatorUpdateListener lambdaFactory$(LargeTitleRowEpoxyModel largeTitleRowEpoxyModel, TitleFormatter titleFormatter) {
        return new LargeTitleRowEpoxyModel$$Lambda$2(largeTitleRowEpoxyModel, titleFormatter);
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        LargeTitleRowEpoxyModel.lambda$animateTitleFrom$1(this.arg$1, this.arg$2, valueAnimator);
    }
}
