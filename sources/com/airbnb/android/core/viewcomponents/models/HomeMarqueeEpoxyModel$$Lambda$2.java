package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.models.Photo;
import com.google.common.base.Function;

final /* synthetic */ class HomeMarqueeEpoxyModel$$Lambda$2 implements Function {
    private static final HomeMarqueeEpoxyModel$$Lambda$2 instance = new HomeMarqueeEpoxyModel$$Lambda$2();

    private HomeMarqueeEpoxyModel$$Lambda$2() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return HomeMarqueeEpoxyModel.lambda$bind$0((Photo) obj);
    }
}
