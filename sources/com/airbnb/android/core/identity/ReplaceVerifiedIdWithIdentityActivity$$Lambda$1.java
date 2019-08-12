package com.airbnb.android.core.identity;

import com.airbnb.android.core.responses.UserResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ReplaceVerifiedIdWithIdentityActivity$$Lambda$1 implements Action1 {
    private final ReplaceVerifiedIdWithIdentityActivity arg$1;

    private ReplaceVerifiedIdWithIdentityActivity$$Lambda$1(ReplaceVerifiedIdWithIdentityActivity replaceVerifiedIdWithIdentityActivity) {
        this.arg$1 = replaceVerifiedIdWithIdentityActivity;
    }

    public static Action1 lambdaFactory$(ReplaceVerifiedIdWithIdentityActivity replaceVerifiedIdWithIdentityActivity) {
        return new ReplaceVerifiedIdWithIdentityActivity$$Lambda$1(replaceVerifiedIdWithIdentityActivity);
    }

    public void call(Object obj) {
        ReplaceVerifiedIdWithIdentityActivity.lambda$new$0(this.arg$1, (UserResponse) obj);
    }
}
