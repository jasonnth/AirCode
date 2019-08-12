package com.airbnb.android.lib.fragments.find;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ListingSelectionFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public ListingSelectionFragment_ObservableResubscriber(ListingSelectionFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.listingRequestListener, "ListingSelectionFragment_listingRequestListener");
        group.resubscribeAll(target.listingRequestListener);
    }
}
