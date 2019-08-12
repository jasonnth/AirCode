package com.airbnb.android.core.views;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class LoopingViewPager$$Lambda$1 implements OnClickListener {
    private final LoopingViewPager arg$1;
    private final OnClickListener arg$2;

    private LoopingViewPager$$Lambda$1(LoopingViewPager loopingViewPager, OnClickListener onClickListener) {
        this.arg$1 = loopingViewPager;
        this.arg$2 = onClickListener;
    }

    public static OnClickListener lambdaFactory$(LoopingViewPager loopingViewPager, OnClickListener onClickListener) {
        return new LoopingViewPager$$Lambda$1(loopingViewPager, onClickListener);
    }

    public void onClick(View view) {
        LoopingViewPager.lambda$setViewPagerClickListener$0(this.arg$1, this.arg$2, view);
    }
}
