package com.airbnb.android.login.p339ui;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

/* renamed from: com.airbnb.android.login.ui.PhoneForgotPasswordFragment_ObservableResubscriber */
public class PhoneForgotPasswordFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public PhoneForgotPasswordFragment_ObservableResubscriber(PhoneForgotPasswordFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.listener, "PhoneForgotPasswordFragment_listener");
        group.resubscribeAll(target.listener);
    }
}
