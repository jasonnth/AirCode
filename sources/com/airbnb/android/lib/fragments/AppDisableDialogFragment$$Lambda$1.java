package com.airbnb.android.lib.fragments;

import android.os.Process;

final /* synthetic */ class AppDisableDialogFragment$$Lambda$1 implements Runnable {
    private static final AppDisableDialogFragment$$Lambda$1 instance = new AppDisableDialogFragment$$Lambda$1();

    private AppDisableDialogFragment$$Lambda$1() {
    }

    public static Runnable lambdaFactory$() {
        return instance;
    }

    public void run() {
        Process.killProcess(Process.myPid());
    }
}
