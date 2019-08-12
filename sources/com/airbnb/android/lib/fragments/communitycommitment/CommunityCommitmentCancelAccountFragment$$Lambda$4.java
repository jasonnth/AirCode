package com.airbnb.android.lib.fragments.communitycommitment;

import com.airbnb.p027n2.interfaces.LinkOnClickListener;

final /* synthetic */ class CommunityCommitmentCancelAccountFragment$$Lambda$4 implements LinkOnClickListener {
    private final CommunityCommitmentCancelAccountFragment arg$1;
    private final String arg$2;

    private CommunityCommitmentCancelAccountFragment$$Lambda$4(CommunityCommitmentCancelAccountFragment communityCommitmentCancelAccountFragment, String str) {
        this.arg$1 = communityCommitmentCancelAccountFragment;
        this.arg$2 = str;
    }

    public static LinkOnClickListener lambdaFactory$(CommunityCommitmentCancelAccountFragment communityCommitmentCancelAccountFragment, String str) {
        return new CommunityCommitmentCancelAccountFragment$$Lambda$4(communityCommitmentCancelAccountFragment, str);
    }

    public void onClickLink(int i) {
        CommunityCommitmentCancelAccountFragment.lambda$setupShareFeedbackBodyRow$3(this.arg$1, this.arg$2, i);
    }
}
