package com.airbnb.android.listing.fragments;

import com.airbnb.android.core.utils.geocoder.models.PlaceDetailsResponse;
import p032rx.functions.Action1;

final /* synthetic */ class AddressAutoCompleteFragment$$Lambda$2 implements Action1 {
    private final AddressAutoCompleteFragment arg$1;

    private AddressAutoCompleteFragment$$Lambda$2(AddressAutoCompleteFragment addressAutoCompleteFragment) {
        this.arg$1 = addressAutoCompleteFragment;
    }

    public static Action1 lambdaFactory$(AddressAutoCompleteFragment addressAutoCompleteFragment) {
        return new AddressAutoCompleteFragment$$Lambda$2(addressAutoCompleteFragment);
    }

    public void call(Object obj) {
        AddressAutoCompleteFragment.lambda$new$1(this.arg$1, (PlaceDetailsResponse) obj);
    }
}
