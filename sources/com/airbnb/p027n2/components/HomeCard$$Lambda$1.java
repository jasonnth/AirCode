package com.airbnb.p027n2.components;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.n2.components.HomeCard$$Lambda$1 */
final /* synthetic */ class HomeCard$$Lambda$1 implements OnClickListener {
    private static final HomeCard$$Lambda$1 instance = new HomeCard$$Lambda$1();

    private HomeCard$$Lambda$1() {
    }

    public static OnClickListener lambdaFactory$() {
        return instance;
    }

    public void onClick(View view) {
        HomeCard.lambda$mockDefault$0(view);
    }
}
