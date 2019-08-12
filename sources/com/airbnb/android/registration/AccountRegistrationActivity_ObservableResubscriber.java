package com.airbnb.android.registration;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class AccountRegistrationActivity_ObservableResubscriber extends BaseObservableResubscriber {
    public AccountRegistrationActivity_ObservableResubscriber(AccountRegistrationActivity target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.createAccountRequestListener, "AccountRegistrationActivity_createAccountRequestListener");
        group.resubscribeAll(target.createAccountRequestListener);
        setTag((AutoTaggableObserver) target.createSocialAccountRequestListener, "AccountRegistrationActivity_createSocialAccountRequestListener");
        group.resubscribeAll(target.createSocialAccountRequestListener);
    }
}
