package com.airbnb.android.listing.utils;

import com.airbnb.android.core.models.CheckInTimeOption;
import com.google.common.collect.FluentIterable;
import java.util.List;

public class CheckInOutUtils {
    private static final int HOUR_BUFFER_MINIMUM = 2;

    public static boolean isValidTimeRange(CheckInTimeOption startTime, CheckInTimeOption endTime) {
        return endTime.isFlexibleTime() || endTime.compareTo(startTime) > 0;
    }

    public static boolean timeRangeMeetsMinimum(CheckInTimeOption startTime, CheckInTimeOption endTime) {
        return endTime.isFlexibleTime() || endTime.compareTo(startTime) >= 2;
    }

    public static String getFormattedHourIfNotNull(Integer time) {
        if (time != null) {
            return time.toString();
        }
        return null;
    }

    public static CheckInTimeOption getTimeOptionForHour(String formattedHour, List<CheckInTimeOption> options) {
        return (CheckInTimeOption) FluentIterable.from((Iterable<E>) options).firstMatch(CheckInOutUtils$$Lambda$1.lambdaFactory$(formattedHour)).mo41058or(CheckInOutUtils$$Lambda$4.lambdaFactory$(formattedHour));
    }

    static /* synthetic */ boolean lambda$getTimeOptionForHour$0(String formattedHour, CheckInTimeOption option) {
        return formattedHour != null && formattedHour.equalsIgnoreCase(option.getFormattedHour());
    }

    static /* synthetic */ CheckInTimeOption lambda$getTimeOptionForHour$1(String formattedHour) {
        CheckInTimeOption timeOption = new CheckInTimeOption();
        timeOption.setFormattedHour(formattedHour);
        return timeOption;
    }
}
