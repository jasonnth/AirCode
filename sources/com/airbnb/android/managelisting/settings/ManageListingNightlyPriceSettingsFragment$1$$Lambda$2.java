package com.airbnb.android.managelisting.settings;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.utils.ExternalAppUtils;

final /* synthetic */ class ManageListingNightlyPriceSettingsFragment$1$$Lambda$2 implements OnClickListener {
    private final C74351 arg$1;

    private ManageListingNightlyPriceSettingsFragment$1$$Lambda$2(C74351 r1) {
        this.arg$1 = r1;
    }

    public static OnClickListener lambdaFactory$(C74351 r1) {
        return new ManageListingNightlyPriceSettingsFragment$1$$Lambda$2(r1);
    }

    public void onClick(View view) {
        ManageListingNightlyPriceSettingsFragment.this.getActivity().startActivity(new Intent("android.intent.action.VIEW", ExternalAppUtils.getAppStoreUri(ManageListingNightlyPriceSettingsFragment.this.getContext())));
    }
}
