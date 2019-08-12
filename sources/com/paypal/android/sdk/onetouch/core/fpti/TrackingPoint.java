package com.paypal.android.sdk.onetouch.core.fpti;

import com.airbnb.android.core.analytics.BaseAnalytics;

public enum TrackingPoint {
    WalletIsPresent("checkwallet", "present"),
    WalletIsAbsent("checkwallet", "absent"),
    PreflightBrowser("preflight", "browser"),
    PreflightWallet("preflight", "wallet"),
    PreflightNone("preflight", "none"),
    SwitchToBrowser("switchaway", "browser"),
    SwitchToWallet("switchaway", "wallet"),
    Cancel("switchback", BaseAnalytics.CANCEL),
    Return("switchback", "return"),
    Error("switchback", BaseAnalytics.CANCEL, true);
    

    /* renamed from: mC */
    private final String f3818mC;

    /* renamed from: mD */
    private final String f3819mD;
    private final boolean mHasError;

    private TrackingPoint(String c, String d, boolean hasError) {
        this.f3818mC = c;
        this.f3819mD = d;
        this.mHasError = hasError;
    }

    private TrackingPoint(String c, String d) {
        this(r7, r8, c, d, false);
    }

    public String getCd() {
        return this.f3818mC + ":" + this.f3819mD;
    }

    public boolean hasError() {
        return this.mHasError;
    }
}
