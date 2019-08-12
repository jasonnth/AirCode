package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class UserProfileAdapter$$Lambda$4 implements OnClickListener {
    private final UserProfileAdapter arg$1;

    private UserProfileAdapter$$Lambda$4(UserProfileAdapter userProfileAdapter) {
        this.arg$1 = userProfileAdapter;
    }

    public static OnClickListener lambdaFactory$(UserProfileAdapter userProfileAdapter) {
        return new UserProfileAdapter$$Lambda$4(userProfileAdapter);
    }

    public void onClick(View view) {
        this.arg$1.clickListener.onVerifyMyIdentity();
    }
}
