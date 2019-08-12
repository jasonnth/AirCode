package com.airbnb.android.lib.activities;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class VerifyEmailActivity_ObservableResubscriber extends BaseObservableResubscriber {
    public VerifyEmailActivity_ObservableResubscriber(VerifyEmailActivity target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.verifyEmailListener, "VerifyEmailActivity_verifyEmailListener");
        group.resubscribeAll(target.verifyEmailListener);
    }
}
