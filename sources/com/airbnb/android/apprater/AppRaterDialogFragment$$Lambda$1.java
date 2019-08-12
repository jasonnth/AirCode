package com.airbnb.android.apprater;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class AppRaterDialogFragment$$Lambda$1 implements OnClickListener {
    private final AppRaterDialogFragment arg$1;

    private AppRaterDialogFragment$$Lambda$1(AppRaterDialogFragment appRaterDialogFragment) {
        this.arg$1 = appRaterDialogFragment;
    }

    public static OnClickListener lambdaFactory$(AppRaterDialogFragment appRaterDialogFragment) {
        return new AppRaterDialogFragment$$Lambda$1(appRaterDialogFragment);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        AppRaterDialogFragment.lambda$onCreateDialog$0(this.arg$1, dialogInterface, i);
    }
}
