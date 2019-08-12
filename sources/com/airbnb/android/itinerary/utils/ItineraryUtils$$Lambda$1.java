package com.airbnb.android.itinerary.utils;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.itinerary.controllers.ItineraryNavigationController;
import com.airbnb.android.itinerary.data.models.Suggestion;

final /* synthetic */ class ItineraryUtils$$Lambda$1 implements OnClickListener {
    private final ItineraryNavigationController arg$1;
    private final Suggestion arg$2;
    private final String arg$3;

    private ItineraryUtils$$Lambda$1(ItineraryNavigationController itineraryNavigationController, Suggestion suggestion, String str) {
        this.arg$1 = itineraryNavigationController;
        this.arg$2 = suggestion;
        this.arg$3 = str;
    }

    public static OnClickListener lambdaFactory$(ItineraryNavigationController itineraryNavigationController, Suggestion suggestion, String str) {
        return new ItineraryUtils$$Lambda$1(itineraryNavigationController, suggestion, str);
    }

    public void onClick(View view) {
        this.arg$1.navigateToSuggestion(this.arg$2, this.arg$3);
    }
}
