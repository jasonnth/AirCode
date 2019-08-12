package com.airbnb.p027n2.components;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.n2.components.HomeCardChina$$Lambda$2 */
final /* synthetic */ class HomeCardChina$$Lambda$2 implements OnClickListener {
    private static final HomeCardChina$$Lambda$2 instance = new HomeCardChina$$Lambda$2();

    private HomeCardChina$$Lambda$2() {
    }

    public static OnClickListener lambdaFactory$() {
        return instance;
    }

    public void onClick(View view) {
        HomeCardChina.lambda$mockNoReviewsNewHome$1(view);
    }
}
