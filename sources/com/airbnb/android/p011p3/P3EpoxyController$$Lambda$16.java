package com.airbnb.android.p011p3;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.android.p3.P3EpoxyController$$Lambda$16 */
final /* synthetic */ class P3EpoxyController$$Lambda$16 implements OnClickListener {
    private final P3EpoxyController arg$1;

    private P3EpoxyController$$Lambda$16(P3EpoxyController p3EpoxyController) {
        this.arg$1 = p3EpoxyController;
    }

    public static OnClickListener lambdaFactory$(P3EpoxyController p3EpoxyController) {
        return new P3EpoxyController$$Lambda$16(p3EpoxyController);
    }

    public void onClick(View view) {
        this.arg$1.controller.onItemClick(4);
    }
}
