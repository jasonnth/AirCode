package com.airbnb.p027n2.collections;

import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.epoxy.InlineEpoxyController;
import com.airbnb.p027n2.epoxy.InlineEpoxyController.BuildModelsCallback;

/* renamed from: com.airbnb.n2.collections.AirRecyclerView$$Lambda$1 */
final /* synthetic */ class AirRecyclerView$$Lambda$1 implements BuildModelsCallback {
    private final EpoxyModel[] arg$1;

    private AirRecyclerView$$Lambda$1(EpoxyModel[] epoxyModelArr) {
        this.arg$1 = epoxyModelArr;
    }

    public static BuildModelsCallback lambdaFactory$(EpoxyModel[] epoxyModelArr) {
        return new AirRecyclerView$$Lambda$1(epoxyModelArr);
    }

    public void buildModels(InlineEpoxyController inlineEpoxyController) {
        AirRecyclerView.lambda$setStaticModels$0(this.arg$1, inlineEpoxyController);
    }
}
