package com.airbnb.android.lib.activities;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.EditText;

final /* synthetic */ class DebugMenuActivity$$Lambda$4 implements OnClickListener {
    private final DebugMenuActivity arg$1;
    private final EditText arg$2;
    private final boolean[] arg$3;

    private DebugMenuActivity$$Lambda$4(DebugMenuActivity debugMenuActivity, EditText editText, boolean[] zArr) {
        this.arg$1 = debugMenuActivity;
        this.arg$2 = editText;
        this.arg$3 = zArr;
    }

    public static OnClickListener lambdaFactory$(DebugMenuActivity debugMenuActivity, EditText editText, boolean[] zArr) {
        return new DebugMenuActivity$$Lambda$4(debugMenuActivity, editText, zArr);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        DebugMenuActivity.lambda$onClickGoToExperience$3(this.arg$1, this.arg$2, this.arg$3, dialogInterface, i);
    }
}
