package com.airbnb.android.login.p339ui;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.android.login.ui.MobileWebLandingFragment$$Lambda$1 */
final /* synthetic */ class MobileWebLandingFragment$$Lambda$1 implements OnClickListener {
    private final MobileWebLandingFragment arg$1;
    private final String arg$2;
    private final String arg$3;

    private MobileWebLandingFragment$$Lambda$1(MobileWebLandingFragment mobileWebLandingFragment, String str, String str2) {
        this.arg$1 = mobileWebLandingFragment;
        this.arg$2 = str;
        this.arg$3 = str2;
    }

    public static OnClickListener lambdaFactory$(MobileWebLandingFragment mobileWebLandingFragment, String str, String str2) {
        return new MobileWebLandingFragment$$Lambda$1(mobileWebLandingFragment, str, str2);
    }

    public void onClick(View view) {
        this.arg$1.attemptLogin(this.arg$2, this.arg$3);
    }
}
