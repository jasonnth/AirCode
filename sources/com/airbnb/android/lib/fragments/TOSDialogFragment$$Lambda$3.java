package com.airbnb.android.lib.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class TOSDialogFragment$$Lambda$3 implements OnClickListener {
    private final TOSDialogFragment arg$1;

    private TOSDialogFragment$$Lambda$3(TOSDialogFragment tOSDialogFragment) {
        this.arg$1 = tOSDialogFragment;
    }

    public static OnClickListener lambdaFactory$(TOSDialogFragment tOSDialogFragment) {
        return new TOSDialogFragment$$Lambda$3(tOSDialogFragment);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.arg$1.decline();
    }
}
