package com.airbnb.android.managelisting.settings;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.SeasonalMinNightsCalendarSetting;

final /* synthetic */ class ManageListingTripLengthAdapter$$Lambda$4 implements OnClickListener {
    private final ManageListingTripLengthAdapter arg$1;
    private final SeasonalMinNightsCalendarSetting arg$2;

    private ManageListingTripLengthAdapter$$Lambda$4(ManageListingTripLengthAdapter manageListingTripLengthAdapter, SeasonalMinNightsCalendarSetting seasonalMinNightsCalendarSetting) {
        this.arg$1 = manageListingTripLengthAdapter;
        this.arg$2 = seasonalMinNightsCalendarSetting;
    }

    public static OnClickListener lambdaFactory$(ManageListingTripLengthAdapter manageListingTripLengthAdapter, SeasonalMinNightsCalendarSetting seasonalMinNightsCalendarSetting) {
        return new ManageListingTripLengthAdapter$$Lambda$4(manageListingTripLengthAdapter, seasonalMinNightsCalendarSetting);
    }

    public void onClick(View view) {
        this.arg$1.listener.onModifySeasonalRequirement(this.arg$2);
    }
}
