package com.airbnb.android.lib.activities;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.EditText;

final /* synthetic */ class DebugMenuActivity$$Lambda$2 implements OnClickListener {
    private final DebugMenuActivity arg$1;
    private final EditText arg$2;

    private DebugMenuActivity$$Lambda$2(DebugMenuActivity debugMenuActivity, EditText editText) {
        this.arg$1 = debugMenuActivity;
        this.arg$2 = editText;
    }

    public static OnClickListener lambdaFactory$(DebugMenuActivity debugMenuActivity, EditText editText) {
        return new DebugMenuActivity$$Lambda$2(debugMenuActivity, editText);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        DebugMenuActivity.lambda$onClickGoToListing$1(this.arg$1, this.arg$2, dialogInterface, i);
    }
}
