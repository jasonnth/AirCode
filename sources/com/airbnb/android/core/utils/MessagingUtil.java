package com.airbnb.android.core.utils;

import android.content.Context;
import android.content.res.Resources;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.C0716R;

public class MessagingUtil {
    public static String getRespondWithinTime(Context context, AirDateTime inquiryCreateTime) {
        AirDateTime withinTime = inquiryCreateTime.plusDays(1);
        int minutes = withinTime.minutesFrom(AirDateTime.now());
        Resources res = context.getResources();
        if (minutes <= 0 || ((long) minutes) >= 60) {
            int hours = withinTime.hoursFrom(AirDateTime.now());
            if (hours <= 0 || ((long) hours) >= 24) {
                return context.getString(C0716R.string.response_overdue);
            }
            int minutesLeft = minutes % 60;
            if (minutesLeft == 0) {
                return res.getQuantityString(C0716R.plurals.respond_within_x_hrs, hours, new Object[]{Integer.valueOf(hours)});
            }
            String minutesLeftString = res.getQuantityString(C0716R.plurals.x_mins, minutesLeft, new Object[]{Integer.valueOf(minutesLeft)});
            String hoursLeftString = res.getQuantityString(C0716R.plurals.x_hrs, hours, new Object[]{Integer.valueOf(hours)});
            return res.getString(C0716R.string.respond_within_x_hrs_mins, new Object[]{hoursLeftString, minutesLeftString});
        }
        return res.getQuantityString(C0716R.plurals.respond_within_x_mins, minutes, new Object[]{Integer.valueOf(minutes)});
    }
}
