package com.airbnb.android.lib.services;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ListingUpdateManager_ObservableResubscriber extends BaseObservableResubscriber {
    public ListingUpdateManager_ObservableResubscriber(ListingUpdateManager target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.listingRequestListener, "ListingUpdateManager_listingRequestListener");
        group.resubscribeAll(target.listingRequestListener);
    }
}
