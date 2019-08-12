package com.airbnb.android.lib.activities;

import com.airbnb.android.utils.CallHelper;
import com.airbnb.p027n2.interfaces.LinkOnClickListener;

final /* synthetic */ class HelpCenterActivity$$Lambda$4 implements LinkOnClickListener {
    private final HelpCenterActivity arg$1;
    private final String arg$2;

    private HelpCenterActivity$$Lambda$4(HelpCenterActivity helpCenterActivity, String str) {
        this.arg$1 = helpCenterActivity;
        this.arg$2 = str;
    }

    public static LinkOnClickListener lambdaFactory$(HelpCenterActivity helpCenterActivity, String str) {
        return new HelpCenterActivity$$Lambda$4(helpCenterActivity, str);
    }

    public void onClickLink(int i) {
        CallHelper.dial(this.arg$1, this.arg$2);
    }
}
