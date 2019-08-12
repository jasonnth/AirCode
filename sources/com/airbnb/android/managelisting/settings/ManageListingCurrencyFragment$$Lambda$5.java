package com.airbnb.android.managelisting.settings;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ManageListingCurrencyFragment$$Lambda$5 implements OnClickListener {
    private final ManageListingCurrencyFragment arg$1;

    private ManageListingCurrencyFragment$$Lambda$5(ManageListingCurrencyFragment manageListingCurrencyFragment) {
        this.arg$1 = manageListingCurrencyFragment;
    }

    public static OnClickListener lambdaFactory$(ManageListingCurrencyFragment manageListingCurrencyFragment) {
        return new ManageListingCurrencyFragment$$Lambda$5(manageListingCurrencyFragment);
    }

    public void onClick(View view) {
        this.arg$1.makeCurrenciesRequest();
    }
}
