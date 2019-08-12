package com.airbnb.android.lib.postbooking;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class PostBookingReferralFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public PostBookingReferralFragment_ObservableResubscriber(PostBookingReferralFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.requestListener, "PostBookingReferralFragment_requestListener");
        group.resubscribeAll(target.requestListener);
    }
}
