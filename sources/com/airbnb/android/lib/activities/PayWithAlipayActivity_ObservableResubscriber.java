package com.airbnb.android.lib.activities;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class PayWithAlipayActivity_ObservableResubscriber extends BaseObservableResubscriber {
    public PayWithAlipayActivity_ObservableResubscriber(PayWithAlipayActivity target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.reservationStatusRequestListener, "PayWithAlipayActivity_reservationStatusRequestListener");
    }
}
