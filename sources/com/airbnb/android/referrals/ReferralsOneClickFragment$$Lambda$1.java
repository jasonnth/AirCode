package com.airbnb.android.referrals;

import com.airbnb.android.core.models.GrayUser;
import com.airbnb.android.referrals.adapters.ReferralsPostReviewController.InviteClickListener;

final /* synthetic */ class ReferralsOneClickFragment$$Lambda$1 implements InviteClickListener {
    private final ReferralsOneClickFragment arg$1;

    private ReferralsOneClickFragment$$Lambda$1(ReferralsOneClickFragment referralsOneClickFragment) {
        this.arg$1 = referralsOneClickFragment;
    }

    public static InviteClickListener lambdaFactory$(ReferralsOneClickFragment referralsOneClickFragment) {
        return new ReferralsOneClickFragment$$Lambda$1(referralsOneClickFragment);
    }

    public void inviteButtonClicked(int i, GrayUser grayUser) {
        this.arg$1.inviteButtonClicked(i, grayUser);
    }
}
