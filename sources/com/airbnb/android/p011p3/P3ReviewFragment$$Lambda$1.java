package com.airbnb.android.p011p3;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.android.p3.P3ReviewFragment$$Lambda$1 */
final /* synthetic */ class P3ReviewFragment$$Lambda$1 implements OnClickListener {
    private final P3ReviewFragment arg$1;

    private P3ReviewFragment$$Lambda$1(P3ReviewFragment p3ReviewFragment) {
        this.arg$1 = p3ReviewFragment;
    }

    public static OnClickListener lambdaFactory$(P3ReviewFragment p3ReviewFragment) {
        return new P3ReviewFragment$$Lambda$1(p3ReviewFragment);
    }

    public void onClick(View view) {
        P3ReviewFragment.lambda$onPrepareOptionsMenu$0(this.arg$1, view);
    }
}
