package com.airbnb.epoxy;

public abstract class TypedEpoxyController<T> extends EpoxyController {
    private T currentData;
    private boolean insideSetData;

    /* access modifiers changed from: protected */
    public abstract void buildModels(T t);

    public final void setData(T data) {
        this.currentData = data;
        this.insideSetData = true;
        requestModelBuild();
        this.insideSetData = false;
    }

    public final void requestModelBuild() {
        if (!this.insideSetData) {
            throw new IllegalStateException("You cannot call `requestModelBuild` directly. Call `setData` instead to trigger a model refresh with new data.");
        }
        super.requestModelBuild();
    }

    public void requestDelayedModelBuild(int delayMs) {
        if (!this.insideSetData) {
            throw new IllegalStateException("You cannot call `requestModelBuild` directly. Call `setData` instead to trigger a model refresh with new data.");
        }
        super.requestDelayedModelBuild(delayMs);
    }

    public final T getCurrentData() {
        return this.currentData;
    }

    /* access modifiers changed from: protected */
    public final void buildModels() {
        if (!isBuildingModels()) {
            throw new IllegalStateException("You cannot call `buildModels` directly. Call `setData` instead to trigger a model refresh with new data.");
        }
        buildModels(this.currentData);
    }
}
