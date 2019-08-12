package com.airbnb.android.p011p3;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.Review;

/* renamed from: com.airbnb.android.p3.P3ReviewsEpoxyController$$Lambda$2 */
final /* synthetic */ class P3ReviewsEpoxyController$$Lambda$2 implements OnClickListener {
    private final P3ReviewsEpoxyController arg$1;
    private final Review arg$2;

    private P3ReviewsEpoxyController$$Lambda$2(P3ReviewsEpoxyController p3ReviewsEpoxyController, Review review) {
        this.arg$1 = p3ReviewsEpoxyController;
        this.arg$2 = review;
    }

    public static OnClickListener lambdaFactory$(P3ReviewsEpoxyController p3ReviewsEpoxyController, Review review) {
        return new P3ReviewsEpoxyController$$Lambda$2(p3ReviewsEpoxyController, review);
    }

    public void onClick(View view) {
        this.arg$1.controller.toggleTranslationState(this.arg$2);
    }
}
