package com.airbnb.p027n2.components;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.n2.components.CityRegistrationToggleRow$$Lambda$1 */
final /* synthetic */ class CityRegistrationToggleRow$$Lambda$1 implements OnClickListener {
    private final CityRegistrationToggleRow arg$1;

    private CityRegistrationToggleRow$$Lambda$1(CityRegistrationToggleRow cityRegistrationToggleRow) {
        this.arg$1 = cityRegistrationToggleRow;
    }

    public static OnClickListener lambdaFactory$(CityRegistrationToggleRow cityRegistrationToggleRow) {
        return new CityRegistrationToggleRow$$Lambda$1(cityRegistrationToggleRow);
    }

    public void onClick(View view) {
        this.arg$1.toggle();
    }
}
