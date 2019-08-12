package com.airbnb.android.identity;

import com.airbnb.android.core.analytics.AccountVerificationAnalytics;
import com.airbnb.android.core.fragments.NavigationTag;
import p032rx.functions.Action1;

final /* synthetic */ class AccountVerificationActivity$$Lambda$1 implements Action1 {
    private static final AccountVerificationActivity$$Lambda$1 instance = new AccountVerificationActivity$$Lambda$1();

    private AccountVerificationActivity$$Lambda$1() {
    }

    public static Action1 lambdaFactory$() {
        return instance;
    }

    public void call(Object obj) {
        AccountVerificationAnalytics.trackRequestSuccess(NavigationTag.VerificationConfirmPhoneCode, "deep_link_confirm_code_request");
    }
}
