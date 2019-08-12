package com.airbnb.android.lib.utils;

final /* synthetic */ class VerificationsPoller$$Lambda$3 implements Runnable {
    private final VerificationsPoller arg$1;

    private VerificationsPoller$$Lambda$3(VerificationsPoller verificationsPoller) {
        this.arg$1 = verificationsPoller;
    }

    public static Runnable lambdaFactory$(VerificationsPoller verificationsPoller) {
        return new VerificationsPoller$$Lambda$3(verificationsPoller);
    }

    public void run() {
        this.arg$1.startVerificationsRequest();
    }
}
