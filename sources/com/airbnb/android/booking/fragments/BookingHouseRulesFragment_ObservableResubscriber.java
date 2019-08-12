package com.airbnb.android.booking.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class BookingHouseRulesFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public BookingHouseRulesFragment_ObservableResubscriber(BookingHouseRulesFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.houseRulesResponseRequestListener, "BookingHouseRulesFragment_houseRulesResponseRequestListener");
        group.resubscribeAll(target.houseRulesResponseRequestListener);
    }
}
