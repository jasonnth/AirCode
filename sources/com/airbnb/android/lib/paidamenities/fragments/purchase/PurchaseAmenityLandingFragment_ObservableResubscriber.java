package com.airbnb.android.lib.paidamenities.fragments.purchase;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class PurchaseAmenityLandingFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public PurchaseAmenityLandingFragment_ObservableResubscriber(PurchaseAmenityLandingFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.fetchAllPaidAmenitiesRequestListener, "PurchaseAmenityLandingFragment_fetchAllPaidAmenitiesRequestListener");
        group.resubscribeAll(target.fetchAllPaidAmenitiesRequestListener);
    }
}
