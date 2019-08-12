package com.airbnb.android.identity;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class IdentitySelfieReviewFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public IdentitySelfieReviewFragment_ObservableResubscriber(IdentitySelfieReviewFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.misnapSelfiePostResponseListener, "IdentitySelfieReviewFragment_misnapSelfiePostResponseListener");
        group.resubscribeAll(target.misnapSelfiePostResponseListener);
        setTag((AutoTaggableObserver) target.airbnbSelfiePostResponseListener, "IdentitySelfieReviewFragment_airbnbSelfiePostResponseListener");
        group.resubscribeAll(target.airbnbSelfiePostResponseListener);
    }
}
