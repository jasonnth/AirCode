package com.airbnb.android.listing.fragments;

import com.airbnb.android.core.utils.geocoder.models.AutocompletePrediction;
import com.airbnb.android.listing.adapters.AddressAutoCompleteAdapter.Listener;

final /* synthetic */ class AddressAutoCompleteFragment$$Lambda$4 implements Listener {
    private final AddressAutoCompleteFragment arg$1;

    private AddressAutoCompleteFragment$$Lambda$4(AddressAutoCompleteFragment addressAutoCompleteFragment) {
        this.arg$1 = addressAutoCompleteFragment;
    }

    public static Listener lambdaFactory$(AddressAutoCompleteFragment addressAutoCompleteFragment) {
        return new AddressAutoCompleteFragment$$Lambda$4(addressAutoCompleteFragment);
    }

    public void onAutocompletePredictionSelected(AutocompletePrediction autocompletePrediction) {
        AddressAutoCompleteFragment.lambda$new$3(this.arg$1, autocompletePrediction);
    }
}
