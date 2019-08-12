package com.airbnb.android.places.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class PickAddToPlansController$$Lambda$1 implements OnClickListener {
    private final PickAddToPlansController arg$1;

    private PickAddToPlansController$$Lambda$1(PickAddToPlansController pickAddToPlansController) {
        this.arg$1 = pickAddToPlansController;
    }

    public static OnClickListener lambdaFactory$(PickAddToPlansController pickAddToPlansController) {
        return new PickAddToPlansController$$Lambda$1(pickAddToPlansController);
    }

    public void onClick(View view) {
        this.arg$1.onPlanSelectedListener.onGoNow(0);
    }
}
