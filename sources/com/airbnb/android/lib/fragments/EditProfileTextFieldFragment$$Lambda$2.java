package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class EditProfileTextFieldFragment$$Lambda$2 implements OnClickListener {
    private final EditProfileTextFieldFragment arg$1;

    private EditProfileTextFieldFragment$$Lambda$2(EditProfileTextFieldFragment editProfileTextFieldFragment) {
        this.arg$1 = editProfileTextFieldFragment;
    }

    public static OnClickListener lambdaFactory$(EditProfileTextFieldFragment editProfileTextFieldFragment) {
        return new EditProfileTextFieldFragment$$Lambda$2(editProfileTextFieldFragment);
    }

    public void onClick(View view) {
        EditProfileTextFieldFragment.lambda$setActionBarEditModeEnabled$1(this.arg$1, view);
    }
}
