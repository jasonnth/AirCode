package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.p027n2.components.RefreshLoader;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class LoadingRowEpoxyModel extends AirEpoxyModel<RefreshLoader> {
    int seed;

    public void bind(RefreshLoader view) {
        super.bind(view);
        incrementSeed();
    }

    public void incrementSeed() {
        this.seed++;
    }

    public int getDividerViewType() {
        return 5;
    }
}
