package com.airbnb.android.lib.activities;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.airbnb.android.core.models.User;

final /* synthetic */ class DebugMenuActivity$$Lambda$11 implements OnClickListener {
    private final DebugMenuActivity arg$1;
    private final User arg$2;

    private DebugMenuActivity$$Lambda$11(DebugMenuActivity debugMenuActivity, User user) {
        this.arg$1 = debugMenuActivity;
        this.arg$2 = user;
    }

    public static OnClickListener lambdaFactory$(DebugMenuActivity debugMenuActivity, User user) {
        return new DebugMenuActivity$$Lambda$11(debugMenuActivity, user);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        DebugMenuActivity.lambda$onClickForceProfile$10(this.arg$1, this.arg$2, dialogInterface, i);
    }
}
