package com.airbnb.p027n2.components;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.n2.components.HomeCard$$Lambda$3 */
final /* synthetic */ class HomeCard$$Lambda$3 implements OnClickListener {
    private static final HomeCard$$Lambda$3 instance = new HomeCard$$Lambda$3();

    private HomeCard$$Lambda$3() {
    }

    public static OnClickListener lambdaFactory$() {
        return instance;
    }

    public void onClick(View view) {
        HomeCard.lambda$mockNoReviews$2(view);
    }
}
