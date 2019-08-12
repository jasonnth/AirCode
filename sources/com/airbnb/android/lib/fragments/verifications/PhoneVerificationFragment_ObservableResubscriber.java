package com.airbnb.android.lib.fragments.verifications;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class PhoneVerificationFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public PhoneVerificationFragment_ObservableResubscriber(PhoneVerificationFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.requestConfirmationCodeListener, "PhoneVerificationFragment_requestConfirmationCodeListener");
        setTag((AutoTaggableObserver) target.verifyConfirmationCodeListener, "PhoneVerificationFragment_verifyConfirmationCodeListener");
        setTag((AutoTaggableObserver) target.requestCallListener, "PhoneVerificationFragment_requestCallListener");
    }
}
