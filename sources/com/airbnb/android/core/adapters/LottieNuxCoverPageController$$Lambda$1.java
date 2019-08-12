package com.airbnb.android.core.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.fragments.LottieNuxViewPagerFragment;

final /* synthetic */ class LottieNuxCoverPageController$$Lambda$1 implements OnClickListener {
    private final LottieNuxCoverPageController arg$1;

    private LottieNuxCoverPageController$$Lambda$1(LottieNuxCoverPageController lottieNuxCoverPageController) {
        this.arg$1 = lottieNuxCoverPageController;
    }

    public static OnClickListener lambdaFactory$(LottieNuxCoverPageController lottieNuxCoverPageController) {
        return new LottieNuxCoverPageController$$Lambda$1(lottieNuxCoverPageController);
    }

    public void onClick(View view) {
        this.arg$1.context.startActivity(LottieNuxViewPagerFragment.intentForNuxArguments(this.arg$1.context, this.arg$1.arguments));
    }
}
