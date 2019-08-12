package com.airbnb.android.lib.reviews.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class WriteFeedbackIntroFragment$$Lambda$1 implements OnClickListener {
    private final WriteFeedbackIntroFragment arg$1;
    private final boolean arg$2;

    private WriteFeedbackIntroFragment$$Lambda$1(WriteFeedbackIntroFragment writeFeedbackIntroFragment, boolean z) {
        this.arg$1 = writeFeedbackIntroFragment;
        this.arg$2 = z;
    }

    public static OnClickListener lambdaFactory$(WriteFeedbackIntroFragment writeFeedbackIntroFragment, boolean z) {
        return new WriteFeedbackIntroFragment$$Lambda$1(writeFeedbackIntroFragment, z);
    }

    public void onClick(View view) {
        WriteFeedbackIntroFragment.lambda$onCreateView$0(this.arg$1, this.arg$2, view);
    }
}
