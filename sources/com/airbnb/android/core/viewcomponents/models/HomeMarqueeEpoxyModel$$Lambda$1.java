package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.models.Photo;
import com.airbnb.p027n2.components.HomeMarquee.HomeMarqueeCarouselItem;
import com.google.common.base.Function;

final /* synthetic */ class HomeMarqueeEpoxyModel$$Lambda$1 implements Function {
    private static final HomeMarqueeEpoxyModel$$Lambda$1 instance = new HomeMarqueeEpoxyModel$$Lambda$1();

    private HomeMarqueeEpoxyModel$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return new HomeMarqueeCarouselItem((Photo) obj);
    }
}
