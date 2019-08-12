package com.airbnb.android.identity;

import com.airbnb.android.core.BaseGraph;

public interface IdentityGraph extends BaseGraph {
    void inject(AccountVerificationActivity accountVerificationActivity);

    void inject(AccountVerificationProfilePhotoFragment accountVerificationProfilePhotoFragment);

    void inject(AccountVerificationSelfieConfirmFragment accountVerificationSelfieConfirmFragment);

    void inject(AccountVerificationSelfieFragment accountVerificationSelfieFragment);

    void inject(AccountVerificationStartActivity accountVerificationStartActivity);

    void inject(AirbnbTakeSelfieActivity airbnbTakeSelfieActivity);

    void inject(IdentitySelfieCaptureFragment identitySelfieCaptureFragment);
}
