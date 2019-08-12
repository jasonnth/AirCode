package com.airbnb.android.referrals.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.GrayUser;

final /* synthetic */ class ReferralsPostReviewController$$Lambda$2 implements OnClickListener {
    private final ReferralsPostReviewController arg$1;
    private final int arg$2;
    private final GrayUser arg$3;

    private ReferralsPostReviewController$$Lambda$2(ReferralsPostReviewController referralsPostReviewController, int i, GrayUser grayUser) {
        this.arg$1 = referralsPostReviewController;
        this.arg$2 = i;
        this.arg$3 = grayUser;
    }

    public static OnClickListener lambdaFactory$(ReferralsPostReviewController referralsPostReviewController, int i, GrayUser grayUser) {
        return new ReferralsPostReviewController$$Lambda$2(referralsPostReviewController, i, grayUser);
    }

    public void onClick(View view) {
        this.arg$1.clickListener.inviteButtonClicked(this.arg$2, this.arg$3);
    }
}
