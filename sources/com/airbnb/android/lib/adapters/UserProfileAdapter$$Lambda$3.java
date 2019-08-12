package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class UserProfileAdapter$$Lambda$3 implements OnClickListener {
    private final UserProfileAdapter arg$1;

    private UserProfileAdapter$$Lambda$3(UserProfileAdapter userProfileAdapter) {
        this.arg$1 = userProfileAdapter;
    }

    public static OnClickListener lambdaFactory$(UserProfileAdapter userProfileAdapter) {
        return new UserProfileAdapter$$Lambda$3(userProfileAdapter);
    }

    public void onClick(View view) {
        this.arg$1.clickListener.onSeeAllStoriesClicked();
    }
}
