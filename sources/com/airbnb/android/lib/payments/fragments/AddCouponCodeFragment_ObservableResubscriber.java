package com.airbnb.android.lib.payments.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class AddCouponCodeFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public AddCouponCodeFragment_ObservableResubscriber(AddCouponCodeFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.priceQuoteRequestListener, "AddCouponCodeFragment_priceQuoteRequestListener");
        group.resubscribeAll(target.priceQuoteRequestListener);
    }
}
