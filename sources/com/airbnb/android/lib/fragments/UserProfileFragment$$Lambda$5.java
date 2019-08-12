package com.airbnb.android.lib.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class UserProfileFragment$$Lambda$5 implements Action1 {
    private final UserProfileFragment arg$1;

    private UserProfileFragment$$Lambda$5(UserProfileFragment userProfileFragment) {
        this.arg$1 = userProfileFragment;
    }

    public static Action1 lambdaFactory$(UserProfileFragment userProfileFragment) {
        return new UserProfileFragment$$Lambda$5(userProfileFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.root, (AirRequestNetworkException) obj);
    }
}
