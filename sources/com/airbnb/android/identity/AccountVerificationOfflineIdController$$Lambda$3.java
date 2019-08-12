package com.airbnb.android.identity;

import com.airbnb.android.core.responses.JumioCredentialsResponse;
import p032rx.functions.Action1;

final /* synthetic */ class AccountVerificationOfflineIdController$$Lambda$3 implements Action1 {
    private final AccountVerificationOfflineIdController arg$1;

    private AccountVerificationOfflineIdController$$Lambda$3(AccountVerificationOfflineIdController accountVerificationOfflineIdController) {
        this.arg$1 = accountVerificationOfflineIdController;
    }

    public static Action1 lambdaFactory$(AccountVerificationOfflineIdController accountVerificationOfflineIdController) {
        return new AccountVerificationOfflineIdController$$Lambda$3(accountVerificationOfflineIdController);
    }

    public void call(Object obj) {
        AccountVerificationOfflineIdController.lambda$new$2(this.arg$1, (JumioCredentialsResponse) obj);
    }
}
