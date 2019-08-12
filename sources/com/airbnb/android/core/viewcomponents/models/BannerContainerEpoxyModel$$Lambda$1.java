package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.models.Banner;
import com.google.common.base.Function;

final /* synthetic */ class BannerContainerEpoxyModel$$Lambda$1 implements Function {
    private final BannerContainerEpoxyModel arg$1;

    private BannerContainerEpoxyModel$$Lambda$1(BannerContainerEpoxyModel bannerContainerEpoxyModel) {
        this.arg$1 = bannerContainerEpoxyModel;
    }

    public static Function lambdaFactory$(BannerContainerEpoxyModel bannerContainerEpoxyModel) {
        return new BannerContainerEpoxyModel$$Lambda$1(bannerContainerEpoxyModel);
    }

    public Object apply(Object obj) {
        return this.arg$1.getBannerItem((Banner) obj);
    }
}
