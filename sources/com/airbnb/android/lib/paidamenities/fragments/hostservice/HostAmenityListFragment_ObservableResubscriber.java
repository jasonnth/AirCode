package com.airbnb.android.lib.paidamenities.fragments.hostservice;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class HostAmenityListFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public HostAmenityListFragment_ObservableResubscriber(HostAmenityListFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.fetchAllPaidAmenitiesRequestListener, "HostAmenityListFragment_fetchAllPaidAmenitiesRequestListener");
        group.resubscribeAll(target.fetchAllPaidAmenitiesRequestListener);
        setTag((AutoTaggableObserver) target.deletePaidAmenityRequestListener, "HostAmenityListFragment_deletePaidAmenityRequestListener");
        group.resubscribeAll(target.deletePaidAmenityRequestListener);
    }
}
