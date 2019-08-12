package com.airbnb.android.login.smartlock;

import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

final /* synthetic */ class GoogleSmartLockControllerImpl$$Lambda$3 implements ResultCallback {
    private final Credential arg$1;

    private GoogleSmartLockControllerImpl$$Lambda$3(Credential credential) {
        this.arg$1 = credential;
    }

    public static ResultCallback lambdaFactory$(Credential credential) {
        return new GoogleSmartLockControllerImpl$$Lambda$3(credential);
    }

    public void onResult(Result result) {
        GoogleSmartLockControllerImpl.lambda$doDeleteInvalidCredential$2(this.arg$1, (Status) result);
    }
}
