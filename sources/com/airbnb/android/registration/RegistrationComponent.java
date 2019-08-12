package com.airbnb.android.registration;

import com.airbnb.android.core.explore.ChildScope;

@ChildScope
public interface RegistrationComponent {

    public interface Builder {
        RegistrationComponent build();
    }

    void inject(PhoneNumberRegistrationConfirmationFragment phoneNumberRegistrationConfirmationFragment);

    void inject(PhoneNumberRegistrationFragment phoneNumberRegistrationFragment);
}
