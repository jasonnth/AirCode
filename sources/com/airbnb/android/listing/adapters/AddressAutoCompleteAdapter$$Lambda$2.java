package com.airbnb.android.listing.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.utils.geocoder.models.AutocompletePrediction;

final /* synthetic */ class AddressAutoCompleteAdapter$$Lambda$2 implements OnClickListener {
    private final AddressAutoCompleteAdapter arg$1;
    private final AutocompletePrediction arg$2;

    private AddressAutoCompleteAdapter$$Lambda$2(AddressAutoCompleteAdapter addressAutoCompleteAdapter, AutocompletePrediction autocompletePrediction) {
        this.arg$1 = addressAutoCompleteAdapter;
        this.arg$2 = autocompletePrediction;
    }

    public static OnClickListener lambdaFactory$(AddressAutoCompleteAdapter addressAutoCompleteAdapter, AutocompletePrediction autocompletePrediction) {
        return new AddressAutoCompleteAdapter$$Lambda$2(addressAutoCompleteAdapter, autocompletePrediction);
    }

    public void onClick(View view) {
        this.arg$1.listener.onAutocompletePredictionSelected(this.arg$2);
    }
}
