package com.airbnb.android.lib.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class RemovePreapprovalFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public RemovePreapprovalFragment_ObservableResubscriber(RemovePreapprovalFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.withdrawSpecialOfferListener, "RemovePreapprovalFragment_withdrawSpecialOfferListener");
        group.resubscribeAll(target.withdrawSpecialOfferListener);
    }
}
