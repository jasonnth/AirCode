package com.airbnb.android.lib.china5a;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.lib.china5a.FiveAxiomRepoImpl.PhotoModel;
import p032rx.functions.Action1;

final /* synthetic */ class FiveAxiomRepoImpl$PhotoModel$$Lambda$6 implements Action1 {
    private final PhotoModel arg$1;

    private FiveAxiomRepoImpl$PhotoModel$$Lambda$6(PhotoModel photoModel) {
        this.arg$1 = photoModel;
    }

    public static Action1 lambdaFactory$(PhotoModel photoModel) {
        return new FiveAxiomRepoImpl$PhotoModel$$Lambda$6(photoModel);
    }

    public void call(Object obj) {
        PhotoModel.lambda$new$5(this.arg$1, (AirRequestNetworkException) obj);
    }
}
