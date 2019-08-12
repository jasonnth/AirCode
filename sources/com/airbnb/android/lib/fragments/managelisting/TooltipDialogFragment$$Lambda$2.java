package com.airbnb.android.lib.fragments.managelisting;

import android.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class TooltipDialogFragment$$Lambda$2 implements OnClickListener {
    private final TooltipDialogFragment arg$1;
    private final AlertDialog arg$2;

    private TooltipDialogFragment$$Lambda$2(TooltipDialogFragment tooltipDialogFragment, AlertDialog alertDialog) {
        this.arg$1 = tooltipDialogFragment;
        this.arg$2 = alertDialog;
    }

    public static OnClickListener lambdaFactory$(TooltipDialogFragment tooltipDialogFragment, AlertDialog alertDialog) {
        return new TooltipDialogFragment$$Lambda$2(tooltipDialogFragment, alertDialog);
    }

    public void onClick(View view) {
        TooltipDialogFragment.lambda$onCreateDialog$1(this.arg$1, this.arg$2, view);
    }
}
