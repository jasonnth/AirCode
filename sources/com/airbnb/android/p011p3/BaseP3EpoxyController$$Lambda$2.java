package com.airbnb.android.p011p3;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.android.p3.BaseP3EpoxyController$$Lambda$2 */
final /* synthetic */ class BaseP3EpoxyController$$Lambda$2 implements OnClickListener {
    private final BaseP3EpoxyController arg$1;

    private BaseP3EpoxyController$$Lambda$2(BaseP3EpoxyController baseP3EpoxyController) {
        this.arg$1 = baseP3EpoxyController;
    }

    public static OnClickListener lambdaFactory$(BaseP3EpoxyController baseP3EpoxyController) {
        return new BaseP3EpoxyController$$Lambda$2(baseP3EpoxyController);
    }

    public void onClick(View view) {
        this.arg$1.controller.onItemClick(10);
    }
}
