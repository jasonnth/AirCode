package com.airbnb.android.cohosting.fragments;

import com.airbnb.p027n2.components.CityRegistrationToggleRow;
import com.airbnb.p027n2.components.CityRegistrationToggleRow.OnCheckChangedListener;

final /* synthetic */ class CohostingMakePrimaryHostFragment$$Lambda$3 implements OnCheckChangedListener {
    private final CohostingMakePrimaryHostFragment arg$1;

    private CohostingMakePrimaryHostFragment$$Lambda$3(CohostingMakePrimaryHostFragment cohostingMakePrimaryHostFragment) {
        this.arg$1 = cohostingMakePrimaryHostFragment;
    }

    public static OnCheckChangedListener lambdaFactory$(CohostingMakePrimaryHostFragment cohostingMakePrimaryHostFragment) {
        return new CohostingMakePrimaryHostFragment$$Lambda$3(cohostingMakePrimaryHostFragment);
    }

    public void onCheckChanged(CityRegistrationToggleRow cityRegistrationToggleRow, boolean z) {
        this.arg$1.toggleNotification();
    }
}
