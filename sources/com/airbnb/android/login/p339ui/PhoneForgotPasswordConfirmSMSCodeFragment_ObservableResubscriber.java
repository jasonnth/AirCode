package com.airbnb.android.login.p339ui;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

/* renamed from: com.airbnb.android.login.ui.PhoneForgotPasswordConfirmSMSCodeFragment_ObservableResubscriber */
public class PhoneForgotPasswordConfirmSMSCodeFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public PhoneForgotPasswordConfirmSMSCodeFragment_ObservableResubscriber(PhoneForgotPasswordConfirmSMSCodeFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.requestListener, "PhoneForgotPasswordConfirmSMSCodeFragment_requestListener");
        group.resubscribeAll(target.requestListener);
    }
}
