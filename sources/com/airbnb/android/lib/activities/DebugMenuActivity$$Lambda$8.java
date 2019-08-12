package com.airbnb.android.lib.activities;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class DebugMenuActivity$$Lambda$8 implements OnClickListener {
    private final int[] arg$1;

    private DebugMenuActivity$$Lambda$8(int[] iArr) {
        this.arg$1 = iArr;
    }

    public static OnClickListener lambdaFactory$(int[] iArr) {
        return new DebugMenuActivity$$Lambda$8(iArr);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        DebugMenuActivity.lambda$showPostBookingDebugDialog$7(this.arg$1, dialogInterface, i);
    }
}
