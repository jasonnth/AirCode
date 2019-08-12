package com.airbnb.android.identity;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

/* renamed from: com.airbnb.android.identity.AccountVerificationPhoneNumberConfirmationFragment_ObservableResubscriber */
public class C6489x9d3e0bc9 extends BaseObservableResubscriber {
    public C6489x9d3e0bc9(AccountVerificationPhoneNumberConfirmationFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.veirfyConfirmationCodeListener, "AccountVerificationPhoneNumberConfirmationFragment_veirfyConfirmationCodeListener");
        group.resubscribeAll(target.veirfyConfirmationCodeListener);
        setTag((AutoTaggableObserver) target.requestConfirmationCodeListener, "AccountVerificationPhoneNumberConfirmationFragment_requestConfirmationCodeListener");
        group.resubscribeAll(target.requestConfirmationCodeListener);
        setTag((AutoTaggableObserver) target.requestCallListener, "AccountVerificationPhoneNumberConfirmationFragment_requestCallListener");
        group.resubscribeAll(target.requestCallListener);
    }
}
