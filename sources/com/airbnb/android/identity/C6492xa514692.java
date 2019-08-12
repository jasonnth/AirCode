package com.airbnb.android.identity;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

/* renamed from: com.airbnb.android.identity.AccountVerificationPhoneNumberInputFragment_ObservableResubscriber */
public class C6492xa514692 extends BaseObservableResubscriber {
    public C6492xa514692(AccountVerificationPhoneNumberInputFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.requestConfirmationCodeListener, "AccountVerificationPhoneNumberInputFragment_requestConfirmationCodeListener");
        group.resubscribeAll(target.requestConfirmationCodeListener);
    }
}
