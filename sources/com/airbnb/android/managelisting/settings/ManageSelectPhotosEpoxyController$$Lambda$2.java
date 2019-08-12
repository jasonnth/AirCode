package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.models.SelectRoomMedia;
import com.google.common.base.Predicate;

final /* synthetic */ class ManageSelectPhotosEpoxyController$$Lambda$2 implements Predicate {
    private static final ManageSelectPhotosEpoxyController$$Lambda$2 instance = new ManageSelectPhotosEpoxyController$$Lambda$2();

    private ManageSelectPhotosEpoxyController$$Lambda$2() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return ((SelectRoomMedia) obj).isPrimaryCoverPhoto();
    }
}
