package com.airbnb.android.cohosting.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

/* renamed from: com.airbnb.android.cohosting.fragments.CohostingInviteFriendWithFeeOptionFragment_ObservableResubscriber */
public class C5681x7cfd206d extends BaseObservableResubscriber {
    public C5681x7cfd206d(CohostingInviteFriendWithFeeOptionFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.sendCohostInvitationListener, "CohostingInviteFriendWithFeeOptionFragment_sendCohostInvitationListener");
        group.resubscribeAll(target.sendCohostInvitationListener);
    }
}
