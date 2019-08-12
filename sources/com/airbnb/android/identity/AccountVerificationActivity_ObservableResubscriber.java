package com.airbnb.android.identity;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class AccountVerificationActivity_ObservableResubscriber extends BaseObservableResubscriber {
    public AccountVerificationActivity_ObservableResubscriber(AccountVerificationActivity target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.verifyConfirmationCodeListener, "AccountVerificationActivity_verifyConfirmationCodeListener");
        group.resubscribeAll(target.verifyConfirmationCodeListener);
    }
}
