package com.airbnb.android.core.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ProgressDialogFragment$$Lambda$1 implements OnClickListener {
    private final ProgressDialogFragment arg$1;

    private ProgressDialogFragment$$Lambda$1(ProgressDialogFragment progressDialogFragment) {
        this.arg$1 = progressDialogFragment;
    }

    public static OnClickListener lambdaFactory$(ProgressDialogFragment progressDialogFragment) {
        return new ProgressDialogFragment$$Lambda$1(progressDialogFragment);
    }

    public void onClick(View view) {
        ProgressDialogFragment.lambda$setupDualButtonsIfNeeded$0(this.arg$1, view);
    }
}
