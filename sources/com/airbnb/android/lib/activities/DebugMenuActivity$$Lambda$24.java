package com.airbnb.android.lib.activities;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.EditText;
import com.airbnb.android.lib.activities.DebugMenuActivity.OnUserInputListener;

final /* synthetic */ class DebugMenuActivity$$Lambda$24 implements OnClickListener {
    private final EditText arg$1;
    private final OnUserInputListener arg$2;

    private DebugMenuActivity$$Lambda$24(EditText editText, OnUserInputListener onUserInputListener) {
        this.arg$1 = editText;
        this.arg$2 = onUserInputListener;
    }

    public static OnClickListener lambdaFactory$(EditText editText, OnUserInputListener onUserInputListener) {
        return new DebugMenuActivity$$Lambda$24(editText, onUserInputListener);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        DebugMenuActivity.lambda$alertForPromptAndExecute$23(this.arg$1, this.arg$2, dialogInterface, i);
    }
}
