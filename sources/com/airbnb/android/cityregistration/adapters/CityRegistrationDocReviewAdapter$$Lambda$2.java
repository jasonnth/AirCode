package com.airbnb.android.cityregistration.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.cityregistration.adapters.CityRegistrationDocReviewAdapter.Listener;
import com.airbnb.android.core.models.ListingRegistrationQuestion;

final /* synthetic */ class CityRegistrationDocReviewAdapter$$Lambda$2 implements OnClickListener {
    private final Listener arg$1;
    private final ListingRegistrationQuestion arg$2;

    private CityRegistrationDocReviewAdapter$$Lambda$2(Listener listener, ListingRegistrationQuestion listingRegistrationQuestion) {
        this.arg$1 = listener;
        this.arg$2 = listingRegistrationQuestion;
    }

    public static OnClickListener lambdaFactory$(Listener listener, ListingRegistrationQuestion listingRegistrationQuestion) {
        return new CityRegistrationDocReviewAdapter$$Lambda$2(listener, listingRegistrationQuestion);
    }

    public void onClick(View view) {
        this.arg$1.getDocPhoto(this.arg$2);
    }
}
