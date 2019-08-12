package com.airbnb.p027n2.browser;

import android.support.p000v4.app.FragmentManager.OnBackStackChangedListener;

/* renamed from: com.airbnb.n2.browser.DLSComponentBrowserActivity$$Lambda$1 */
final /* synthetic */ class DLSComponentBrowserActivity$$Lambda$1 implements OnBackStackChangedListener {
    private final DLSComponentBrowserActivity arg$1;

    private DLSComponentBrowserActivity$$Lambda$1(DLSComponentBrowserActivity dLSComponentBrowserActivity) {
        this.arg$1 = dLSComponentBrowserActivity;
    }

    public static OnBackStackChangedListener lambdaFactory$(DLSComponentBrowserActivity dLSComponentBrowserActivity) {
        return new DLSComponentBrowserActivity$$Lambda$1(dLSComponentBrowserActivity);
    }

    public void onBackStackChanged() {
        DLSComponentBrowserActivity.lambda$new$0(this.arg$1);
    }
}
