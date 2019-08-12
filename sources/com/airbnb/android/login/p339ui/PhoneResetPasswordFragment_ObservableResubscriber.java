package com.airbnb.android.login.p339ui;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

/* renamed from: com.airbnb.android.login.ui.PhoneResetPasswordFragment_ObservableResubscriber */
public class PhoneResetPasswordFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public PhoneResetPasswordFragment_ObservableResubscriber(PhoneResetPasswordFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.requestlistener, "PhoneResetPasswordFragment_requestlistener");
        group.resubscribeAll(target.requestlistener);
    }
}
