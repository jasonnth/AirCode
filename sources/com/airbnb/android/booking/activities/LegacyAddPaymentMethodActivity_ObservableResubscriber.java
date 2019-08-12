package com.airbnb.android.booking.activities;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class LegacyAddPaymentMethodActivity_ObservableResubscriber extends BaseObservableResubscriber {
    public LegacyAddPaymentMethodActivity_ObservableResubscriber(LegacyAddPaymentMethodActivity target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.createPaymentInstrumentListener, "LegacyAddPaymentMethodActivity_createPaymentInstrumentListener");
        group.resubscribeAll(target.createPaymentInstrumentListener);
    }
}
