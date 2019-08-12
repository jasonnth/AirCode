package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class EditProfileFragment$$Lambda$11 implements OnClickListener {
    private final EditProfileFragment arg$1;

    private EditProfileFragment$$Lambda$11(EditProfileFragment editProfileFragment) {
        this.arg$1 = editProfileFragment;
    }

    public static OnClickListener lambdaFactory$(EditProfileFragment editProfileFragment) {
        return new EditProfileFragment$$Lambda$11(editProfileFragment);
    }

    public void onClick(View view) {
        EditProfileFragment.lambda$setUpWorkEmailSection$10(this.arg$1, view);
    }
}
