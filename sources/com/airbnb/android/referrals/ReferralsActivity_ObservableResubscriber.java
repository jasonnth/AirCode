package com.airbnb.android.referrals;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ReferralsActivity_ObservableResubscriber extends BaseObservableResubscriber {
    public ReferralsActivity_ObservableResubscriber(ReferralsActivity target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.getAssociatedGrayUsersListener, "ReferralsActivity_getAssociatedGrayUsersListener");
        group.resubscribeAll(target.getAssociatedGrayUsersListener);
        setTag((AutoTaggableObserver) target.getReferralStatusListener, "ReferralsActivity_getReferralStatusListener");
        group.resubscribeAll(target.getReferralStatusListener);
        setTag((AutoTaggableObserver) target.getUserReferralListener, "ReferralsActivity_getUserReferralListener");
        group.resubscribeAll(target.getUserReferralListener);
    }
}
