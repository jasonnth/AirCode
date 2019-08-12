package com.airbnb.android.lib.fragments;

import com.airbnb.android.core.responses.UserResponse;
import p032rx.functions.Action1;

final /* synthetic */ class EditProfileFragment$$Lambda$5 implements Action1 {
    private final EditProfileFragment arg$1;

    private EditProfileFragment$$Lambda$5(EditProfileFragment editProfileFragment) {
        this.arg$1 = editProfileFragment;
    }

    public static Action1 lambdaFactory$(EditProfileFragment editProfileFragment) {
        return new EditProfileFragment$$Lambda$5(editProfileFragment);
    }

    public void call(Object obj) {
        EditProfileFragment.lambda$new$4(this.arg$1, (UserResponse) obj);
    }
}
