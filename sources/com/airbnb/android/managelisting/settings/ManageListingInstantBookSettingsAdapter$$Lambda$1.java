package com.airbnb.android.managelisting.settings;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ManageListingInstantBookSettingsAdapter$$Lambda$1 implements OnClickListener {
    private final Listener arg$1;

    private ManageListingInstantBookSettingsAdapter$$Lambda$1(Listener listener) {
        this.arg$1 = listener;
    }

    public static OnClickListener lambdaFactory$(Listener listener) {
        return new ManageListingInstantBookSettingsAdapter$$Lambda$1(listener);
    }

    public void onClick(View view) {
        this.arg$1.onAirbnbRequirementsSelected();
    }
}
