package com.airbnb.android.listing.adapters;

import com.airbnb.android.core.utils.geocoder.models.AutocompletePrediction;
import com.google.common.base.Function;

final /* synthetic */ class AddressAutoCompleteAdapter$$Lambda$1 implements Function {
    private final AddressAutoCompleteAdapter arg$1;

    private AddressAutoCompleteAdapter$$Lambda$1(AddressAutoCompleteAdapter addressAutoCompleteAdapter) {
        this.arg$1 = addressAutoCompleteAdapter;
    }

    public static Function lambdaFactory$(AddressAutoCompleteAdapter addressAutoCompleteAdapter) {
        return new AddressAutoCompleteAdapter$$Lambda$1(addressAutoCompleteAdapter);
    }

    public Object apply(Object obj) {
        return this.arg$1.buildAutoCompleteEpoxyModel((AutocompletePrediction) obj);
    }
}
