package com.airbnb.android.places.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.RecommendationItem;

final /* synthetic */ class PlaceActivityPDPController$$Lambda$9 implements OnClickListener {
    private final PlaceActivityPDPController arg$1;
    private final RecommendationItem arg$2;
    private final int arg$3;

    private PlaceActivityPDPController$$Lambda$9(PlaceActivityPDPController placeActivityPDPController, RecommendationItem recommendationItem, int i) {
        this.arg$1 = placeActivityPDPController;
        this.arg$2 = recommendationItem;
        this.arg$3 = i;
    }

    public static OnClickListener lambdaFactory$(PlaceActivityPDPController placeActivityPDPController, RecommendationItem recommendationItem, int i) {
        return new PlaceActivityPDPController$$Lambda$9(placeActivityPDPController, recommendationItem, i);
    }

    public void onClick(View view) {
        this.arg$1.adapterController.onTapRecommendationItem(this.arg$2, this.arg$3);
    }
}
