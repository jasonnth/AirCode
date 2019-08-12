package com.airbnb.android.lib.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.view.KeyEvent;

final /* synthetic */ class TOSDialogFragment$$Lambda$4 implements OnKeyListener {
    private final TOSDialogFragment arg$1;

    private TOSDialogFragment$$Lambda$4(TOSDialogFragment tOSDialogFragment) {
        this.arg$1 = tOSDialogFragment;
    }

    public static OnKeyListener lambdaFactory$(TOSDialogFragment tOSDialogFragment) {
        return new TOSDialogFragment$$Lambda$4(tOSDialogFragment);
    }

    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        return TOSDialogFragment.lambda$onCreateDialog$3(this.arg$1, dialogInterface, i, keyEvent);
    }
}
