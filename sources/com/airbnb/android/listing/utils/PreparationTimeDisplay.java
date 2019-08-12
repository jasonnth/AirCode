package com.airbnb.android.listing.utils;

import android.content.Context;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.models.TurnoverDaysSetting;
import com.airbnb.android.listing.C7213R;

public class PreparationTimeDisplay {
    public static String getTitle(Context context) {
        return context.getString(getTitleRes());
    }

    public static int getTitleRes() {
        return C7213R.string.manage_listing_availability_settings_prep_time_title;
    }

    public static String getInfo(Context context) {
        return context.getString(getInfoRes());
    }

    public static int getInfoRes() {
        return C7213R.string.manage_listing_availability_settings_prep_time_info;
    }

    public static String getShortValue(Context context, TurnoverDaysSetting setting) {
        return getValueHelper(context, setting.getDays(), C7213R.plurals.x_nights);
    }

    public static String getLongValue(Context context, TurnoverDaysSetting setting) {
        return getValueHelper(context, setting.getDays(), C7213R.plurals.at_least_x_days_before_and_after);
    }

    private static String getValueHelper(Context context, int days, int pluralRes) {
        switch (days) {
            case 0:
                return context.getString(C7213R.string.none);
            case 1:
            case 2:
                return context.getResources().getQuantityString(pluralRes, days, new Object[]{Integer.valueOf(days)});
            default:
                BugsnagWrapper.throwOrNotify(new RuntimeException("Invalid turnover_days setting: " + Integer.toString(days)));
                return new String();
        }
    }
}
