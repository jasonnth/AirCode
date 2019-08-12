package com.airbnb.android.host_referrals.epoxycontrollers;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class HostReferralsEpoxyController$$Lambda$2 implements OnClickListener {
    private final HostReferralsEpoxyController arg$1;

    private HostReferralsEpoxyController$$Lambda$2(HostReferralsEpoxyController hostReferralsEpoxyController) {
        this.arg$1 = hostReferralsEpoxyController;
    }

    public static OnClickListener lambdaFactory$(HostReferralsEpoxyController hostReferralsEpoxyController) {
        return new HostReferralsEpoxyController$$Lambda$2(hostReferralsEpoxyController);
    }

    public void onClick(View view) {
        this.arg$1.listener.onTravelCreditClicked();
    }
}
