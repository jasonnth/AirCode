package com.airbnb.android.lib.tripassistant.amenities;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class HTPhotoPickerActivity$$Lambda$1 implements Action1 {
    private final HTPhotoPickerActivity arg$1;

    private HTPhotoPickerActivity$$Lambda$1(HTPhotoPickerActivity hTPhotoPickerActivity) {
        this.arg$1 = hTPhotoPickerActivity;
    }

    public static Action1 lambdaFactory$(HTPhotoPickerActivity hTPhotoPickerActivity) {
        return new HTPhotoPickerActivity$$Lambda$1(hTPhotoPickerActivity);
    }

    public void call(Object obj) {
        HTPhotoPickerActivity.lambda$new$0(this.arg$1, (AirRequestNetworkException) obj);
    }
}
