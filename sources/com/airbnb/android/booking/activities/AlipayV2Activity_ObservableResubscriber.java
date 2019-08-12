package com.airbnb.android.booking.activities;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class AlipayV2Activity_ObservableResubscriber extends BaseObservableResubscriber {
    public AlipayV2Activity_ObservableResubscriber(AlipayV2Activity target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.queryPaymentOptionListener, "AlipayV2Activity_queryPaymentOptionListener");
        group.resubscribeAll(target.queryPaymentOptionListener);
    }
}
