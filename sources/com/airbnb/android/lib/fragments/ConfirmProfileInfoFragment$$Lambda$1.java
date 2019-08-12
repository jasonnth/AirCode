package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.User;

final /* synthetic */ class ConfirmProfileInfoFragment$$Lambda$1 implements OnClickListener {
    private final ConfirmProfileInfoFragment arg$1;
    private final User arg$2;

    private ConfirmProfileInfoFragment$$Lambda$1(ConfirmProfileInfoFragment confirmProfileInfoFragment, User user) {
        this.arg$1 = confirmProfileInfoFragment;
        this.arg$2 = user;
    }

    public static OnClickListener lambdaFactory$(ConfirmProfileInfoFragment confirmProfileInfoFragment, User user) {
        return new ConfirmProfileInfoFragment$$Lambda$1(confirmProfileInfoFragment, user);
    }

    public void onClick(View view) {
        ConfirmProfileInfoFragment.lambda$onCreateView$0(this.arg$1, this.arg$2, view);
    }
}
