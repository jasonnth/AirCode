package com.airbnb.android.lib.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class RedeemGiftCardFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public RedeemGiftCardFragment_ObservableResubscriber(RedeemGiftCardFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.templateRequestListener, "RedeemGiftCardFragment_templateRequestListener");
        group.resubscribeAll(target.templateRequestListener);
        setTag((AutoTaggableObserver) target.balanceRequestListener, "RedeemGiftCardFragment_balanceRequestListener");
        group.resubscribeAll(target.balanceRequestListener);
        setTag((AutoTaggableObserver) target.claimRequestListener, "RedeemGiftCardFragment_claimRequestListener");
    }
}
