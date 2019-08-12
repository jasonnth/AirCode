package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class LYSCurrencyFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public LYSCurrencyFragment_ObservableResubscriber(LYSCurrencyFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.currenciesListener, "LYSCurrencyFragment_currenciesListener");
        group.resubscribeAll(target.currenciesListener);
    }
}
