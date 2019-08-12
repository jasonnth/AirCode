package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.responses.CalendarPricingSettingsResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingDiscountsSettingsFragment$$Lambda$4 implements Action1 {
    private final ManageListingDiscountsSettingsFragment arg$1;

    private ManageListingDiscountsSettingsFragment$$Lambda$4(ManageListingDiscountsSettingsFragment manageListingDiscountsSettingsFragment) {
        this.arg$1 = manageListingDiscountsSettingsFragment;
    }

    public static Action1 lambdaFactory$(ManageListingDiscountsSettingsFragment manageListingDiscountsSettingsFragment) {
        return new ManageListingDiscountsSettingsFragment$$Lambda$4(manageListingDiscountsSettingsFragment);
    }

    public void call(Object obj) {
        ManageListingDiscountsSettingsFragment.lambda$new$3(this.arg$1, (CalendarPricingSettingsResponse) obj);
    }
}
