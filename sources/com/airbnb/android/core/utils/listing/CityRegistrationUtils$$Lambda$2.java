package com.airbnb.android.core.utils.listing;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class CityRegistrationUtils$$Lambda$2 implements OnClickListener {
    private final String arg$1;

    private CityRegistrationUtils$$Lambda$2(String str) {
        this.arg$1 = str;
    }

    public static OnClickListener lambdaFactory$(String str) {
        return new CityRegistrationUtils$$Lambda$2(str);
    }

    public void onClick(View view) {
        CityRegistrationUtils.openCityRegistrationUrl(view.getContext(), this.arg$1);
    }
}
