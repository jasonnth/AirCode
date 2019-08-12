package com.airbnb.android.misnap;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class MiSnapController$$Lambda$3 implements OnClickListener {
    private final MiSnapController arg$1;

    private MiSnapController$$Lambda$3(MiSnapController miSnapController) {
        this.arg$1 = miSnapController;
    }

    public static OnClickListener lambdaFactory$(MiSnapController miSnapController) {
        return new MiSnapController$$Lambda$3(miSnapController);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        MiSnapController.lambda$showConfirmationDialog$2(this.arg$1, dialogInterface, i);
    }
}
