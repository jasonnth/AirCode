package com.airbnb.android.managelisting.settings.photos;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ManagePhotosFragment$$Lambda$2 implements Action1 {
    private final ManagePhotosFragment arg$1;

    private ManagePhotosFragment$$Lambda$2(ManagePhotosFragment managePhotosFragment) {
        this.arg$1 = managePhotosFragment;
    }

    public static Action1 lambdaFactory$(ManagePhotosFragment managePhotosFragment) {
        return new ManagePhotosFragment$$Lambda$2(managePhotosFragment);
    }

    public void call(Object obj) {
        ManagePhotosFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
