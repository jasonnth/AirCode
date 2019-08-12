package com.airbnb.android.lib.fragments.communitycommitment;

import android.content.Intent;
import com.airbnb.p027n2.interfaces.LinkOnClickListener;

final /* synthetic */ class CommunityCommitmentFragment$$Lambda$3 implements LinkOnClickListener {
    private final CommunityCommitmentFragment arg$1;
    private final Intent arg$2;

    private CommunityCommitmentFragment$$Lambda$3(CommunityCommitmentFragment communityCommitmentFragment, Intent intent) {
        this.arg$1 = communityCommitmentFragment;
        this.arg$2 = intent;
    }

    public static LinkOnClickListener lambdaFactory$(CommunityCommitmentFragment communityCommitmentFragment, Intent intent) {
        return new CommunityCommitmentFragment$$Lambda$3(communityCommitmentFragment, intent);
    }

    public void onClickLink(int i) {
        this.arg$1.startActivity(this.arg$2);
    }
}
