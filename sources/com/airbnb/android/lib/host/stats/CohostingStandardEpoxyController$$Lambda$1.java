package com.airbnb.android.lib.host.stats;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class CohostingStandardEpoxyController$$Lambda$1 implements OnClickListener {
    private final CohostingStandardEpoxyController arg$1;

    private CohostingStandardEpoxyController$$Lambda$1(CohostingStandardEpoxyController cohostingStandardEpoxyController) {
        this.arg$1 = cohostingStandardEpoxyController;
    }

    public static OnClickListener lambdaFactory$(CohostingStandardEpoxyController cohostingStandardEpoxyController) {
        return new CohostingStandardEpoxyController$$Lambda$1(cohostingStandardEpoxyController);
    }

    public void onClick(View view) {
        this.arg$1.listener.goToCohostingStandardPage(this.arg$1.context);
    }
}
