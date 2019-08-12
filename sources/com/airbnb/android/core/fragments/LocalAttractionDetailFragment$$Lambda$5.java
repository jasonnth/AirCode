package com.airbnb.android.core.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class LocalAttractionDetailFragment$$Lambda$5 implements OnClickListener {
    private final LocalAttractionDetailFragment arg$1;
    private final String arg$2;

    private LocalAttractionDetailFragment$$Lambda$5(LocalAttractionDetailFragment localAttractionDetailFragment, String str) {
        this.arg$1 = localAttractionDetailFragment;
        this.arg$2 = str;
    }

    public static OnClickListener lambdaFactory$(LocalAttractionDetailFragment localAttractionDetailFragment, String str) {
        return new LocalAttractionDetailFragment$$Lambda$5(localAttractionDetailFragment, str);
    }

    public void onClick(View view) {
        LocalAttractionDetailFragment.lambda$setupAttractionContactInfo$3(this.arg$1, this.arg$2, view);
    }
}
