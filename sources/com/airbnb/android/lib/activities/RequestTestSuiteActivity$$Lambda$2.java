package com.airbnb.android.lib.activities;

final /* synthetic */ class RequestTestSuiteActivity$$Lambda$2 implements Runnable {
    private final RequestTestSuiteActivity arg$1;

    private RequestTestSuiteActivity$$Lambda$2(RequestTestSuiteActivity requestTestSuiteActivity) {
        this.arg$1 = requestTestSuiteActivity;
    }

    public static Runnable lambdaFactory$(RequestTestSuiteActivity requestTestSuiteActivity) {
        return new RequestTestSuiteActivity$$Lambda$2(requestTestSuiteActivity);
    }

    public void run() {
        this.arg$1.scrollView.scrollTo(0, this.arg$1.scrollView.getBottom());
    }
}
