package com.airbnb.android.lib.activities;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.intents.AccountVerificationStartActivityIntents;
import java.util.ArrayList;

final /* synthetic */ class DebugMenuActivity$$Lambda$16 implements OnClickListener {
    private final DebugMenuActivity arg$1;
    private final VerificationFlow arg$2;
    private final ArrayList arg$3;

    private DebugMenuActivity$$Lambda$16(DebugMenuActivity debugMenuActivity, VerificationFlow verificationFlow, ArrayList arrayList) {
        this.arg$1 = debugMenuActivity;
        this.arg$2 = verificationFlow;
        this.arg$3 = arrayList;
    }

    public static OnClickListener lambdaFactory$(DebugMenuActivity debugMenuActivity, VerificationFlow verificationFlow, ArrayList arrayList) {
        return new DebugMenuActivity$$Lambda$16(debugMenuActivity, verificationFlow, arrayList);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.arg$1.startActivity(AccountVerificationStartActivityIntents.newIntentForDebug(this.arg$1, this.arg$2, this.arg$3));
    }
}
