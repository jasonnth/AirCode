package com.airbnb.android.identity;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class AccountVerificationOfflineIdController$$Lambda$4 implements Action1 {
    private final AccountVerificationOfflineIdController arg$1;

    private AccountVerificationOfflineIdController$$Lambda$4(AccountVerificationOfflineIdController accountVerificationOfflineIdController) {
        this.arg$1 = accountVerificationOfflineIdController;
    }

    public static Action1 lambdaFactory$(AccountVerificationOfflineIdController accountVerificationOfflineIdController) {
        return new AccountVerificationOfflineIdController$$Lambda$4(accountVerificationOfflineIdController);
    }

    public void call(Object obj) {
        AccountVerificationOfflineIdController.lambda$new$3(this.arg$1, (AirRequestNetworkException) obj);
    }
}
