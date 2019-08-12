package com.airbnb.p027n2.collections;

import com.airbnb.p027n2.collections.CarouselLayoutManager.OnSnapToPositionListener;

/* renamed from: com.airbnb.n2.collections.Carousel$$Lambda$1 */
final /* synthetic */ class Carousel$$Lambda$1 implements OnSnapToPositionListener {
    private final Carousel arg$1;

    private Carousel$$Lambda$1(Carousel carousel) {
        this.arg$1 = carousel;
    }

    public static OnSnapToPositionListener lambdaFactory$(Carousel carousel) {
        return new Carousel$$Lambda$1(carousel);
    }

    public void onSnappedToPosition(int i, boolean z) {
        Carousel.lambda$init$0(this.arg$1, i, z);
    }
}
