package com.airbnb.android.lib.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class EditProfileFragment$$Lambda$4 implements Action1 {
    private final EditProfileFragment arg$1;

    private EditProfileFragment$$Lambda$4(EditProfileFragment editProfileFragment) {
        this.arg$1 = editProfileFragment;
    }

    public static Action1 lambdaFactory$(EditProfileFragment editProfileFragment) {
        return new EditProfileFragment$$Lambda$4(editProfileFragment);
    }

    public void call(Object obj) {
        EditProfileFragment.lambda$new$3(this.arg$1, (AirRequestNetworkException) obj);
    }
}
