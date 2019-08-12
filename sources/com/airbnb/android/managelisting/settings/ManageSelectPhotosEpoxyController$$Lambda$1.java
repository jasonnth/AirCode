package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.models.SelectListingRoom;
import com.google.common.base.Function;

final /* synthetic */ class ManageSelectPhotosEpoxyController$$Lambda$1 implements Function {
    private static final ManageSelectPhotosEpoxyController$$Lambda$1 instance = new ManageSelectPhotosEpoxyController$$Lambda$1();

    private ManageSelectPhotosEpoxyController$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return ((SelectListingRoom) obj).getMedia();
    }
}
