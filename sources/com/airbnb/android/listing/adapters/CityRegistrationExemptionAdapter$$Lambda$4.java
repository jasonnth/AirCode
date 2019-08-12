package com.airbnb.android.listing.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.listing.adapters.CityRegistrationExemptionAdapter.Listener;
import com.airbnb.android.listing.adapters.CityRegistrationExemptionAdapter.ListenerV2;

final /* synthetic */ class CityRegistrationExemptionAdapter$$Lambda$4 implements OnClickListener {
    private final Listener arg$1;

    private CityRegistrationExemptionAdapter$$Lambda$4(Listener listener) {
        this.arg$1 = listener;
    }

    public static OnClickListener lambdaFactory$(Listener listener) {
        return new CityRegistrationExemptionAdapter$$Lambda$4(listener);
    }

    public void onClick(View view) {
        ((ListenerV2) this.arg$1).showDateSelection();
    }
}
