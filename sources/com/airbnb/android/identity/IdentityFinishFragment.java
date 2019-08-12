package com.airbnb.android.identity;

import android.content.Intent;
import com.airbnb.android.core.identity.IdentityReactNativeInfoType;
import com.airbnb.android.utils.FragmentBundler;

public class IdentityFinishFragment extends IdentityBaseInfoFragment {
    public IdentityFinishFragment getInstance() {
        return (IdentityFinishFragment) FragmentBundler.make(new IdentityFinishFragment()).build();
    }

    public IdentityReactNativeInfoType getType() {
        return getVerificationFlow().getFinishIdentityReactNativeInfoType();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Intent resultData;
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0) {
            this.controller.showPreviousStep();
        } else if (resultCode == -1) {
            if (data == null) {
                resultData = new Intent();
            } else {
                resultData = data;
            }
            resultData.putExtra("extra_verification_flow", getVerificationFlow());
            getActivity().setResult(-1, resultData);
            getActivity().finish();
        }
    }
}
