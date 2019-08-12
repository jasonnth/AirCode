package com.airbnb.epoxy;

public abstract class Typed2EpoxyController<T, U> extends EpoxyController {
    private T data1;
    private U data2;
    private boolean insideSetData;

    /* access modifiers changed from: protected */
    public abstract void buildModels(T t, U u);

    public void setData(T data12, U data22) {
        this.data1 = data12;
        this.data2 = data22;
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

    /* access modifiers changed from: protected */
    public final void buildModels() {
        if (!isBuildingModels()) {
            throw new IllegalStateException("You cannot call `buildModels` directly. Call `setData` instead to trigger a model refresh with new data.");
        }
        buildModels(this.data1, this.data2);
    }
}
