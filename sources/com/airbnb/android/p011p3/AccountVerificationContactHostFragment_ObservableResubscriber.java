package com.airbnb.android.p011p3;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

/* renamed from: com.airbnb.android.p3.AccountVerificationContactHostFragment_ObservableResubscriber */
public class AccountVerificationContactHostFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public AccountVerificationContactHostFragment_ObservableResubscriber(AccountVerificationContactHostFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.tripsRequestListener, "AccountVerificationContactHostFragment_tripsRequestListener");
        group.resubscribeAll(target.tripsRequestListener);
    }
}
