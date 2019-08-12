package com.airbnb.android.lib.activities;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class EditProfileActivity$$Lambda$2 implements Action1 {
    private final EditProfileActivity arg$1;

    private EditProfileActivity$$Lambda$2(EditProfileActivity editProfileActivity) {
        this.arg$1 = editProfileActivity;
    }

    public static Action1 lambdaFactory$(EditProfileActivity editProfileActivity) {
        return new EditProfileActivity$$Lambda$2(editProfileActivity);
    }

    public void call(Object obj) {
        EditProfileActivity.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
