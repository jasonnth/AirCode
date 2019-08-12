package com.airbnb.android.lib.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class SpecialOfferFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public SpecialOfferFragment_ObservableResubscriber(SpecialOfferFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.specialOfferRequestListener, "SpecialOfferFragment_specialOfferRequestListener");
        group.resubscribeAll(target.specialOfferRequestListener);
    }
}
