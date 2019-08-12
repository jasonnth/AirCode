package com.airbnb.android.listing.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.listing.adapters.CityRegistrationOverviewAdapter.Listener;

final /* synthetic */ class CityRegistrationOverviewAdapter$$Lambda$1 implements OnClickListener {
    private final Listener arg$1;

    private CityRegistrationOverviewAdapter$$Lambda$1(Listener listener) {
        this.arg$1 = listener;
    }

    public static OnClickListener lambdaFactory$(Listener listener) {
        return new CityRegistrationOverviewAdapter$$Lambda$1(listener);
    }

    public void onClick(View view) {
        this.arg$1.addExistingLicense();
    }
}
