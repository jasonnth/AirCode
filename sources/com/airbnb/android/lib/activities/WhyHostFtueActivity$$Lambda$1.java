package com.airbnb.android.lib.activities;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class WhyHostFtueActivity$$Lambda$1 implements OnClickListener {
    private final WhyHostFtueActivity arg$1;

    private WhyHostFtueActivity$$Lambda$1(WhyHostFtueActivity whyHostFtueActivity) {
        this.arg$1 = whyHostFtueActivity;
    }

    public static OnClickListener lambdaFactory$(WhyHostFtueActivity whyHostFtueActivity) {
        return new WhyHostFtueActivity$$Lambda$1(whyHostFtueActivity);
    }

    public void onClick(View view) {
        WhyHostFtueActivity.lambda$onCreate$0(this.arg$1, view);
    }
}
