package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.models.SeasonalMinNightsCalendarSetting;
import com.google.common.base.Function;

final /* synthetic */ class ManageListingTripLengthAdapter$$Lambda$3 implements Function {
    private final ManageListingTripLengthAdapter arg$1;

    private ManageListingTripLengthAdapter$$Lambda$3(ManageListingTripLengthAdapter manageListingTripLengthAdapter) {
        this.arg$1 = manageListingTripLengthAdapter;
    }

    public static Function lambdaFactory$(ManageListingTripLengthAdapter manageListingTripLengthAdapter) {
        return new ManageListingTripLengthAdapter$$Lambda$3(manageListingTripLengthAdapter);
    }

    public Object apply(Object obj) {
        return this.arg$1.createRowFromSeasonalSetting(this.arg$1.context, (SeasonalMinNightsCalendarSetting) obj);
    }
}
