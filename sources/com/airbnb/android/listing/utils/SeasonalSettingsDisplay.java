package com.airbnb.android.listing.utils;

import android.content.Context;
import com.airbnb.android.core.models.SeasonalMinNightsCalendarSetting;
import com.airbnb.android.listing.C7213R;

public class SeasonalSettingsDisplay {
    public static int getDayOfWeekOptionsFromIndex(Integer index) {
        if (index == null || index.intValue() == -1) {
            return 0;
        }
        switch (index.intValue()) {
            case 0:
                return C7213R.string.manage_listing_seasonal_settings_check_in_option_sunday;
            case 1:
                return C7213R.string.manage_listing_seasonal_settings_check_in_option_monday;
            case 2:
                return C7213R.string.manage_listing_seasonal_settings_check_in_option_tuesday;
            case 3:
                return C7213R.string.manage_listing_seasonal_settings_check_in_option_wednesday;
            case 4:
                return C7213R.string.manage_listing_seasonal_settings_check_in_option_thursday;
            case 5:
                return C7213R.string.manage_listing_seasonal_settings_check_in_option_friday;
            case 6:
                return C7213R.string.manage_listing_seasonal_settings_check_in_option_saturday;
            default:
                throw new IllegalArgumentException("unknown day of week index: " + index);
        }
    }

    public static String getSeasonalRequirementDescription(Context context, SeasonalMinNightsCalendarSetting setting) {
        Integer dayOfWeek = setting.getStartDayOfWeek();
        if (dayOfWeek == null || dayOfWeek.intValue() == -1) {
            return context.getString(C7213R.string.manage_listing_trip_length_requirement_subtitle_nights, new Object[]{Integer.valueOf(setting.getMinNights())});
        }
        int checkInStartDayRes = getDayOfWeekFromIndex(dayOfWeek.intValue());
        return context.getString(C7213R.string.manage_listing_trip_length_requirement_subtitle_nights_checkin, new Object[]{Integer.valueOf(setting.getMinNights()), context.getString(checkInStartDayRes)});
    }

    private static int getDayOfWeekFromIndex(int index) {
        switch (index) {
            case 0:
                return C7213R.string.sunday;
            case 1:
                return C7213R.string.monday;
            case 2:
                return C7213R.string.tuesday;
            case 3:
                return C7213R.string.wednesday;
            case 4:
                return C7213R.string.thursday;
            case 5:
                return C7213R.string.friday;
            case 6:
                return C7213R.string.saturday;
            default:
                throw new IllegalArgumentException("unknown day of week index: " + index);
        }
    }
}
