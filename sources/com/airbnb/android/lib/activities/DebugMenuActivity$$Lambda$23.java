package com.airbnb.android.lib.activities;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class DebugMenuActivity$$Lambda$23 implements OnClickListener {
    private static final DebugMenuActivity$$Lambda$23 instance = new DebugMenuActivity$$Lambda$23();

    private DebugMenuActivity$$Lambda$23() {
    }

    public static OnClickListener lambdaFactory$() {
        return instance;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        DebugMenuActivity.lambda$setEndpoint$22(dialogInterface, i);
    }
}
