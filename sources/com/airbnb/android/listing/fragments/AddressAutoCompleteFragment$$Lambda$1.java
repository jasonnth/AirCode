package com.airbnb.android.listing.fragments;

import com.airbnb.android.core.utils.geocoder.models.AutocompleteResponse;
import p032rx.functions.Action1;

final /* synthetic */ class AddressAutoCompleteFragment$$Lambda$1 implements Action1 {
    private final AddressAutoCompleteFragment arg$1;

    private AddressAutoCompleteFragment$$Lambda$1(AddressAutoCompleteFragment addressAutoCompleteFragment) {
        this.arg$1 = addressAutoCompleteFragment;
    }

    public static Action1 lambdaFactory$(AddressAutoCompleteFragment addressAutoCompleteFragment) {
        return new AddressAutoCompleteFragment$$Lambda$1(addressAutoCompleteFragment);
    }

    public void call(Object obj) {
        this.arg$1.adapter.setAutoCompleteItems(((AutocompleteResponse) obj).getPredictions());
    }
}
