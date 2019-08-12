package com.airbnb.android.profile_completion.edit_about_me;

import com.airbnb.android.core.interfaces.OnHomeListener;

final /* synthetic */ class EditAboutMeFragment$$Lambda$4 implements OnHomeListener {
    private final EditAboutMeFragment arg$1;

    private EditAboutMeFragment$$Lambda$4(EditAboutMeFragment editAboutMeFragment) {
        this.arg$1 = editAboutMeFragment;
    }

    public static OnHomeListener lambdaFactory$(EditAboutMeFragment editAboutMeFragment) {
        return new EditAboutMeFragment$$Lambda$4(editAboutMeFragment);
    }

    public boolean onHomePressed() {
        return this.arg$1.dismissKeyboard();
    }
}
