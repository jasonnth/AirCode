package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.UserFlag;

final /* synthetic */ class UserProfileAdapter$$Lambda$1 implements OnClickListener {
    private final UserProfileAdapter arg$1;
    private final UserFlag arg$2;

    private UserProfileAdapter$$Lambda$1(UserProfileAdapter userProfileAdapter, UserFlag userFlag) {
        this.arg$1 = userProfileAdapter;
        this.arg$2 = userFlag;
    }

    public static OnClickListener lambdaFactory$(UserProfileAdapter userProfileAdapter, UserFlag userFlag) {
        return new UserProfileAdapter$$Lambda$1(userProfileAdapter, userFlag);
    }

    public void onClick(View view) {
        this.arg$1.clickListener.onReportUser(this.arg$2);
    }
}
