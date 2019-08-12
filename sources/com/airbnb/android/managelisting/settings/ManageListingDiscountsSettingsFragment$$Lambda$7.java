package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.responses.SimpleListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingDiscountsSettingsFragment$$Lambda$7 implements Action1 {
    private final ManageListingDiscountsSettingsFragment arg$1;

    private ManageListingDiscountsSettingsFragment$$Lambda$7(ManageListingDiscountsSettingsFragment manageListingDiscountsSettingsFragment) {
        this.arg$1 = manageListingDiscountsSettingsFragment;
    }

    public static Action1 lambdaFactory$(ManageListingDiscountsSettingsFragment manageListingDiscountsSettingsFragment) {
        return new ManageListingDiscountsSettingsFragment$$Lambda$7(manageListingDiscountsSettingsFragment);
    }

    public void call(Object obj) {
        ManageListingDiscountsSettingsFragment.lambda$new$6(this.arg$1, (SimpleListingResponse) obj);
    }
}
