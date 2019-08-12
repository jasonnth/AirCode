package com.airbnb.android.lib.share;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ShareYourTripToWechatFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public ShareYourTripToWechatFragment_ObservableResubscriber(ShareYourTripToWechatFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.reservationListener, "ShareYourTripToWechatFragment_reservationListener");
        group.resubscribeAll(target.reservationListener);
    }
}
