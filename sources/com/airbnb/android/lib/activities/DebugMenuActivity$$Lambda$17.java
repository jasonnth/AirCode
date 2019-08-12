package com.airbnb.android.lib.activities;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class DebugMenuActivity$$Lambda$17 implements OnClickListener {
    private static final DebugMenuActivity$$Lambda$17 instance = new DebugMenuActivity$$Lambda$17();

    private DebugMenuActivity$$Lambda$17() {
    }

    public static OnClickListener lambdaFactory$() {
        return instance;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        DebugMenuActivity.lambda$showVerificationStepSelectionDialog$16(dialogInterface, i);
    }
}
