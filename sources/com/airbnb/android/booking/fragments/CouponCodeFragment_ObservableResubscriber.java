package com.airbnb.android.booking.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class CouponCodeFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public CouponCodeFragment_ObservableResubscriber(CouponCodeFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.couponListener, "CouponCodeFragment_couponListener");
        group.resubscribeAll(target.couponListener);
    }
}
