package com.airbnb.android.core.identity;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ReplaceVerifiedIdWithIdentityActivity$$Lambda$2 implements Action1 {
    private final ReplaceVerifiedIdWithIdentityActivity arg$1;

    private ReplaceVerifiedIdWithIdentityActivity$$Lambda$2(ReplaceVerifiedIdWithIdentityActivity replaceVerifiedIdWithIdentityActivity) {
        this.arg$1 = replaceVerifiedIdWithIdentityActivity;
    }

    public static Action1 lambdaFactory$(ReplaceVerifiedIdWithIdentityActivity replaceVerifiedIdWithIdentityActivity) {
        return new ReplaceVerifiedIdWithIdentityActivity$$Lambda$2(replaceVerifiedIdWithIdentityActivity);
    }

    public void call(Object obj) {
        ReplaceVerifiedIdWithIdentityActivity.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
