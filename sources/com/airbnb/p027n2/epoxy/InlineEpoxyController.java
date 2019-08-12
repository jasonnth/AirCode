package com.airbnb.p027n2.epoxy;

/* renamed from: com.airbnb.n2.epoxy.InlineEpoxyController */
public class InlineEpoxyController extends AirEpoxyController {
    private final BuildModelsCallback callback;

    /* renamed from: com.airbnb.n2.epoxy.InlineEpoxyController$BuildModelsCallback */
    public interface BuildModelsCallback {
        void buildModels(InlineEpoxyController inlineEpoxyController);
    }

    public InlineEpoxyController(BuildModelsCallback callback2) {
        this.callback = callback2;
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        this.callback.buildModels(this);
    }
}
