package com.airbnb.android.login.smartlock;

import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

final /* synthetic */ class GoogleSmartLockControllerImpl$$Lambda$2 implements ResultCallback {
    private final GoogleSmartLockControllerImpl arg$1;
    private final Credential arg$2;

    private GoogleSmartLockControllerImpl$$Lambda$2(GoogleSmartLockControllerImpl googleSmartLockControllerImpl, Credential credential) {
        this.arg$1 = googleSmartLockControllerImpl;
        this.arg$2 = credential;
    }

    public static ResultCallback lambdaFactory$(GoogleSmartLockControllerImpl googleSmartLockControllerImpl, Credential credential) {
        return new GoogleSmartLockControllerImpl$$Lambda$2(googleSmartLockControllerImpl, credential);
    }

    public void onResult(Result result) {
        GoogleSmartLockControllerImpl.lambda$doSaveCredential$1(this.arg$1, this.arg$2, (Status) result);
    }
}
