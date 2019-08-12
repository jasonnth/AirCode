package com.airbnb.android.lib.activities;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.utils.CallHelper;

final /* synthetic */ class HelpCenterActivity$$Lambda$2 implements OnClickListener {
    private final HelpCenterActivity arg$1;

    private HelpCenterActivity$$Lambda$2(HelpCenterActivity helpCenterActivity) {
        this.arg$1 = helpCenterActivity;
    }

    public static OnClickListener lambdaFactory$(HelpCenterActivity helpCenterActivity) {
        return new HelpCenterActivity$$Lambda$2(helpCenterActivity);
    }

    public void onClick(View view) {
        CallHelper.dial(this.arg$1, view.getTag().toString());
    }
}
