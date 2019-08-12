package com.airbnb.android.managelisting.settings;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ManageListingCurrencyFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public ManageListingCurrencyFragment_ObservableResubscriber(ManageListingCurrencyFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.currenciesListener, "ManageListingCurrencyFragment_currenciesListener");
        group.resubscribeAll(target.currenciesListener);
        setTag((AutoTaggableObserver) target.updateListingListener, "ManageListingCurrencyFragment_updateListingListener");
        group.resubscribeAll(target.updateListingListener);
    }
}
