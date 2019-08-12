package com.airbnb.android.explore.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class MTLocationChinaFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public MTLocationChinaFragment_ObservableResubscriber(MTLocationChinaFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.autocompleteRequestListener, "MTLocationChinaFragment_autocompleteRequestListener");
        group.resubscribeAll(target.autocompleteRequestListener);
        setTag((AutoTaggableObserver) target.satoriRequestListener, "MTLocationChinaFragment_satoriRequestListener");
        group.resubscribeAll(target.satoriRequestListener);
        setTag((AutoTaggableObserver) target.savedSearchesRequestListener, "MTLocationChinaFragment_savedSearchesRequestListener");
        group.resubscribeAll(target.savedSearchesRequestListener);
        setTag((AutoTaggableObserver) target.popularDestinationRequestListener, "MTLocationChinaFragment_popularDestinationRequestListener");
        group.resubscribeAll(target.popularDestinationRequestListener);
    }
}
