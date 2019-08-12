package com.airbnb.android.lib.activities;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.EditText;

final /* synthetic */ class DebugMenuActivity$1$$Lambda$1 implements OnClickListener {
    private final C65861 arg$1;
    private final EditText arg$2;

    private DebugMenuActivity$1$$Lambda$1(C65861 r1, EditText editText) {
        this.arg$1 = r1;
        this.arg$2 = editText;
    }

    public static OnClickListener lambdaFactory$(C65861 r1, EditText editText) {
        return new DebugMenuActivity$1$$Lambda$1(r1, editText);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        C65861.lambda$onCustomEndpointSelected$0(this.arg$1, this.arg$2, dialogInterface, i);
    }
}
