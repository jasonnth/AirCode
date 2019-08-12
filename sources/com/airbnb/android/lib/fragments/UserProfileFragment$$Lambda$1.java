package com.airbnb.android.lib.fragments;

import com.airbnb.android.core.responses.UserFlagResponse;
import p032rx.functions.Action1;

final /* synthetic */ class UserProfileFragment$$Lambda$1 implements Action1 {
    private final UserProfileFragment arg$1;

    private UserProfileFragment$$Lambda$1(UserProfileFragment userProfileFragment) {
        this.arg$1 = userProfileFragment;
    }

    public static Action1 lambdaFactory$(UserProfileFragment userProfileFragment) {
        return new UserProfileFragment$$Lambda$1(userProfileFragment);
    }

    public void call(Object obj) {
        UserProfileFragment.lambda$new$0(this.arg$1, (UserFlagResponse) obj);
    }
}
