package com.airbnb.android.explore.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class MTLocationFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public MTLocationFragment_ObservableResubscriber(MTLocationFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.autocompleteRequestListener, "MTLocationFragment_autocompleteRequestListener");
        group.resubscribeAll(target.autocompleteRequestListener);
        setTag((AutoTaggableObserver) target.satoriRequestListener, "MTLocationFragment_satoriRequestListener");
        group.resubscribeAll(target.satoriRequestListener);
        setTag((AutoTaggableObserver) target.savedSearchesRequestListener, "MTLocationFragment_savedSearchesRequestListener");
        group.resubscribeAll(target.savedSearchesRequestListener);
        setTag((AutoTaggableObserver) target.recommendedLocationsRequestListener, "MTLocationFragment_recommendedLocationsRequestListener");
        group.resubscribeAll(target.recommendedLocationsRequestListener);
    }
}
