package com.airbnb.android.core.activities;

import com.airbnb.android.core.views.SuperHeroDismissView.SuperHeroDismissInterface;

final /* synthetic */ class AirActivity$2$$Lambda$2 implements SuperHeroDismissInterface {
    private final C58042 arg$1;

    private AirActivity$2$$Lambda$2(C58042 r1) {
        this.arg$1 = r1;
    }

    public static SuperHeroDismissInterface lambdaFactory$(C58042 r1) {
        return new AirActivity$2$$Lambda$2(r1);
    }

    public void onFlingOrTap() {
        this.arg$1.dismiss();
    }
}
