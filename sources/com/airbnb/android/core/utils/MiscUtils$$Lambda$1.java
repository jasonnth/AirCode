package com.airbnb.android.core.utils;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class MiscUtils$$Lambda$1 implements OnClickListener {
    private static final MiscUtils$$Lambda$1 instance = new MiscUtils$$Lambda$1();

    private MiscUtils$$Lambda$1() {
    }

    public static OnClickListener lambdaFactory$() {
        return instance;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
