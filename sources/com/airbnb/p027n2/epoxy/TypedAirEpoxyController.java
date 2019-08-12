package com.airbnb.p027n2.epoxy;

/* renamed from: com.airbnb.n2.epoxy.TypedAirEpoxyController */
public abstract class TypedAirEpoxyController<T> extends AirEpoxyController {
    private T currentData;
    private boolean insideSetData;

    /* access modifiers changed from: protected */
    public abstract void buildModels(T t);

    public void setData(T data) {
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

    /* access modifiers changed from: protected */
    public final void buildModels() {
        buildModels(this.currentData);
    }
}
