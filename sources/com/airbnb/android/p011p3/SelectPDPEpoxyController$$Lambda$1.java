package com.airbnb.android.p011p3;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.android.p3.SelectPDPEpoxyController$$Lambda$1 */
final /* synthetic */ class SelectPDPEpoxyController$$Lambda$1 implements OnClickListener {
    private final SelectPDPEpoxyController arg$1;

    private SelectPDPEpoxyController$$Lambda$1(SelectPDPEpoxyController selectPDPEpoxyController) {
        this.arg$1 = selectPDPEpoxyController;
    }

    public static OnClickListener lambdaFactory$(SelectPDPEpoxyController selectPDPEpoxyController) {
        return new SelectPDPEpoxyController$$Lambda$1(selectPDPEpoxyController);
    }

    public void onClick(View view) {
        this.arg$1.context.startActivity(HomeTourActivity.intent(this.arg$1.context, this.arg$1.controller.getMockHomeLayout()));
    }
}