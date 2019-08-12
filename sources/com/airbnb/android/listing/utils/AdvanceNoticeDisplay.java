package com.airbnb.android.listing.utils;

import android.content.Context;
import com.airbnb.android.core.models.AdvanceNoticeSetting;
import com.airbnb.android.core.utils.DateHelper;
import com.airbnb.android.listing.C7213R;

public class AdvanceNoticeDisplay {
    public static String getDaysTitle(Context context) {
        return context.getString(getDaysTitleRes());
    }

    public static int getDaysTitleRes() {
        return C7213R.string.manage_listing_availability_settings_advance_notice_title;
    }

    public static String getDaysInfo(Context context) {
        return context.getString(getDaysInfoRes());
    }

    public static int getDaysInfoRes() {
        return C7213R.string.manage_listing_availability_settings_advance_notice_info;
    }

    public static String getDaysShortValue(Context context, AdvanceNoticeSetting setting) {
        int numDays = setting.getNumDays();
        if (numDays == 0) {
            return context.getString(C7213R.string.f9698x2c8971ce);
        }
        return context.getResources().getQuantityString(C7213R.plurals.x_days, numDays, new Object[]{Integer.valueOf(numDays)});
    }

    public static String getDaysLongValue(Context context, AdvanceNoticeSetting setting) {
        int numDays = setting.getNumDays();
        if (numDays == 0) {
            return context.getString(C7213R.string.f9698x2c8971ce);
        }
        return context.getResources().getQuantityString(C7213R.plurals.at_least_x_days_notice, numDays, new Object[]{Integer.valueOf(numDays)});
    }

    public static String getCutoffTimeTitle(Context context) {
        return context.getString(getCutoffTimeTitleRes());
    }

    public static int getCutoffTimeTitleRes() {
        return C7213R.string.manage_listing_availability_settings_cutoff_time_title;
    }

    public static String getCutoffTimeInfo(Context context) {
        return context.getString(getCutoffTimeInfoRes());
    }

    public static int getCutoffTimeInfoRes() {
        return C7213R.string.manage_listing_availability_settings_cutoff_time_info;
    }

    public static String getHoursValue(Context context, AdvanceNoticeSetting setting) {
        if (setting.getHours() == 0 || setting.getHours() == -1) {
            return context.getString(C7213R.string.manage_listing_availability_settings_cutoff_time_any_time);
        }
        return DateHelper.formatHourOfDay(context, setting.getHourOfDay(), true);
    }

    public static String getRequestToBookTitle(Context context) {
        return context.getString(getRequestToBookTitleRes());
    }

    public static int getRequestToBookTitleRes() {
        return C7213R.string.manage_listing_availability_settings_reservation_requests_title;
    }

    public static String getRequestToBookInfo(Context context, AdvanceNoticeSetting setting) {
        return context.getResources().getQuantityString(C7213R.plurals.x_days_notice_request_to_book, setting.getNumDays(), new Object[]{Integer.valueOf(setting.getNumDays())});
    }

    public static String getRequestToBookValue(Context context, AdvanceNoticeSetting setting) {
        return getRequestToBookValue(context, setting.getAllowRtbBoolean());
    }

    public static String getRequestToBookValue(Context context, boolean allowRtb) {
        return allowRtb ? context.getString(C7213R.string.yes) : context.getString(C7213R.string.f9720no);
    }
}
