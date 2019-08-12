package com.airbnb.android.login.smartlock;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.FragmentActivity;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.registration.models.AccountLoginData;
import com.google.android.gms.auth.api.credentials.Credential;

public interface GoogleSmartLockController {

    public static class Factory {
        public static GoogleSmartLockController create(FragmentActivity activity, GoogleSmartLockCredentialListener listener, Bundle savedInstanceState) {
            if (!MiscUtils.hasGooglePlayServices(activity) || CoreApplication.instance().isTestApplication()) {
                return new DummyGoogleSmartLockController();
            }
            return new GoogleSmartLockControllerImpl(activity, listener, savedInstanceState);
        }
    }

    public interface GoogleSmartLockCredentialListener {
        void onCredentialRetrievalCanceled();

        void onCredentialRetrievalError();

        void onCredentialRetrievalSuccess(Credential credential);
    }

    void deleteInvalidCredential(Credential credential);

    void ignoreCredentialResponse();

    boolean isRequestingCredential();

    void onActivityResult(int i, int i2, Intent intent);

    void onSaveInstanceState(Bundle bundle);

    void requestCredential();

    void saveCredential(User user, AccountLoginData accountLoginData);
}
