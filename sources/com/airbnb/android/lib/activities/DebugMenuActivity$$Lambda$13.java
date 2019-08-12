package com.airbnb.android.lib.activities;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.airbnb.android.core.enums.VerificationFlow;
import java.util.ArrayList;

final /* synthetic */ class DebugMenuActivity$$Lambda$13 implements OnClickListener {
    private final DebugMenuActivity arg$1;
    private final ArrayList arg$2;
    private final int[] arg$3;

    private DebugMenuActivity$$Lambda$13(DebugMenuActivity debugMenuActivity, ArrayList arrayList, int[] iArr) {
        this.arg$1 = debugMenuActivity;
        this.arg$2 = arrayList;
        this.arg$3 = iArr;
    }

    public static OnClickListener lambdaFactory$(DebugMenuActivity debugMenuActivity, ArrayList arrayList, int[] iArr) {
        return new DebugMenuActivity$$Lambda$13(debugMenuActivity, arrayList, iArr);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.arg$1.showVerificationStepSelectionDialog(VerificationFlow.getVerificationFlowFromString((String) this.arg$2.get(this.arg$3[0])));
    }
}
