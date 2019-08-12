package com.airbnb.android.lib.activities;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class DebugMenuActivity$$Lambda$12 implements OnClickListener {
    private final int[] arg$1;

    private DebugMenuActivity$$Lambda$12(int[] iArr) {
        this.arg$1 = iArr;
    }

    public static OnClickListener lambdaFactory$(int[] iArr) {
        return new DebugMenuActivity$$Lambda$12(iArr);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        DebugMenuActivity.lambda$showVerificationFlowSelectionDialog$11(this.arg$1, dialogInterface, i);
    }
}
