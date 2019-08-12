package com.airbnb.android.nestedlistings.epoxycontrollers;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class NestedListingsOverviewEpoxyController$$Lambda$1 implements OnClickListener {
    private final NestedListingsOverviewEpoxyController arg$1;

    private NestedListingsOverviewEpoxyController$$Lambda$1(NestedListingsOverviewEpoxyController nestedListingsOverviewEpoxyController) {
        this.arg$1 = nestedListingsOverviewEpoxyController;
    }

    public static OnClickListener lambdaFactory$(NestedListingsOverviewEpoxyController nestedListingsOverviewEpoxyController) {
        return new NestedListingsOverviewEpoxyController$$Lambda$1(nestedListingsOverviewEpoxyController);
    }

    public void onClick(View view) {
        this.arg$1.listener.onLearnMore();
    }
}
