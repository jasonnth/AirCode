package com.airbnb.android.lib.activities;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class DebugMenuActivity$$Lambda$20 implements OnClickListener {
    private static final DebugMenuActivity$$Lambda$20 instance = new DebugMenuActivity$$Lambda$20();

    private DebugMenuActivity$$Lambda$20() {
    }

    public static OnClickListener lambdaFactory$() {
        return instance;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        DebugMenuActivity.lambda$onClickLaunchReactVerificationInfo$19(dialogInterface, i);
    }
}
