package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.core.requests.CurrenciesRequest;
import com.airbnb.android.core.responses.CurrenciesResponse;
import p032rx.functions.Action1;

final /* synthetic */ class LYSCurrencyFragment$$Lambda$1 implements Action1 {
    private final LYSCurrencyFragment arg$1;

    private LYSCurrencyFragment$$Lambda$1(LYSCurrencyFragment lYSCurrencyFragment) {
        this.arg$1 = lYSCurrencyFragment;
    }

    public static Action1 lambdaFactory$(LYSCurrencyFragment lYSCurrencyFragment) {
        return new LYSCurrencyFragment$$Lambda$1(lYSCurrencyFragment);
    }

    public void call(Object obj) {
        this.arg$1.adapter.setCurrencyOptions(CurrenciesRequest.filterIfIndianRegion(((CurrenciesResponse) obj).currencies));
    }
}
