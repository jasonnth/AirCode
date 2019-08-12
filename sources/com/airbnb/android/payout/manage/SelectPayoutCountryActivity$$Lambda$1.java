package com.airbnb.android.payout.manage;

import com.airbnb.android.core.responses.CountriesResponse;
import p032rx.functions.Action1;

final /* synthetic */ class SelectPayoutCountryActivity$$Lambda$1 implements Action1 {
    private final SelectPayoutCountryActivity arg$1;

    private SelectPayoutCountryActivity$$Lambda$1(SelectPayoutCountryActivity selectPayoutCountryActivity) {
        this.arg$1 = selectPayoutCountryActivity;
    }

    public static Action1 lambdaFactory$(SelectPayoutCountryActivity selectPayoutCountryActivity) {
        return new SelectPayoutCountryActivity$$Lambda$1(selectPayoutCountryActivity);
    }

    public void call(Object obj) {
        SelectPayoutCountryActivity.lambda$new$1(this.arg$1, (CountriesResponse) obj);
    }
}
