package com.airbnb.android.identity;

import android.content.Intent;
import com.airbnb.android.core.identity.AccountVerificationStep;
import com.airbnb.android.core.identity.IdentityReactNativeInfoType;
import com.airbnb.android.utils.FragmentBundler;

public class IdentitySelfieInfoFragment extends IdentityBaseInfoFragment {
    public IdentitySelfieInfoFragment getInstance() {
        return (IdentitySelfieInfoFragment) FragmentBundler.make(new IdentitySelfieInfoFragment()).build();
    }

    public IdentityReactNativeInfoType getType() {
        return getVerificationFlow().getSelfieIdentityReactNativeInfoType();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0) {
            this.controller.showPreviousStep();
        } else if (resultCode == -1) {
            this.controller.onStepFinished(AccountVerificationStep.Selfie, true);
        }
    }
}
