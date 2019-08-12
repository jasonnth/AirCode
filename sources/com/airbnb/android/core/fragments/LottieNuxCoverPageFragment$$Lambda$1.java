package com.airbnb.android.core.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class LottieNuxCoverPageFragment$$Lambda$1 implements OnClickListener {
    private final LottieNuxCoverPageFragment arg$1;

    private LottieNuxCoverPageFragment$$Lambda$1(LottieNuxCoverPageFragment lottieNuxCoverPageFragment) {
        this.arg$1 = lottieNuxCoverPageFragment;
    }

    public static OnClickListener lambdaFactory$(LottieNuxCoverPageFragment lottieNuxCoverPageFragment) {
        return new LottieNuxCoverPageFragment$$Lambda$1(lottieNuxCoverPageFragment);
    }

    public void onClick(View view) {
        this.arg$1.getActivity().finish();
    }
}
