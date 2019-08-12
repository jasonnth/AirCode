package com.airbnb.android.core.messaging.p007db;

import com.airbnb.android.airdate.AirDateTime;

/* renamed from: com.airbnb.android.core.messaging.db.AirDateTimeConverter */
final class AirDateTimeConverter {
    AirDateTimeConverter() {
    }

    static Long convert(AirDateTime airDateTime) {
        if (airDateTime == null) {
            return null;
        }
        return Long.valueOf(airDateTime.getMillis());
    }

    static String convertToString(AirDateTime airDateTime) {
        Long converted = convert(airDateTime);
        if (converted == null) {
            return null;
        }
        return Long.toString(converted.longValue());
    }

    static boolean equals(AirDateTime airDateTime, Long aLong) {
        Long airDateTimeLong = convert(airDateTime);
        if (airDateTimeLong == null && aLong == null) {
            return true;
        }
        if (airDateTimeLong == null || aLong == null) {
            return false;
        }
        return airDateTimeLong.equals(aLong);
    }
}
