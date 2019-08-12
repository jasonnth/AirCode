package com.airbnb.p027n2.components;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.n2.components.HomeCard$$Lambda$2 */
final /* synthetic */ class HomeCard$$Lambda$2 implements OnClickListener {
    private static final HomeCard$$Lambda$2 instance = new HomeCard$$Lambda$2();

    private HomeCard$$Lambda$2() {
    }

    public static OnClickListener lambdaFactory$() {
        return instance;
    }

    public void onClick(View view) {
        HomeCard.lambda$mockNoReviewsNewHome$1(view);
    }
}
