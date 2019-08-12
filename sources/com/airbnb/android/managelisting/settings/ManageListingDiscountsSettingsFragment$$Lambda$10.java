package com.airbnb.android.managelisting.settings;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ManageListingDiscountsSettingsFragment$$Lambda$10 implements OnClickListener {
    private final ManageListingDiscountsSettingsFragment arg$1;

    private ManageListingDiscountsSettingsFragment$$Lambda$10(ManageListingDiscountsSettingsFragment manageListingDiscountsSettingsFragment) {
        this.arg$1 = manageListingDiscountsSettingsFragment;
    }

    public static OnClickListener lambdaFactory$(ManageListingDiscountsSettingsFragment manageListingDiscountsSettingsFragment) {
        return new ManageListingDiscountsSettingsFragment$$Lambda$10(manageListingDiscountsSettingsFragment);
    }

    public void onClick(View view) {
        this.arg$1.controller.actionExecutor.discountsExample();
    }
}
