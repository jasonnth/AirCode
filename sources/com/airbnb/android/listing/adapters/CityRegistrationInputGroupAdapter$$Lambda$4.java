package com.airbnb.android.listing.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.ListingRegistrationQuestion;
import com.airbnb.android.listing.adapters.CityRegistrationInputGroupAdapter.ListenerV2;

final /* synthetic */ class CityRegistrationInputGroupAdapter$$Lambda$4 implements OnClickListener {
    private final CityRegistrationInputGroupAdapter arg$1;
    private final ListingRegistrationQuestion arg$2;

    private CityRegistrationInputGroupAdapter$$Lambda$4(CityRegistrationInputGroupAdapter cityRegistrationInputGroupAdapter, ListingRegistrationQuestion listingRegistrationQuestion) {
        this.arg$1 = cityRegistrationInputGroupAdapter;
        this.arg$2 = listingRegistrationQuestion;
    }

    public static OnClickListener lambdaFactory$(CityRegistrationInputGroupAdapter cityRegistrationInputGroupAdapter, ListingRegistrationQuestion listingRegistrationQuestion) {
        return new CityRegistrationInputGroupAdapter$$Lambda$4(cityRegistrationInputGroupAdapter, listingRegistrationQuestion);
    }

    public void onClick(View view) {
        ((ListenerV2) this.arg$1.listener).showDocumentTypeSelection(this.arg$2);
    }
}
