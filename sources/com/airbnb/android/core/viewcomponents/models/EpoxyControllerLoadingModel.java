package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.p027n2.components.RefreshLoader;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class EpoxyControllerLoadingModel extends AirEpoxyModel<RefreshLoader> {
    private static int seedCounter = 0;
    final int seed;

    public EpoxyControllerLoadingModel() {
        int i = seedCounter;
        seedCounter = i + 1;
        this.seed = i;
    }

    public int getDividerViewType() {
        return 5;
    }
}
