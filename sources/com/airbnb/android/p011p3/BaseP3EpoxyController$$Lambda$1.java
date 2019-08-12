package com.airbnb.android.p011p3;

import com.airbnb.android.core.models.Review;
import com.google.common.base.Function;

/* renamed from: com.airbnb.android.p3.BaseP3EpoxyController$$Lambda$1 */
final /* synthetic */ class BaseP3EpoxyController$$Lambda$1 implements Function {
    private final BaseP3EpoxyController arg$1;

    private BaseP3EpoxyController$$Lambda$1(BaseP3EpoxyController baseP3EpoxyController) {
        this.arg$1 = baseP3EpoxyController;
    }

    public static Function lambdaFactory$(BaseP3EpoxyController baseP3EpoxyController) {
        return new BaseP3EpoxyController$$Lambda$1(baseP3EpoxyController);
    }

    public Object apply(Object obj) {
        return this.arg$1.buildReviewRowModel((Review) obj);
    }
}
