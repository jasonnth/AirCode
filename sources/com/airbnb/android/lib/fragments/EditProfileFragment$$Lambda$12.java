package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class EditProfileFragment$$Lambda$12 implements OnClickListener {
    private final EditProfileFragment arg$1;

    private EditProfileFragment$$Lambda$12(EditProfileFragment editProfileFragment) {
        this.arg$1 = editProfileFragment;
    }

    public static OnClickListener lambdaFactory$(EditProfileFragment editProfileFragment) {
        return new EditProfileFragment$$Lambda$12(editProfileFragment);
    }

    public void onClick(View view) {
        this.arg$1.startUpdateProfilePicture();
    }
}
