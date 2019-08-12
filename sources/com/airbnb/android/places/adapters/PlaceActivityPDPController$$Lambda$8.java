package com.airbnb.android.places.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.PlaceActivity;

final /* synthetic */ class PlaceActivityPDPController$$Lambda$8 implements OnClickListener {
    private final PlaceActivityPDPController arg$1;
    private final PlaceActivity arg$2;

    private PlaceActivityPDPController$$Lambda$8(PlaceActivityPDPController placeActivityPDPController, PlaceActivity placeActivity) {
        this.arg$1 = placeActivityPDPController;
        this.arg$2 = placeActivity;
    }

    public static OnClickListener lambdaFactory$(PlaceActivityPDPController placeActivityPDPController, PlaceActivity placeActivity) {
        return new PlaceActivityPDPController$$Lambda$8(placeActivityPDPController, placeActivity);
    }

    public void onClick(View view) {
        this.arg$1.adapterController.goToFeedback(this.arg$2);
    }
}
