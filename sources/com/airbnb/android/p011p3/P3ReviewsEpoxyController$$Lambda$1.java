package com.airbnb.android.p011p3;

import com.airbnb.android.core.viewcomponents.models.EpoxyControllerLoadingModel_;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.p027n2.components.RefreshLoader;

/* renamed from: com.airbnb.android.p3.P3ReviewsEpoxyController$$Lambda$1 */
final /* synthetic */ class P3ReviewsEpoxyController$$Lambda$1 implements OnModelBoundListener {
    private final P3ReviewsEpoxyController arg$1;

    private P3ReviewsEpoxyController$$Lambda$1(P3ReviewsEpoxyController p3ReviewsEpoxyController) {
        this.arg$1 = p3ReviewsEpoxyController;
    }

    public static OnModelBoundListener lambdaFactory$(P3ReviewsEpoxyController p3ReviewsEpoxyController) {
        return new P3ReviewsEpoxyController$$Lambda$1(p3ReviewsEpoxyController);
    }

    public void onModelBound(EpoxyModel epoxyModel, Object obj, int i) {
        P3ReviewsEpoxyController.lambda$buildModels$0(this.arg$1, (EpoxyControllerLoadingModel_) epoxyModel, (RefreshLoader) obj, i);
    }
}
