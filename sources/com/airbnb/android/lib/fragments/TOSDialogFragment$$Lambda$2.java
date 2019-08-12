package com.airbnb.android.lib.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class TOSDialogFragment$$Lambda$2 implements OnClickListener {
    private final TOSDialogFragment arg$1;

    private TOSDialogFragment$$Lambda$2(TOSDialogFragment tOSDialogFragment) {
        this.arg$1 = tOSDialogFragment;
    }

    public static OnClickListener lambdaFactory$(TOSDialogFragment tOSDialogFragment) {
        return new TOSDialogFragment$$Lambda$2(tOSDialogFragment);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.arg$1.accept();
    }
}
