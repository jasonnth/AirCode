package com.airbnb.android.lib.fragments.paymentinfo.payout;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class PayoutAddressFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public PayoutAddressFragment_ObservableResubscriber(PayoutAddressFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.payoutInfoTypesListener, "PayoutAddressFragment_payoutInfoTypesListener");
        group.resubscribeAll(target.payoutInfoTypesListener);
    }
}
