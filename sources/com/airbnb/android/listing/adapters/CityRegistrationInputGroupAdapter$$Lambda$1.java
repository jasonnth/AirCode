package com.airbnb.android.listing.adapters;

import com.airbnb.android.core.models.ListingRegistrationQuestion;
import com.airbnb.p027n2.components.InlineInputRow.OnInputChangedListener;

final /* synthetic */ class CityRegistrationInputGroupAdapter$$Lambda$1 implements OnInputChangedListener {
    private final ListingRegistrationQuestion arg$1;

    private CityRegistrationInputGroupAdapter$$Lambda$1(ListingRegistrationQuestion listingRegistrationQuestion) {
        this.arg$1 = listingRegistrationQuestion;
    }

    public static OnInputChangedListener lambdaFactory$(ListingRegistrationQuestion listingRegistrationQuestion) {
        return new CityRegistrationInputGroupAdapter$$Lambda$1(listingRegistrationQuestion);
    }

    public void onInputChanged(String str) {
        this.arg$1.setInputAnswer(str);
    }
}
