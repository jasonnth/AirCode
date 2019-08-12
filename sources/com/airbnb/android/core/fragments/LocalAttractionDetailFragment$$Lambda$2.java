package com.airbnb.android.core.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class LocalAttractionDetailFragment$$Lambda$2 implements OnClickListener {
    private final LocalAttractionDetailFragment arg$1;

    private LocalAttractionDetailFragment$$Lambda$2(LocalAttractionDetailFragment localAttractionDetailFragment) {
        this.arg$1 = localAttractionDetailFragment;
    }

    public static OnClickListener lambdaFactory$(LocalAttractionDetailFragment localAttractionDetailFragment) {
        return new LocalAttractionDetailFragment$$Lambda$2(localAttractionDetailFragment);
    }

    public void onClick(View view) {
        LocalAttractionDetailFragment.lambda$setupHoursInfo$0(this.arg$1, view);
    }
}
