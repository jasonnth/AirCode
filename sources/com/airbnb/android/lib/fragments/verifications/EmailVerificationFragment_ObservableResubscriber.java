package com.airbnb.android.lib.fragments.verifications;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class EmailVerificationFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public EmailVerificationFragment_ObservableResubscriber(EmailVerificationFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.requestListener, "EmailVerificationFragment_requestListener");
    }
}
