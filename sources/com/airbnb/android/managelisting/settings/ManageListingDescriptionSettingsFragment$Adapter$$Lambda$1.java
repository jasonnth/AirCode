package com.airbnb.android.managelisting.settings;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.listing.utils.TextSetting;

final /* synthetic */ class ManageListingDescriptionSettingsFragment$Adapter$$Lambda$1 implements OnClickListener {
    private final Adapter arg$1;

    private ManageListingDescriptionSettingsFragment$Adapter$$Lambda$1(Adapter adapter) {
        this.arg$1 = adapter;
    }

    public static OnClickListener lambdaFactory$(Adapter adapter) {
        return new ManageListingDescriptionSettingsFragment$Adapter$$Lambda$1(adapter);
    }

    public void onClick(View view) {
        ManageListingDescriptionSettingsFragment.this.controller.actionExecutor.textSetting(TextSetting.Summary);
    }
}
