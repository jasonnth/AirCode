package com.airbnb.android.apprater;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;

final /* synthetic */ class AppRaterDialogFragment$$Lambda$6 implements OnCancelListener {
    private final AppRaterDialogFragment arg$1;

    private AppRaterDialogFragment$$Lambda$6(AppRaterDialogFragment appRaterDialogFragment) {
        this.arg$1 = appRaterDialogFragment;
    }

    public static OnCancelListener lambdaFactory$(AppRaterDialogFragment appRaterDialogFragment) {
        return new AppRaterDialogFragment$$Lambda$6(appRaterDialogFragment);
    }

    public void onCancel(DialogInterface dialogInterface) {
        AppRaterDialogFragment.lambda$onCreateDialog$3(this.arg$1, dialogInterface);
    }
}
