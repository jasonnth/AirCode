package com.airbnb.android.internal.bugreporter;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class InternalBugReportAdapter$$Lambda$8 implements OnClickListener {
    private final Listener arg$1;

    private InternalBugReportAdapter$$Lambda$8(Listener listener) {
        this.arg$1 = listener;
    }

    public static OnClickListener lambdaFactory$(Listener listener) {
        return new InternalBugReportAdapter$$Lambda$8(listener);
    }

    public void onClick(View view) {
        this.arg$1.pickPhoto();
    }
}
