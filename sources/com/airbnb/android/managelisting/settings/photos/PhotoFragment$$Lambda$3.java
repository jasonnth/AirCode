package com.airbnb.android.managelisting.settings.photos;

import com.airbnb.airrequest.BaseResponse;
import p032rx.functions.Action1;

final /* synthetic */ class PhotoFragment$$Lambda$3 implements Action1 {
    private final PhotoFragment arg$1;

    private PhotoFragment$$Lambda$3(PhotoFragment photoFragment) {
        this.arg$1 = photoFragment;
    }

    public static Action1 lambdaFactory$(PhotoFragment photoFragment) {
        return new PhotoFragment$$Lambda$3(photoFragment);
    }

    public void call(Object obj) {
        PhotoFragment.lambda$new$1(this.arg$1, (BaseResponse) obj);
    }
}
