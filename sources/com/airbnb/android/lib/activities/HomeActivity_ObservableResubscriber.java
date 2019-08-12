package com.airbnb.android.lib.activities;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class HomeActivity_ObservableResubscriber extends BaseObservableResubscriber {
    public HomeActivity_ObservableResubscriber(HomeActivity target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.activeAccountListener, "HomeActivity_activeAccountListener");
        group.resubscribeAll(target.activeAccountListener);
        setTag((AutoTaggableObserver) target.currencyRequestListener, "HomeActivity_currencyRequestListener");
        group.resubscribeAll(target.currencyRequestListener);
        setTag((AutoTaggableObserver) target.travelCouponListener, "HomeActivity_travelCouponListener");
        group.resubscribeAll(target.travelCouponListener);
        setTag((AutoTaggableObserver) target.couponClaimResponseRequestListener, "HomeActivity_couponClaimResponseRequestListener");
        group.resubscribeAll(target.couponClaimResponseRequestListener);
    }
}
