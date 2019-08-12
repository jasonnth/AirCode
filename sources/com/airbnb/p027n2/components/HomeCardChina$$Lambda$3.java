package com.airbnb.p027n2.components;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.n2.components.HomeCardChina$$Lambda$3 */
final /* synthetic */ class HomeCardChina$$Lambda$3 implements OnClickListener {
    private static final HomeCardChina$$Lambda$3 instance = new HomeCardChina$$Lambda$3();

    private HomeCardChina$$Lambda$3() {
    }

    public static OnClickListener lambdaFactory$() {
        return instance;
    }

    public void onClick(View view) {
        HomeCardChina.lambda$mockNoReviews$2(view);
    }
}
