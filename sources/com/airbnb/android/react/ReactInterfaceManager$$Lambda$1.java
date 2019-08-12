package com.airbnb.android.react;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

final /* synthetic */ class ReactInterfaceManager$$Lambda$1 implements Runnable {
    private final Bundle arg$1;
    private final Activity arg$2;
    private final Intent arg$3;
    private final int arg$4;

    private ReactInterfaceManager$$Lambda$1(Bundle bundle, Activity activity, Intent intent, int i) {
        this.arg$1 = bundle;
        this.arg$2 = activity;
        this.arg$3 = intent;
        this.arg$4 = i;
    }

    public static Runnable lambdaFactory$(Bundle bundle, Activity activity, Intent intent, int i) {
        return new ReactInterfaceManager$$Lambda$1(bundle, activity, intent, i);
    }

    public void run() {
        ReactInterfaceManager.lambda$startActivityWithPromise$0(this.arg$1, this.arg$2, this.arg$3, this.arg$4);
    }
}
