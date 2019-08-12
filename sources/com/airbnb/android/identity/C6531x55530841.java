package com.airbnb.android.identity;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

/* renamed from: com.airbnb.android.identity.FBAccountKitPhoneNumberVerificationActivity_ObservableResubscriber */
public class C6531x55530841 extends BaseObservableResubscriber {
    public C6531x55530841(FBAccountKitPhoneNumberVerificationActivity target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.phoneNumberVerificationListener, "FBAccountKitPhoneNumberVerificationActivity_phoneNumberVerificationListener");
        group.resubscribeAll(target.phoneNumberVerificationListener);
    }
}
