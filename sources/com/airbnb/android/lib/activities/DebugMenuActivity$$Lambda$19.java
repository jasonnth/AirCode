package com.airbnb.android.lib.activities;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.airbnb.android.core.identity.IdentityReactNativeInfoType;
import com.airbnb.android.core.intents.ReactNativeIntents;

final /* synthetic */ class DebugMenuActivity$$Lambda$19 implements OnClickListener {
    private final DebugMenuActivity arg$1;
    private final String[] arg$2;
    private final int[] arg$3;

    private DebugMenuActivity$$Lambda$19(DebugMenuActivity debugMenuActivity, String[] strArr, int[] iArr) {
        this.arg$1 = debugMenuActivity;
        this.arg$2 = strArr;
        this.arg$3 = iArr;
    }

    public static OnClickListener lambdaFactory$(DebugMenuActivity debugMenuActivity, String[] strArr, int[] iArr) {
        return new DebugMenuActivity$$Lambda$19(debugMenuActivity, strArr, iArr);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.arg$1.startActivity(ReactNativeIntents.intentForVerificationInfo(this.arg$1, IdentityReactNativeInfoType.valueOf(this.arg$2[this.arg$3[0]])));
    }
}
