package com.airbnb.android.lib.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.EditText;

final /* synthetic */ class SwitchAccountDialogFragment$1$$Lambda$1 implements OnClickListener {
    private final C69011 arg$1;
    private final EditText arg$2;

    private SwitchAccountDialogFragment$1$$Lambda$1(C69011 r1, EditText editText) {
        this.arg$1 = r1;
        this.arg$2 = editText;
    }

    public static OnClickListener lambdaFactory$(C69011 r1, EditText editText) {
        return new SwitchAccountDialogFragment$1$$Lambda$1(r1, editText);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        C69011.lambda$onLoginWithToken$0(this.arg$1, this.arg$2, dialogInterface, i);
    }
}
