package com.airbnb.android.registration;

import android.support.p000v4.app.Fragment;
import com.airbnb.android.registration.models.AccountLoginData;
import com.airbnb.android.registration.models.AccountRegistrationData;
import com.airbnb.android.registration.models.AccountRegistrationStep;

public interface AccountRegistrationController {
    void onStepFinished(AccountRegistrationStep accountRegistrationStep, AccountRegistrationData accountRegistrationData);

    void showFragment(Fragment fragment, boolean z);

    void switchToLoginWithLoginData(AccountLoginData accountLoginData);
}
