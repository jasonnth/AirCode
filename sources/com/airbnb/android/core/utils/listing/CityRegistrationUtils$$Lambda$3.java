package com.airbnb.android.core.utils.listing;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class CityRegistrationUtils$$Lambda$3 implements OnClickListener {
    private final int arg$1;

    private CityRegistrationUtils$$Lambda$3(int i) {
        this.arg$1 = i;
    }

    public static OnClickListener lambdaFactory$(int i) {
        return new CityRegistrationUtils$$Lambda$3(i);
    }

    public void onClick(View view) {
        CityRegistrationUtils.openHelpCenterArticle(view.getContext(), this.arg$1);
    }
}
