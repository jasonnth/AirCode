package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class LYSAddressFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public LYSAddressFragment_ObservableResubscriber(LYSAddressFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.createListingRequestListener, "LYSAddressFragment_createListingRequestListener");
        group.resubscribeAll(target.createListingRequestListener);
    }
}
