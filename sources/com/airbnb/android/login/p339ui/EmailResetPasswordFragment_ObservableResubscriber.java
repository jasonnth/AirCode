package com.airbnb.android.login.p339ui;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

/* renamed from: com.airbnb.android.login.ui.EmailResetPasswordFragment_ObservableResubscriber */
public class EmailResetPasswordFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public EmailResetPasswordFragment_ObservableResubscriber(EmailResetPasswordFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.secretVerificationListener, "EmailResetPasswordFragment_secretVerificationListener");
        group.resubscribeAll(target.secretVerificationListener);
        setTag((AutoTaggableObserver) target.resetPasswordListener, "EmailResetPasswordFragment_resetPasswordListener");
        group.resubscribeAll(target.resetPasswordListener);
    }
}
