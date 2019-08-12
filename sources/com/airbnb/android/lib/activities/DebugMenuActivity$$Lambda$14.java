package com.airbnb.android.lib.activities;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class DebugMenuActivity$$Lambda$14 implements OnClickListener {
    private static final DebugMenuActivity$$Lambda$14 instance = new DebugMenuActivity$$Lambda$14();

    private DebugMenuActivity$$Lambda$14() {
    }

    public static OnClickListener lambdaFactory$() {
        return instance;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        DebugMenuActivity.lambda$showVerificationFlowSelectionDialog$13(dialogInterface, i);
    }
}
