package com.airbnb.android.p011p3;

import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.google.common.collect.FluentIterable;

/* renamed from: com.airbnb.android.p3.P3EpoxyController$$Lambda$22 */
final /* synthetic */ class P3EpoxyController$$Lambda$22 implements OnModelBoundListener {
    private final P3EpoxyController arg$1;

    private P3EpoxyController$$Lambda$22(P3EpoxyController p3EpoxyController) {
        this.arg$1 = p3EpoxyController;
    }

    public static OnModelBoundListener lambdaFactory$(P3EpoxyController p3EpoxyController) {
        return new P3EpoxyController$$Lambda$22(p3EpoxyController);
    }

    public void onModelBound(EpoxyModel epoxyModel, Object obj, int i) {
        this.arg$1.controller.getAnalytics().trackSimilarListingsEnteringViewport(FluentIterable.from((Iterable<E>) this.arg$1.controller.getState().similarListings()).transform(P3EpoxyController$$Lambda$23.lambdaFactory$()).toList(), InstantBookUpsellUtils.getIBSimilarListingCount(this.arg$1.controller.getState().similarListings()));
    }
}
