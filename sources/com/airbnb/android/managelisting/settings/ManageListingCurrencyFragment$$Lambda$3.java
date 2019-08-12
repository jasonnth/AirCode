package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.responses.SimpleListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingCurrencyFragment$$Lambda$3 implements Action1 {
    private final ManageListingCurrencyFragment arg$1;

    private ManageListingCurrencyFragment$$Lambda$3(ManageListingCurrencyFragment manageListingCurrencyFragment) {
        this.arg$1 = manageListingCurrencyFragment;
    }

    public static Action1 lambdaFactory$(ManageListingCurrencyFragment manageListingCurrencyFragment) {
        return new ManageListingCurrencyFragment$$Lambda$3(manageListingCurrencyFragment);
    }

    public void call(Object obj) {
        ManageListingCurrencyFragment.lambda$new$3(this.arg$1, (SimpleListingResponse) obj);
    }
}
