package com.airbnb.android.places.fragments;

import com.airbnb.android.places.ResyController.ResyUpdateListener;
import com.airbnb.android.places.ResyState;

final /* synthetic */ class ResyFragment$$Lambda$2 implements ResyUpdateListener {
    private final ResyFragment arg$1;

    private ResyFragment$$Lambda$2(ResyFragment resyFragment) {
        this.arg$1 = resyFragment;
    }

    public static ResyUpdateListener lambdaFactory$(ResyFragment resyFragment) {
        return new ResyFragment$$Lambda$2(resyFragment);
    }

    public void onContentUpdated(ResyState resyState) {
        this.arg$1.setResyState(resyState);
    }
}
