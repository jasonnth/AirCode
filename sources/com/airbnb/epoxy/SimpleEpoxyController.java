package com.airbnb.epoxy;

import java.util.List;

public class SimpleEpoxyController extends EpoxyController {
    private List<? extends EpoxyModel<?>> currentModels;
    private boolean insideSetModels;

    public void setModels(List<? extends EpoxyModel<?>> models) {
        this.currentModels = models;
        this.insideSetModels = true;
        requestModelBuild();
        this.insideSetModels = false;
    }

    public final void requestModelBuild() {
        if (!this.insideSetModels) {
            throw new IllegalEpoxyUsage("You cannot call `requestModelBuild` directly. Call `setModels` instead.");
        }
        super.requestModelBuild();
    }

    /* access modifiers changed from: protected */
    public final void buildModels() {
        if (!isBuildingModels()) {
            throw new IllegalEpoxyUsage("You cannot call `buildModels` directly. Call `setModels` instead.");
        }
        add(this.currentModels);
    }
}
