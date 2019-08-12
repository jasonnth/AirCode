package com.airbnb.android.lib.activities;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class DebugMenuActivity$$Lambda$21 implements OnClickListener {
    private final DebugMenuActivity arg$1;

    private DebugMenuActivity$$Lambda$21(DebugMenuActivity debugMenuActivity) {
        this.arg$1 = debugMenuActivity;
    }

    public static OnClickListener lambdaFactory$(DebugMenuActivity debugMenuActivity) {
        return new DebugMenuActivity$$Lambda$21(debugMenuActivity);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        DebugMenuActivity.lambda$onClickWriteReview$20(this.arg$1, dialogInterface, i);
    }
}
