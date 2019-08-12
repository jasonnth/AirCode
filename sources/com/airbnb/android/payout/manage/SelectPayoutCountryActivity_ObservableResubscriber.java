package com.airbnb.android.payout.manage;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class SelectPayoutCountryActivity_ObservableResubscriber extends BaseObservableResubscriber {
    public SelectPayoutCountryActivity_ObservableResubscriber(SelectPayoutCountryActivity target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.countriesListener, "SelectPayoutCountryActivity_countriesListener");
        group.resubscribeAll(target.countriesListener);
    }
}
