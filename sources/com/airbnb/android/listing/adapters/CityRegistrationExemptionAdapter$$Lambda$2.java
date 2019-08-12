package com.airbnb.android.listing.adapters;

import com.airbnb.android.core.models.ListingRegistrationProcess;
import com.airbnb.android.listing.adapters.CityRegistrationExemptionAdapter.Listener;
import com.airbnb.p027n2.interfaces.SwitchRowInterface;
import com.airbnb.p027n2.interfaces.SwitchRowInterface.OnCheckedChangeListener;

final /* synthetic */ class CityRegistrationExemptionAdapter$$Lambda$2 implements OnCheckedChangeListener {
    private final CityRegistrationExemptionAdapter arg$1;
    private final Listener arg$2;
    private final ListingRegistrationProcess arg$3;

    private CityRegistrationExemptionAdapter$$Lambda$2(CityRegistrationExemptionAdapter cityRegistrationExemptionAdapter, Listener listener, ListingRegistrationProcess listingRegistrationProcess) {
        this.arg$1 = cityRegistrationExemptionAdapter;
        this.arg$2 = listener;
        this.arg$3 = listingRegistrationProcess;
    }

    public static OnCheckedChangeListener lambdaFactory$(CityRegistrationExemptionAdapter cityRegistrationExemptionAdapter, Listener listener, ListingRegistrationProcess listingRegistrationProcess) {
        return new CityRegistrationExemptionAdapter$$Lambda$2(cityRegistrationExemptionAdapter, listener, listingRegistrationProcess);
    }

    public void onCheckedChanged(SwitchRowInterface switchRowInterface, boolean z) {
        CityRegistrationExemptionAdapter.lambda$new$1(this.arg$1, this.arg$2, this.arg$3, switchRowInterface, z);
    }
}
