package com.airbnb.android.lib.tripassistant.amenities;

import com.airbnb.android.lib.tripassistant.HelpThreadPhoto;
import com.google.common.base.Function;

final /* synthetic */ class HTPhotoPickerActivity$$Lambda$3 implements Function {
    private static final HTPhotoPickerActivity$$Lambda$3 instance = new HTPhotoPickerActivity$$Lambda$3();

    private HTPhotoPickerActivity$$Lambda$3() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return ((HelpThreadPhoto) obj).attachment();
    }
}
