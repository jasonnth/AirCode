package com.airbnb.android.lib.activities;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class DebugMenuActivity$$Lambda$10 implements OnClickListener {
    private static final DebugMenuActivity$$Lambda$10 instance = new DebugMenuActivity$$Lambda$10();

    private DebugMenuActivity$$Lambda$10() {
    }

    public static OnClickListener lambdaFactory$() {
        return instance;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        DebugMenuActivity.lambda$showPostBookingDebugDialog$9(dialogInterface, i);
    }
}
