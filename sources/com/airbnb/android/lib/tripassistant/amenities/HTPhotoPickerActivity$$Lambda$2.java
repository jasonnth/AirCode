package com.airbnb.android.lib.tripassistant.amenities;

import com.airbnb.android.lib.tripassistant.HelpThreadPhoto;
import com.google.common.base.Predicate;

final /* synthetic */ class HTPhotoPickerActivity$$Lambda$2 implements Predicate {
    private static final HTPhotoPickerActivity$$Lambda$2 instance = new HTPhotoPickerActivity$$Lambda$2();

    private HTPhotoPickerActivity$$Lambda$2() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return ((HelpThreadPhoto) obj).hasAttachment();
    }
}
