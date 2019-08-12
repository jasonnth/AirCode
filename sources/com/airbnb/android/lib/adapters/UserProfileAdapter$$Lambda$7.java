package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.enums.WorkEmailStatus;

final /* synthetic */ class UserProfileAdapter$$Lambda$7 implements OnClickListener {
    private final UserProfileAdapter arg$1;
    private final WorkEmailStatus arg$2;

    private UserProfileAdapter$$Lambda$7(UserProfileAdapter userProfileAdapter, WorkEmailStatus workEmailStatus) {
        this.arg$1 = userProfileAdapter;
        this.arg$2 = workEmailStatus;
    }

    public static OnClickListener lambdaFactory$(UserProfileAdapter userProfileAdapter, WorkEmailStatus workEmailStatus) {
        return new UserProfileAdapter$$Lambda$7(userProfileAdapter, workEmailStatus);
    }

    public void onClick(View view) {
        this.arg$1.clickListener.onAddOrVerifyWorkEmail(this.arg$2);
    }
}
