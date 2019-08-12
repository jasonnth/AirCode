package com.airbnb.android.core.adapters;

import com.airbnb.android.core.models.ListingRoom;
import com.airbnb.android.core.viewcomponents.models.BedDetailsCardEpoxyModel_;
import com.google.common.base.Function;

final /* synthetic */ class BedDetailsTrayCarouselAdapter$$Lambda$1 implements Function {
    private static final BedDetailsTrayCarouselAdapter$$Lambda$1 instance = new BedDetailsTrayCarouselAdapter$$Lambda$1();

    private BedDetailsTrayCarouselAdapter$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return new BedDetailsCardEpoxyModel_().room((ListingRoom) obj);
    }
}
