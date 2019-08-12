package com.airbnb.android.managelisting.settings.photos;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class PhotoFragment$$Lambda$2 implements Action1 {
    private final PhotoFragment arg$1;

    private PhotoFragment$$Lambda$2(PhotoFragment photoFragment) {
        this.arg$1 = photoFragment;
    }

    public static Action1 lambdaFactory$(PhotoFragment photoFragment) {
        return new PhotoFragment$$Lambda$2(photoFragment);
    }

    public void call(Object obj) {
        this.arg$1.handleError((AirRequestNetworkException) obj);
    }
}
