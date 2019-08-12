package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.models.SeasonalMinNightsCalendarSetting;
import com.google.common.base.Predicate;

final /* synthetic */ class ManageListingSeasonalCalendarSettingsFragment$$Lambda$3 implements Predicate {
    private final SeasonalMinNightsCalendarSetting arg$1;

    private ManageListingSeasonalCalendarSettingsFragment$$Lambda$3(SeasonalMinNightsCalendarSetting seasonalMinNightsCalendarSetting) {
        this.arg$1 = seasonalMinNightsCalendarSetting;
    }

    public static Predicate lambdaFactory$(SeasonalMinNightsCalendarSetting seasonalMinNightsCalendarSetting) {
        return new ManageListingSeasonalCalendarSettingsFragment$$Lambda$3(seasonalMinNightsCalendarSetting);
    }

    public boolean apply(Object obj) {
        return ManageListingSeasonalCalendarSettingsFragment.lambda$onOptionsItemSelected$2(this.arg$1, (SeasonalMinNightsCalendarSetting) obj);
    }
}
