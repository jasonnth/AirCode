package com.airbnb.android.lib.payments.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class CreditCardDetailsFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public CreditCardDetailsFragment_ObservableResubscriber(CreditCardDetailsFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.brazilCepListener, "CreditCardDetailsFragment_brazilCepListener");
        group.resubscribeAll(target.brazilCepListener);
    }
}
