package com.airbnb.android.cohosting.epoxycontrollers;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class CohostUpsellEpoxyController$$Lambda$1 implements OnClickListener {
    private final CohostUpsellEpoxyController arg$1;

    private CohostUpsellEpoxyController$$Lambda$1(CohostUpsellEpoxyController cohostUpsellEpoxyController) {
        this.arg$1 = cohostUpsellEpoxyController;
    }

    public static OnClickListener lambdaFactory$(CohostUpsellEpoxyController cohostUpsellEpoxyController) {
        return new CohostUpsellEpoxyController$$Lambda$1(cohostUpsellEpoxyController);
    }

    public void onClick(View view) {
        CohostUpsellEpoxyController.lambda$buildModels$0(this.arg$1, view);
    }
}
