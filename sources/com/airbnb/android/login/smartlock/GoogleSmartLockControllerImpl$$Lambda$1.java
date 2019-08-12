package com.airbnb.android.login.smartlock;

import com.google.android.gms.auth.api.credentials.CredentialRequestResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;

final /* synthetic */ class GoogleSmartLockControllerImpl$$Lambda$1 implements ResultCallback {
    private final GoogleSmartLockControllerImpl arg$1;

    private GoogleSmartLockControllerImpl$$Lambda$1(GoogleSmartLockControllerImpl googleSmartLockControllerImpl) {
        this.arg$1 = googleSmartLockControllerImpl;
    }

    public static ResultCallback lambdaFactory$(GoogleSmartLockControllerImpl googleSmartLockControllerImpl) {
        return new GoogleSmartLockControllerImpl$$Lambda$1(googleSmartLockControllerImpl);
    }

    public void onResult(Result result) {
        GoogleSmartLockControllerImpl.lambda$doRequestCredentials$0(this.arg$1, (CredentialRequestResult) result);
    }
}
