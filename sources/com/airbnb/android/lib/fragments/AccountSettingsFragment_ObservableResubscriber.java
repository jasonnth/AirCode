package com.airbnb.android.lib.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class AccountSettingsFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public AccountSettingsFragment_ObservableResubscriber(AccountSettingsFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.currencyRequestListener, "AccountSettingsFragment_currencyRequestListener");
        group.resubscribeAll(target.currencyRequestListener);
    }
}
