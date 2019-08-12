package com.airbnb.android.core.adtracking;

import android.content.Context;

final /* synthetic */ class GoogleAdvertisingIdProvider$$Lambda$1 implements Runnable {
    private final Context arg$1;

    private GoogleAdvertisingIdProvider$$Lambda$1(Context context) {
        this.arg$1 = context;
    }

    public static Runnable lambdaFactory$(Context context) {
        return new GoogleAdvertisingIdProvider$$Lambda$1(context);
    }

    public void run() {
        GoogleAdvertisingIdProvider.lambda$init$0(this.arg$1);
    }
}
