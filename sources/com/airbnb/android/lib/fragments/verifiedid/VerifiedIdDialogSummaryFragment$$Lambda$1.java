package com.airbnb.android.lib.fragments.verifiedid;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class VerifiedIdDialogSummaryFragment$$Lambda$1 implements OnClickListener {
    private final VerifiedIdDialogSummaryFragment arg$1;

    private VerifiedIdDialogSummaryFragment$$Lambda$1(VerifiedIdDialogSummaryFragment verifiedIdDialogSummaryFragment) {
        this.arg$1 = verifiedIdDialogSummaryFragment;
    }

    public static OnClickListener lambdaFactory$(VerifiedIdDialogSummaryFragment verifiedIdDialogSummaryFragment) {
        return new VerifiedIdDialogSummaryFragment$$Lambda$1(verifiedIdDialogSummaryFragment);
    }

    public void onClick(View view) {
        this.arg$1.dismiss();
    }
}
