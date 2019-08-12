package com.airbnb.android.places.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ResyFragmentController$$Lambda$1 implements OnClickListener {
    private final ResyFragmentController arg$1;

    private ResyFragmentController$$Lambda$1(ResyFragmentController resyFragmentController) {
        this.arg$1 = resyFragmentController;
    }

    public static OnClickListener lambdaFactory$(ResyFragmentController resyFragmentController) {
        return new ResyFragmentController$$Lambda$1(resyFragmentController);
    }

    public void onClick(View view) {
        this.arg$1.resyController.goToDatePicker();
    }
}
