package com.airbnb.android.profile_completion.edit_about_me;

import com.airbnb.android.core.interfaces.OnBackListener;

final /* synthetic */ class EditAboutMeFragment$$Lambda$1 implements OnBackListener {
    private final EditAboutMeFragment arg$1;

    private EditAboutMeFragment$$Lambda$1(EditAboutMeFragment editAboutMeFragment) {
        this.arg$1 = editAboutMeFragment;
    }

    public static OnBackListener lambdaFactory$(EditAboutMeFragment editAboutMeFragment) {
        return new EditAboutMeFragment$$Lambda$1(editAboutMeFragment);
    }

    public boolean onBackPressed() {
        return this.arg$1.dismissKeyboard();
    }
}
