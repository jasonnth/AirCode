package com.airbnb.android.lib.host.stats;

import android.content.Context;

final /* synthetic */ class CohostingStandardFragment$$Lambda$1 implements Listener {
    private final CohostingStandardFragment arg$1;

    private CohostingStandardFragment$$Lambda$1(CohostingStandardFragment cohostingStandardFragment) {
        this.arg$1 = cohostingStandardFragment;
    }

    public static Listener lambdaFactory$(CohostingStandardFragment cohostingStandardFragment) {
        return new CohostingStandardFragment$$Lambda$1(cohostingStandardFragment);
    }

    public void goToCohostingStandardPage(Context context) {
        this.arg$1.goToCohostingStandardPage(context);
    }
}
