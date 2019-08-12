package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class SouthKoreanCancellationPolicyFragment$$Lambda$1 implements OnClickListener {
    private final SouthKoreanCancellationPolicyFragment arg$1;

    private SouthKoreanCancellationPolicyFragment$$Lambda$1(SouthKoreanCancellationPolicyFragment southKoreanCancellationPolicyFragment) {
        this.arg$1 = southKoreanCancellationPolicyFragment;
    }

    public static OnClickListener lambdaFactory$(SouthKoreanCancellationPolicyFragment southKoreanCancellationPolicyFragment) {
        return new SouthKoreanCancellationPolicyFragment$$Lambda$1(southKoreanCancellationPolicyFragment);
    }

    public void onClick(View view) {
        this.arg$1.goToSouthKoreanCancellationPolicy();
    }
}
