package com.airbnb.android.identity;

import com.airbnb.android.core.explore.ChildScope;

@ChildScope
public interface IdentityComponent {

    public interface Builder {
        IdentityComponent build();
    }

    void inject(AccountVerificationPhoneNumberConfirmationFragment accountVerificationPhoneNumberConfirmationFragment);

    void inject(AccountVerificationPhoneNumberInputFragment accountVerificationPhoneNumberInputFragment);

    void inject(IdentitySelfieCaptureFragment identitySelfieCaptureFragment);
}
