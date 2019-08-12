package com.airbnb.android.listing.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class AddressAutoCompleteFragment$$Lambda$3 implements Action1 {
    private final AddressAutoCompleteFragment arg$1;

    private AddressAutoCompleteFragment$$Lambda$3(AddressAutoCompleteFragment addressAutoCompleteFragment) {
        this.arg$1 = addressAutoCompleteFragment;
    }

    public static Action1 lambdaFactory$(AddressAutoCompleteFragment addressAutoCompleteFragment) {
        return new AddressAutoCompleteFragment$$Lambda$3(addressAutoCompleteFragment);
    }

    public void call(Object obj) {
        AddressAutoCompleteFragment.lambda$new$2(this.arg$1, (AirRequestNetworkException) obj);
    }
}
