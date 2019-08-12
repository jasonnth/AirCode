package com.airbnb.android.core.views;

final /* synthetic */ class LoopingViewPager$1$$Lambda$1 implements Runnable {
    private final C62971 arg$1;

    private LoopingViewPager$1$$Lambda$1(C62971 r1) {
        this.arg$1 = r1;
    }

    public static Runnable lambdaFactory$(C62971 r1) {
        return new LoopingViewPager$1$$Lambda$1(r1);
    }

    public void run() {
        C62971.lambda$hideViewPagerAndScroll$0(this.arg$1);
    }
}
