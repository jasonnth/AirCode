package com.airbnb.p027n2.collections;

import com.airbnb.p027n2.utils.AutoScrollingController.Target;

/* renamed from: com.airbnb.n2.collections.Carousel$$Lambda$2 */
final /* synthetic */ class Carousel$$Lambda$2 implements Target {
    private final Carousel arg$1;

    private Carousel$$Lambda$2(Carousel carousel) {
        this.arg$1 = carousel;
    }

    public static Target lambdaFactory$(Carousel carousel) {
        return new Carousel$$Lambda$2(carousel);
    }

    public boolean scrollToPosition(int i) {
        return this.arg$1.autoScrollToPosition(i);
    }
}
