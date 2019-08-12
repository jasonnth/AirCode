package com.airbnb.android.identity;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class AccountVerificationEmailInputFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public AccountVerificationEmailInputFragment_ObservableResubscriber(AccountVerificationEmailInputFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.confirmEmailRequestListener, "AccountVerificationEmailInputFragment_confirmEmailRequestListener");
        group.resubscribeAll(target.confirmEmailRequestListener);
    }
}
