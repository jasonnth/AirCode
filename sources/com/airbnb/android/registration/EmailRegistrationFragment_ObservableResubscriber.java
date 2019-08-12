package com.airbnb.android.registration;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class EmailRegistrationFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public EmailRegistrationFragment_ObservableResubscriber(EmailRegistrationFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.emailValidationRequestListener, "EmailRegistrationFragment_emailValidationRequestListener");
        group.resubscribeAll(target.emailValidationRequestListener);
    }
}
