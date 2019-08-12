package com.airbnb.android.lib.activities;

final /* synthetic */ class RequestTestSuiteActivity$$Lambda$1 implements Runnable {
    private final RequestTestSuiteActivity arg$1;
    private final String arg$2;

    private RequestTestSuiteActivity$$Lambda$1(RequestTestSuiteActivity requestTestSuiteActivity, String str) {
        this.arg$1 = requestTestSuiteActivity;
        this.arg$2 = str;
    }

    public static Runnable lambdaFactory$(RequestTestSuiteActivity requestTestSuiteActivity, String str) {
        return new RequestTestSuiteActivity$$Lambda$1(requestTestSuiteActivity, str);
    }

    public void run() {
        RequestTestSuiteActivity.lambda$appendLog$0(this.arg$1, this.arg$2);
    }
}
