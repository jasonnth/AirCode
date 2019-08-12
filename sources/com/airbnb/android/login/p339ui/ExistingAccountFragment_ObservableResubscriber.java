package com.airbnb.android.login.p339ui;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

/* renamed from: com.airbnb.android.login.ui.ExistingAccountFragment_ObservableResubscriber */
public class ExistingAccountFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public ExistingAccountFragment_ObservableResubscriber(ExistingAccountFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.forgotPasswordListener, "ExistingAccountFragment_forgotPasswordListener");
        group.resubscribeAll(target.forgotPasswordListener);
    }
}
