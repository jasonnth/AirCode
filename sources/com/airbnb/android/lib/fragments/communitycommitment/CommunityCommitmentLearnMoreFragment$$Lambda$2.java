package com.airbnb.android.lib.fragments.communitycommitment;

import com.airbnb.p027n2.interfaces.LinkOnClickListener;

final /* synthetic */ class CommunityCommitmentLearnMoreFragment$$Lambda$2 implements LinkOnClickListener {
    private final CommunityCommitmentLearnMoreFragment arg$1;
    private final String arg$2;

    private CommunityCommitmentLearnMoreFragment$$Lambda$2(CommunityCommitmentLearnMoreFragment communityCommitmentLearnMoreFragment, String str) {
        this.arg$1 = communityCommitmentLearnMoreFragment;
        this.arg$2 = str;
    }

    public static LinkOnClickListener lambdaFactory$(CommunityCommitmentLearnMoreFragment communityCommitmentLearnMoreFragment, String str) {
        return new CommunityCommitmentLearnMoreFragment$$Lambda$2(communityCommitmentLearnMoreFragment, str);
    }

    public void onClickLink(int i) {
        CommunityCommitmentLearnMoreFragment.lambda$setupShareFeedbackBodyRow$1(this.arg$1, this.arg$2, i);
    }
}
