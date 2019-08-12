package com.airbnb.android.apprater;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class AppRaterDialogFragment$$Lambda$5 implements OnClickListener {
    private final AppRaterDialogFragment arg$1;

    private AppRaterDialogFragment$$Lambda$5(AppRaterDialogFragment appRaterDialogFragment) {
        this.arg$1 = appRaterDialogFragment;
    }

    public static OnClickListener lambdaFactory$(AppRaterDialogFragment appRaterDialogFragment) {
        return new AppRaterDialogFragment$$Lambda$5(appRaterDialogFragment);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        AppRaterDialogFragment.lambda$onCreateDialog$2(this.arg$1, dialogInterface, i);
    }
}
