package com.airbnb.android.host_referrals.activities;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class HostReferralsActivity_ObservableResubscriber extends BaseObservableResubscriber {
    public HostReferralsActivity_ObservableResubscriber(HostReferralsActivity target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.getHostReferralsResponseRequestListener, "HostReferralsActivity_getHostReferralsResponseRequestListener");
        group.resubscribeAll(target.getHostReferralsResponseRequestListener);
        setTag((AutoTaggableObserver) target.getHostReferralInfoResponseRequestListener, "HostReferralsActivity_getHostReferralInfoResponseRequestListener");
        group.resubscribeAll(target.getHostReferralInfoResponseRequestListener);
    }
}
