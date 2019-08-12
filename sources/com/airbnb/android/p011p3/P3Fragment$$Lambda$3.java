package com.airbnb.android.p011p3;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.android.p3.P3Fragment$$Lambda$3 */
final /* synthetic */ class P3Fragment$$Lambda$3 implements OnClickListener {
    private final P3Fragment arg$1;

    private P3Fragment$$Lambda$3(P3Fragment p3Fragment) {
        this.arg$1 = p3Fragment;
    }

    public static OnClickListener lambdaFactory$(P3Fragment p3Fragment) {
        return new P3Fragment$$Lambda$3(p3Fragment);
    }

    public void onClick(View view) {
        this.arg$1.controller.onItemClick(10);
    }
}
