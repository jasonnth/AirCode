package com.airbnb.android.login.smartlock;

import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.core.models.User;
import com.airbnb.android.registration.models.AccountLoginData;
import com.google.android.gms.auth.api.credentials.Credential;

public class DummyGoogleSmartLockController implements GoogleSmartLockController {
    public void requestCredential() {
    }

    public void saveCredential(User user, AccountLoginData loginData) {
    }

    public void deleteInvalidCredential(Credential credential) {
    }

    public void onSaveInstanceState(Bundle outState) {
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    public void ignoreCredentialResponse() {
    }

    public boolean isRequestingCredential() {
        return false;
    }
}
