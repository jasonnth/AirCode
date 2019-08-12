package com.airbnb.p027n2.epoxy;

/* renamed from: com.airbnb.n2.epoxy.Typed3AirEpoxyController */
public abstract class Typed3AirEpoxyController<T, U, V> extends AirEpoxyController {
    private T data1;
    private U data2;
    private V data3;
    private boolean insideSetData;

    /* access modifiers changed from: protected */
    public abstract void buildModels(T t, U u, V v);

    public void setData(T data12, U data22, V data32) {
        this.data1 = data12;
        this.data2 = data22;
        this.data3 = data32;
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
        buildModels(this.data1, this.data2, this.data3);
    }
}