package com.airbnb.android.lib.activities;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class DebugActivityPDPActivity$DebugActivityPDPController$$Lambda$2 implements OnClickListener {
    private final DebugActivityPDPController arg$1;
    private final int arg$2;

    private DebugActivityPDPActivity$DebugActivityPDPController$$Lambda$2(DebugActivityPDPController debugActivityPDPController, int i) {
        this.arg$1 = debugActivityPDPController;
        this.arg$2 = i;
    }

    public static OnClickListener lambdaFactory$(DebugActivityPDPController debugActivityPDPController, int i) {
        return new DebugActivityPDPActivity$DebugActivityPDPController$$Lambda$2(debugActivityPDPController, i);
    }

    public void onClick(View view) {
        DebugActivityPDPActivity.this.goToPlaceActivity((long) this.arg$2);
    }
}
