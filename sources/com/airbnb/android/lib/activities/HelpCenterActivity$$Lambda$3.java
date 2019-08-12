package com.airbnb.android.lib.activities;

import android.view.View;
import android.view.View.OnLongClickListener;
import com.airbnb.android.core.utils.MiscUtils;

final /* synthetic */ class HelpCenterActivity$$Lambda$3 implements OnLongClickListener {
    private final HelpCenterActivity arg$1;

    private HelpCenterActivity$$Lambda$3(HelpCenterActivity helpCenterActivity) {
        this.arg$1 = helpCenterActivity;
    }

    public static OnLongClickListener lambdaFactory$(HelpCenterActivity helpCenterActivity) {
        return new HelpCenterActivity$$Lambda$3(helpCenterActivity);
    }

    public boolean onLongClick(View view) {
        return MiscUtils.copyToClipboard(this.arg$1, view.getTag().toString());
    }
}
