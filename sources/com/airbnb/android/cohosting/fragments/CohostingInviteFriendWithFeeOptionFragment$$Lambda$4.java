package com.airbnb.android.cohosting.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class CohostingInviteFriendWithFeeOptionFragment$$Lambda$4 implements OnClickListener {
    private final CohostingInviteFriendWithFeeOptionFragment arg$1;

    private CohostingInviteFriendWithFeeOptionFragment$$Lambda$4(CohostingInviteFriendWithFeeOptionFragment cohostingInviteFriendWithFeeOptionFragment) {
        this.arg$1 = cohostingInviteFriendWithFeeOptionFragment;
    }

    public static OnClickListener lambdaFactory$(CohostingInviteFriendWithFeeOptionFragment cohostingInviteFriendWithFeeOptionFragment) {
        return new CohostingInviteFriendWithFeeOptionFragment$$Lambda$4(cohostingInviteFriendWithFeeOptionFragment);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.arg$1.dismiss();
    }
}
