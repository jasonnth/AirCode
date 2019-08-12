package com.airbnb.android.lib.fragments.communitycommitment;

import android.content.Intent;
import com.airbnb.p027n2.interfaces.LinkOnClickListener;

final /* synthetic */ class CommunityCommitmentLearnMoreFragment$$Lambda$3 implements LinkOnClickListener {
    private final CommunityCommitmentLearnMoreFragment arg$1;
    private final Intent arg$2;

    private CommunityCommitmentLearnMoreFragment$$Lambda$3(CommunityCommitmentLearnMoreFragment communityCommitmentLearnMoreFragment, Intent intent) {
        this.arg$1 = communityCommitmentLearnMoreFragment;
        this.arg$2 = intent;
    }

    public static LinkOnClickListener lambdaFactory$(CommunityCommitmentLearnMoreFragment communityCommitmentLearnMoreFragment, Intent intent) {
        return new CommunityCommitmentLearnMoreFragment$$Lambda$3(communityCommitmentLearnMoreFragment, intent);
    }

    public void onClickLink(int i) {
        CommunityCommitmentLearnMoreFragment.lambda$setupGetMoreHelpInfoRow$2(this.arg$1, this.arg$2, i);
    }
}
