package com.airbnb.android.lib.views;

final /* synthetic */ class ScrollingBackground$$Lambda$1 implements Runnable {
    private final ScrollingBackground arg$1;

    private ScrollingBackground$$Lambda$1(ScrollingBackground scrollingBackground) {
        this.arg$1 = scrollingBackground;
    }

    public static Runnable lambdaFactory$(ScrollingBackground scrollingBackground) {
        return new ScrollingBackground$$Lambda$1(scrollingBackground);
    }

    public void run() {
        this.arg$1.invalidate();
    }
}
