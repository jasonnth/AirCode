package com.airbnb.android.login.p339ui;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

/* renamed from: com.airbnb.android.login.ui.EmailForgotPasswordFragment_ObservableResubscriber */
public class EmailForgotPasswordFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public EmailForgotPasswordFragment_ObservableResubscriber(EmailForgotPasswordFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.listener, "EmailForgotPasswordFragment_listener");
        group.resubscribeAll(target.listener);
    }
}
