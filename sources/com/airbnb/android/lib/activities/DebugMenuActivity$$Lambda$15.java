package com.airbnb.android.lib.activities;

import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import java.util.ArrayList;

final /* synthetic */ class DebugMenuActivity$$Lambda$15 implements OnMultiChoiceClickListener {
    private final ArrayList arg$1;

    private DebugMenuActivity$$Lambda$15(ArrayList arrayList) {
        this.arg$1 = arrayList;
    }

    public static OnMultiChoiceClickListener lambdaFactory$(ArrayList arrayList) {
        return new DebugMenuActivity$$Lambda$15(arrayList);
    }

    public void onClick(DialogInterface dialogInterface, int i, boolean z) {
        DebugMenuActivity.lambda$showVerificationStepSelectionDialog$14(this.arg$1, dialogInterface, i, z);
    }
}
