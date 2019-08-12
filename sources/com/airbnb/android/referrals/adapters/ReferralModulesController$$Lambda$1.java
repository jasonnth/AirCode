package com.airbnb.android.referrals.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ReferralModulesController$$Lambda$1 implements OnClickListener {
    private final ReferralModulesController arg$1;

    private ReferralModulesController$$Lambda$1(ReferralModulesController referralModulesController) {
        this.arg$1 = referralModulesController;
    }

    public static OnClickListener lambdaFactory$(ReferralModulesController referralModulesController) {
        return new ReferralModulesController$$Lambda$1(referralModulesController);
    }

    public void onClick(View view) {
        this.arg$1.listener.onShareYourLinkClicked();
    }
}
