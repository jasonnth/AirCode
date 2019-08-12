package com.airbnb.android.places.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ResyFragment$$Lambda$3 implements OnClickListener {
    private final ResyFragment arg$1;

    private ResyFragment$$Lambda$3(ResyFragment resyFragment) {
        this.arg$1 = resyFragment;
    }

    public static OnClickListener lambdaFactory$(ResyFragment resyFragment) {
        return new ResyFragment$$Lambda$3(resyFragment);
    }

    public void onClick(View view) {
        this.arg$1.resyController.goToQuickPay();
    }
}
