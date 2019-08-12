package com.airbnb.android.login.p339ui;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.android.login.ui.MobileWebLandingFragment$$Lambda$2 */
final /* synthetic */ class MobileWebLandingFragment$$Lambda$2 implements OnClickListener {
    private final MobileWebLandingFragment arg$1;

    private MobileWebLandingFragment$$Lambda$2(MobileWebLandingFragment mobileWebLandingFragment) {
        this.arg$1 = mobileWebLandingFragment;
    }

    public static OnClickListener lambdaFactory$(MobileWebLandingFragment mobileWebLandingFragment) {
        return new MobileWebLandingFragment$$Lambda$2(mobileWebLandingFragment);
    }

    public void onClick(View view) {
        this.arg$1.switchAccount();
    }
}
