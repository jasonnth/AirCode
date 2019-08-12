package com.airbnb.android.lib.activities;

import com.airbnb.android.core.responses.UserResponse;
import p032rx.functions.Action1;

final /* synthetic */ class EditProfileActivity$$Lambda$1 implements Action1 {
    private final EditProfileActivity arg$1;

    private EditProfileActivity$$Lambda$1(EditProfileActivity editProfileActivity) {
        this.arg$1 = editProfileActivity;
    }

    public static Action1 lambdaFactory$(EditProfileActivity editProfileActivity) {
        return new EditProfileActivity$$Lambda$1(editProfileActivity);
    }

    public void call(Object obj) {
        EditProfileActivity.lambda$new$0(this.arg$1, (UserResponse) obj);
    }
}
