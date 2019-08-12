package com.airbnb.android.identity;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

/* renamed from: com.airbnb.android.identity.AccountVerificationEmailConfirmationFragment_ObservableResubscriber */
public class C6468x95198f64 extends BaseObservableResubscriber {
    public C6468x95198f64(AccountVerificationEmailConfirmationFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.resendEmailRequestListener, "AccountVerificationEmailConfirmationFragment_resendEmailRequestListener");
        group.resubscribeAll(target.resendEmailRequestListener);
        setTag((AutoTaggableObserver) target.emailPollingRequestListener, "AccountVerificationEmailConfirmationFragment_emailPollingRequestListener");
        group.resubscribeAll(target.emailPollingRequestListener);
    }
}
