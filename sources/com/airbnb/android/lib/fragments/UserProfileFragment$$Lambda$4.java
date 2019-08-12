package com.airbnb.android.lib.fragments;

import com.airbnb.android.core.responses.ListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class UserProfileFragment$$Lambda$4 implements Action1 {
    private final UserProfileFragment arg$1;

    private UserProfileFragment$$Lambda$4(UserProfileFragment userProfileFragment) {
        this.arg$1 = userProfileFragment;
    }

    public static Action1 lambdaFactory$(UserProfileFragment userProfileFragment) {
        return new UserProfileFragment$$Lambda$4(userProfileFragment);
    }

    public void call(Object obj) {
        UserProfileFragment.lambda$new$3(this.arg$1, (ListingResponse) obj);
    }
}
