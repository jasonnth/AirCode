package com.airbnb.android.lib.fragments.communitycommitment;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class CommunityCommitmentFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public CommunityCommitmentFragment_ObservableResubscriber(CommunityCommitmentFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.acceptCommunityCommitmentListener, "CommunityCommitmentFragment_acceptCommunityCommitmentListener");
        group.resubscribeAll(target.acceptCommunityCommitmentListener);
    }
}
