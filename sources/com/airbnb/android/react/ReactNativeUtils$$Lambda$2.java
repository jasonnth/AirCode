package com.airbnb.android.react;

import android.app.Application;
import android.widget.Toast;

final /* synthetic */ class ReactNativeUtils$$Lambda$2 implements Runnable {
    private final Application arg$1;

    private ReactNativeUtils$$Lambda$2(Application application) {
        this.arg$1 = application;
    }

    public static Runnable lambdaFactory$(Application application) {
        return new ReactNativeUtils$$Lambda$2(application);
    }

    public void run() {
        Toast.makeText(this.arg$1, this.arg$1.getString(C7663R.string.rn_no_bundle_file), 1).show();
    }
}
