package com.airbnb.android.lib.fragments;

import com.airbnb.android.core.responses.DeleteManualVerificationResponse;
import p032rx.functions.Action1;

final /* synthetic */ class EditProfileFragment$$Lambda$3 implements Action1 {
    private final EditProfileFragment arg$1;

    private EditProfileFragment$$Lambda$3(EditProfileFragment editProfileFragment) {
        this.arg$1 = editProfileFragment;
    }

    public static Action1 lambdaFactory$(EditProfileFragment editProfileFragment) {
        return new EditProfileFragment$$Lambda$3(editProfileFragment);
    }

    public void call(Object obj) {
        EditProfileFragment.lambda$new$2(this.arg$1, (DeleteManualVerificationResponse) obj);
    }
}
