package com.airbnb.android.lib.fragments.completeprofile;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class CompleteProfilePhoneChildFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public CompleteProfilePhoneChildFragment_ObservableResubscriber(CompleteProfilePhoneChildFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.requestConfirmationCodeListener, "CompleteProfilePhoneChildFragment_requestConfirmationCodeListener");
        group.resubscribeAll(target.requestConfirmationCodeListener);
    }
}
