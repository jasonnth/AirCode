package com.airbnb.android.lib.cancellation.host;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class LateCancellationFragment$$Lambda$6 implements OnClickListener {
    private final LateCancellationFragment arg$1;

    private LateCancellationFragment$$Lambda$6(LateCancellationFragment lateCancellationFragment) {
        this.arg$1 = lateCancellationFragment;
    }

    public static OnClickListener lambdaFactory$(LateCancellationFragment lateCancellationFragment) {
        return new LateCancellationFragment$$Lambda$6(lateCancellationFragment);
    }

    public void onClick(View view) {
        this.arg$1.requestInternationalNumbers();
    }
}
