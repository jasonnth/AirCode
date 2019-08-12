package com.airbnb.android.listing.adapters;

import com.airbnb.android.core.models.ListingRegistrationProcess;
import com.airbnb.android.listing.adapters.CityRegistrationExemptionAdapter.Listener;
import com.airbnb.p027n2.components.InlineInputRow.OnInputChangedListener;

final /* synthetic */ class CityRegistrationExemptionAdapter$$Lambda$5 implements OnInputChangedListener {
    private final CityRegistrationExemptionAdapter arg$1;
    private final Listener arg$2;
    private final ListingRegistrationProcess arg$3;

    private CityRegistrationExemptionAdapter$$Lambda$5(CityRegistrationExemptionAdapter cityRegistrationExemptionAdapter, Listener listener, ListingRegistrationProcess listingRegistrationProcess) {
        this.arg$1 = cityRegistrationExemptionAdapter;
        this.arg$2 = listener;
        this.arg$3 = listingRegistrationProcess;
    }

    public static OnInputChangedListener lambdaFactory$(CityRegistrationExemptionAdapter cityRegistrationExemptionAdapter, Listener listener, ListingRegistrationProcess listingRegistrationProcess) {
        return new CityRegistrationExemptionAdapter$$Lambda$5(cityRegistrationExemptionAdapter, listener, listingRegistrationProcess);
    }

    public void onInputChanged(String str) {
        this.arg$2.inputIsValid(this.arg$1.allInputIsValid(this.arg$3));
    }
}
