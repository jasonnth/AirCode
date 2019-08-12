package com.airbnb.android.lib.fragments.communitycommitment;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class CommunityCommitmentCancelAccountFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public CommunityCommitmentCancelAccountFragment_ObservableResubscriber(CommunityCommitmentCancelAccountFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.declineCommunityCommitmentListener, "CommunityCommitmentCancelAccountFragment_declineCommunityCommitmentListener");
        group.resubscribeAll(target.declineCommunityCommitmentListener);
    }
}
