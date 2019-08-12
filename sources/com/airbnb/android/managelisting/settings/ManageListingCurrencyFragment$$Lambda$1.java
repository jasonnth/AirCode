package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.requests.CurrenciesRequest;
import com.airbnb.android.core.responses.CurrenciesResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingCurrencyFragment$$Lambda$1 implements Action1 {
    private final ManageListingCurrencyFragment arg$1;

    private ManageListingCurrencyFragment$$Lambda$1(ManageListingCurrencyFragment manageListingCurrencyFragment) {
        this.arg$1 = manageListingCurrencyFragment;
    }

    public static Action1 lambdaFactory$(ManageListingCurrencyFragment manageListingCurrencyFragment) {
        return new ManageListingCurrencyFragment$$Lambda$1(manageListingCurrencyFragment);
    }

    public void call(Object obj) {
        this.arg$1.adapter.setCurrencyOptions(CurrenciesRequest.filterIfIndianRegion(((CurrenciesResponse) obj).currencies));
    }
}
