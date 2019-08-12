package com.airbnb.android.identity;

import com.airbnb.android.core.identity.AccountVerificationStep;
import p032rx.functions.Action1;

final /* synthetic */ class AccountVerificationOfflineIdController$$Lambda$1 implements Action1 {
    private final AccountVerificationOfflineIdController arg$1;

    private AccountVerificationOfflineIdController$$Lambda$1(AccountVerificationOfflineIdController accountVerificationOfflineIdController) {
        this.arg$1 = accountVerificationOfflineIdController;
    }

    public static Action1 lambdaFactory$(AccountVerificationOfflineIdController accountVerificationOfflineIdController) {
        return new AccountVerificationOfflineIdController$$Lambda$1(accountVerificationOfflineIdController);
    }

    public void call(Object obj) {
        this.arg$1.controller.onStepFinished(AccountVerificationStep.OfflineId, false);
    }
}
