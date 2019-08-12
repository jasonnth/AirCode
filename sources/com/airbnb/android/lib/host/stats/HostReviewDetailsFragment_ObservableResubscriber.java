package com.airbnb.android.lib.host.stats;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class HostReviewDetailsFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public HostReviewDetailsFragment_ObservableResubscriber(HostReviewDetailsFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.loadMoreReviewsForAllListingsListener, "HostReviewDetailsFragment_loadMoreReviewsForAllListingsListener");
        group.resubscribeAll(target.loadMoreReviewsForAllListingsListener);
    }
}
