package com.airbnb.android.lib.fragments.verifiedid;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class VerifiedIdCompletedFragment$$Lambda$1 implements OnClickListener {
    private final VerifiedIdCompletedFragment arg$1;

    private VerifiedIdCompletedFragment$$Lambda$1(VerifiedIdCompletedFragment verifiedIdCompletedFragment) {
        this.arg$1 = verifiedIdCompletedFragment;
    }

    public static OnClickListener lambdaFactory$(VerifiedIdCompletedFragment verifiedIdCompletedFragment) {
        return new VerifiedIdCompletedFragment$$Lambda$1(verifiedIdCompletedFragment);
    }

    public void onClick(View view) {
        VerifiedIdCompletedFragment.lambda$onCreateView$0(this.arg$1, view);
    }
}
