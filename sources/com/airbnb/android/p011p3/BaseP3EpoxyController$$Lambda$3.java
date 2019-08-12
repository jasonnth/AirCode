package com.airbnb.android.p011p3;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.Review;

/* renamed from: com.airbnb.android.p3.BaseP3EpoxyController$$Lambda$3 */
final /* synthetic */ class BaseP3EpoxyController$$Lambda$3 implements OnClickListener {
    private final BaseP3EpoxyController arg$1;
    private final Review arg$2;

    private BaseP3EpoxyController$$Lambda$3(BaseP3EpoxyController baseP3EpoxyController, Review review) {
        this.arg$1 = baseP3EpoxyController;
        this.arg$2 = review;
    }

    public static OnClickListener lambdaFactory$(BaseP3EpoxyController baseP3EpoxyController, Review review) {
        return new BaseP3EpoxyController$$Lambda$3(baseP3EpoxyController, review);
    }

    public void onClick(View view) {
        this.arg$1.controller.getReviewsController().toggleTranslationState(this.arg$2);
    }
}
