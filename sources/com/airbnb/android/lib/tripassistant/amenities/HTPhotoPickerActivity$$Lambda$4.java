package com.airbnb.android.lib.tripassistant.amenities;

import com.airbnb.android.lib.tripassistant.HelpThreadPhoto;
import com.google.common.base.Predicate;

final /* synthetic */ class HTPhotoPickerActivity$$Lambda$4 implements Predicate {
    private final HTPhotoPickerActivity arg$1;

    private HTPhotoPickerActivity$$Lambda$4(HTPhotoPickerActivity hTPhotoPickerActivity) {
        this.arg$1 = hTPhotoPickerActivity;
    }

    public static Predicate lambdaFactory$(HTPhotoPickerActivity hTPhotoPickerActivity) {
        return new HTPhotoPickerActivity$$Lambda$4(hTPhotoPickerActivity);
    }

    public boolean apply(Object obj) {
        return this.arg$1.isPhotoUploading((HelpThreadPhoto) obj);
    }
}
