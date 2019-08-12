package com.airbnb.android.places.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.beta.models.guidebook.Place;

final /* synthetic */ class PlaceActivityPDPController$$Lambda$7 implements OnClickListener {
    private final PlaceActivityPDPController arg$1;
    private final Place arg$2;

    private PlaceActivityPDPController$$Lambda$7(PlaceActivityPDPController placeActivityPDPController, Place place) {
        this.arg$1 = placeActivityPDPController;
        this.arg$2 = place;
    }

    public static OnClickListener lambdaFactory$(PlaceActivityPDPController placeActivityPDPController, Place place) {
        return new PlaceActivityPDPController$$Lambda$7(placeActivityPDPController, place);
    }

    public void onClick(View view) {
        this.arg$1.adapterController.goToPlaceWebsite(this.arg$2.getName(), this.arg$2.getWebsite());
    }
}
