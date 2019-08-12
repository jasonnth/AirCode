package com.airbnb.android.listing.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class CountryFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public CountryFragment_ObservableResubscriber(CountryFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.countriesListener, "CountryFragment_countriesListener");
        group.resubscribeAll(target.countriesListener);
    }
}
