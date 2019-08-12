package com.airbnb.android.core.views;

import com.airbnb.p027n2.utils.AutoScrollingController.Target;

final /* synthetic */ class ClickableViewPager$$Lambda$1 implements Target {
    private final ClickableViewPager arg$1;

    private ClickableViewPager$$Lambda$1(ClickableViewPager clickableViewPager) {
        this.arg$1 = clickableViewPager;
    }

    public static Target lambdaFactory$(ClickableViewPager clickableViewPager) {
        return new ClickableViewPager$$Lambda$1(clickableViewPager);
    }

    public boolean scrollToPosition(int i) {
        return this.arg$1.autoScrollToPosition(i);
    }
}
