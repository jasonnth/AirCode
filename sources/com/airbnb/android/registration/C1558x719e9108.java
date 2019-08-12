package com.airbnb.android.registration;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

/* renamed from: com.airbnb.android.registration.PhoneNumberRegistrationConfirmationFragment_ObservableResubscriber */
public class C1558x719e9108 extends BaseObservableResubscriber {
    public C1558x719e9108(PhoneNumberRegistrationConfirmationFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.phoneSMScodeMatchingRequestListener, "PhoneNumberRegistrationConfirmationFragment_phoneSMScodeMatchingRequestListener");
        group.resubscribeAll(target.phoneSMScodeMatchingRequestListener);
    }
}
