package com.airbnb.android.core.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.beta.models.guidebook.Place;

final /* synthetic */ class LocalAttractionDetailFragment$$Lambda$3 implements OnClickListener {
    private final LocalAttractionDetailFragment arg$1;
    private final Place arg$2;

    private LocalAttractionDetailFragment$$Lambda$3(LocalAttractionDetailFragment localAttractionDetailFragment, Place place) {
        this.arg$1 = localAttractionDetailFragment;
        this.arg$2 = place;
    }

    public static OnClickListener lambdaFactory$(LocalAttractionDetailFragment localAttractionDetailFragment, Place place) {
        return new LocalAttractionDetailFragment$$Lambda$3(localAttractionDetailFragment, place);
    }

    public void onClick(View view) {
        LocalAttractionDetailFragment.lambda$setupAttractionContactInfo$1(this.arg$1, this.arg$2, view);
    }
}
