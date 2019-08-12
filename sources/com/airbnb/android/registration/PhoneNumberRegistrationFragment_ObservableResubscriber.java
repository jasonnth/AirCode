package com.airbnb.android.registration;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class PhoneNumberRegistrationFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public PhoneNumberRegistrationFragment_ObservableResubscriber(PhoneNumberRegistrationFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.phoneNumberVerificationRequestListener, "PhoneNumberRegistrationFragment_phoneNumberVerificationRequestListener");
        group.resubscribeAll(target.phoneNumberVerificationRequestListener);
        setTag((AutoTaggableObserver) target.phoneNumberExistValidationRequestListener, "PhoneNumberRegistrationFragment_phoneNumberExistValidationRequestListener");
        group.resubscribeAll(target.phoneNumberExistValidationRequestListener);
    }
}
