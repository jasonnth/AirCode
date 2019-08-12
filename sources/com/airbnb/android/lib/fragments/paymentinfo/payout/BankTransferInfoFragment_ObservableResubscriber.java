package com.airbnb.android.lib.fragments.paymentinfo.payout;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class BankTransferInfoFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public BankTransferInfoFragment_ObservableResubscriber(BankTransferInfoFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.createPayoutRequestListener, "BankTransferInfoFragment_createPayoutRequestListener");
        group.resubscribeAll(target.createPayoutRequestListener);
    }
}
