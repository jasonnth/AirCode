package com.airbnb.android.lib.views;

import android.view.View.OnClickListener;
import com.airbnb.p027n2.interfaces.LinkOnClickListener;

final /* synthetic */ class UserVerificationView$$Lambda$1 implements LinkOnClickListener {
    private final UserVerificationView arg$1;
    private final OnClickListener arg$2;

    private UserVerificationView$$Lambda$1(UserVerificationView userVerificationView, OnClickListener onClickListener) {
        this.arg$1 = userVerificationView;
        this.arg$2 = onClickListener;
    }

    public static LinkOnClickListener lambdaFactory$(UserVerificationView userVerificationView, OnClickListener onClickListener) {
        return new UserVerificationView$$Lambda$1(userVerificationView, onClickListener);
    }

    public void onClickLink(int i) {
        this.arg$2.onClick(this.arg$1.learnMoreLink);
    }
}
