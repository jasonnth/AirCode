package com.airbnb.android.lib.fragments.paymentinfo.payout;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class PayoutSummaryFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public PayoutSummaryFragment_ObservableResubscriber(PayoutSummaryFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.payoutsListener, "PayoutSummaryFragment_payoutsListener");
        group.resubscribeAll(target.payoutsListener);
    }
}
