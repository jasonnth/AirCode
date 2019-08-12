package com.airbnb.android.places.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class PlaceActivityPDPController$$Lambda$1 implements OnClickListener {
    private final PlaceActivityPDPController arg$1;

    private PlaceActivityPDPController$$Lambda$1(PlaceActivityPDPController placeActivityPDPController) {
        this.arg$1 = placeActivityPDPController;
    }

    public static OnClickListener lambdaFactory$(PlaceActivityPDPController placeActivityPDPController) {
        return new PlaceActivityPDPController$$Lambda$1(placeActivityPDPController);
    }

    public void onClick(View view) {
        this.arg$1.adapterController.onTapResyChange();
    }
}
