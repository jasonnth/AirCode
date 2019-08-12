package com.airbnb.android.identity;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class AccountVerificationOfflineIdController$$Lambda$2 implements Action1 {
    private final AccountVerificationOfflineIdController arg$1;

    private AccountVerificationOfflineIdController$$Lambda$2(AccountVerificationOfflineIdController accountVerificationOfflineIdController) {
        this.arg$1 = accountVerificationOfflineIdController;
    }

    public static Action1 lambdaFactory$(AccountVerificationOfflineIdController accountVerificationOfflineIdController) {
        return new AccountVerificationOfflineIdController$$Lambda$2(accountVerificationOfflineIdController);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.view, (AirRequestNetworkException) obj);
    }
}
