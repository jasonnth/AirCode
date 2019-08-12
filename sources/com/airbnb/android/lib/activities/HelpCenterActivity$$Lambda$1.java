package com.airbnb.android.lib.activities;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.intents.HelpCenterIntents;

final /* synthetic */ class HelpCenterActivity$$Lambda$1 implements OnClickListener {
    private final HelpCenterActivity arg$1;

    private HelpCenterActivity$$Lambda$1(HelpCenterActivity helpCenterActivity) {
        this.arg$1 = helpCenterActivity;
    }

    public static OnClickListener lambdaFactory$(HelpCenterActivity helpCenterActivity) {
        return new HelpCenterActivity$$Lambda$1(helpCenterActivity);
    }

    public void onClick(View view) {
        this.arg$1.startActivity(HelpCenterIntents.intentForHelpCenterUrl(this.arg$1, HelpCenterIntents.getBaseHelpCenterUrl(this.arg$1)).toIntent());
    }
}
