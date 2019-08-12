package com.airbnb.android.core.views;

final /* synthetic */ class AirWebView$AirWebViewClient$$Lambda$1 implements Runnable {
    private final AirWebView arg$1;

    private AirWebView$AirWebViewClient$$Lambda$1(AirWebView airWebView) {
        this.arg$1 = airWebView;
    }

    public static Runnable lambdaFactory$(AirWebView airWebView) {
        return new AirWebView$AirWebViewClient$$Lambda$1(airWebView);
    }

    public void run() {
        this.arg$1.invalidate();
    }
}
