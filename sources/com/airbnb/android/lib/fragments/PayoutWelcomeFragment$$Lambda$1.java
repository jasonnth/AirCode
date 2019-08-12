package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.enums.HelpCenterTopic;
import com.airbnb.android.core.intents.HelpCenterIntents;

final /* synthetic */ class PayoutWelcomeFragment$$Lambda$1 implements OnClickListener {
    private final PayoutWelcomeFragment arg$1;

    private PayoutWelcomeFragment$$Lambda$1(PayoutWelcomeFragment payoutWelcomeFragment) {
        this.arg$1 = payoutWelcomeFragment;
    }

    public static OnClickListener lambdaFactory$(PayoutWelcomeFragment payoutWelcomeFragment) {
        return new PayoutWelcomeFragment$$Lambda$1(payoutWelcomeFragment);
    }

    public void onClick(View view) {
        this.arg$1.startActivity(HelpCenterIntents.intentForHelpCenterTopic(this.arg$1.getContext(), HelpCenterTopic.HOST_PAYOUT).toIntent());
    }
}
