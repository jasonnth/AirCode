package com.airbnb.android.listing.utils;

import android.content.Context;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.models.MaxDaysNoticeSetting;
import com.airbnb.android.listing.C7213R;

public class FutureReservationsDisplay {
    public static String getTitle(Context context) {
        return context.getString(getTitleRes());
    }

    public static int getTitleRes() {
        return C7213R.string.manage_listing_availability_settings_future_reservations_title;
    }

    public static int getInfoRes() {
        return C7213R.string.manage_listing_availability_settings_future_reservations_info;
    }

    public static String getShortValue(Context context, MaxDaysNoticeSetting setting) {
        return getValueHelper(context, setting.getDays(), C7213R.string.future_reservations_no_end_date, C7213R.string.calendar_details_blocked, C7213R.plurals.x_months, C7213R.plurals.x_nights);
    }

    public static String getLongValue(Context context, MaxDaysNoticeSetting setting) {
        return getValueHelper(context, setting.getDays(), C7213R.string.future_reservations_available_by_default, C7213R.string.future_reservations_blocked_by_default, C7213R.plurals.x_months_into_the_future, C7213R.plurals.x_days_into_the_future);
    }

    private static String getValueHelper(Context context, int days, int availableRes, int blockedRes, int monthPluralRes, int dayPluralRes) {
        switch (days) {
            case -1:
                return context.getString(availableRes);
            case 0:
                return context.getString(blockedRes);
            case 90:
            case 180:
            case MaxDaysNoticeSetting.MAX_DAYS_NOTICE_9_MONTHS /*270*/:
            case MaxDaysNoticeSetting.MAX_DAYS_NOTICE_1_YEAR /*365*/:
                int months = MaxDaysNoticeSetting.daysToMonths(days);
                if (months > 0) {
                    return context.getResources().getQuantityString(monthPluralRes, months, new Object[]{Integer.valueOf(months)});
                }
                break;
        }
        BugsnagWrapper.throwOrNotify(new RuntimeException("Unexpected turnover_days setting: " + Integer.toString(days)));
        return context.getResources().getQuantityString(dayPluralRes, days, new Object[]{Integer.valueOf(days)});
    }
}
