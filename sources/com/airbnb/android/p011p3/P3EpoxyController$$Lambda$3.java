package com.airbnb.android.p011p3;

import com.airbnb.p027n2.collections.Carousel.OnSnapToPositionListener;

/* renamed from: com.airbnb.android.p3.P3EpoxyController$$Lambda$3 */
final /* synthetic */ class P3EpoxyController$$Lambda$3 implements OnSnapToPositionListener {
    private final P3EpoxyController arg$1;

    private P3EpoxyController$$Lambda$3(P3EpoxyController p3EpoxyController) {
        this.arg$1 = p3EpoxyController;
    }

    public static OnSnapToPositionListener lambdaFactory$(P3EpoxyController p3EpoxyController) {
        return new P3EpoxyController$$Lambda$3(p3EpoxyController);
    }

    public void onSnappedToPosition(int i, boolean z, boolean z2) {
        P3EpoxyController.lambda$buildModels$1(this.arg$1, i, z, z2);
    }
}
