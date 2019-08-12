package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenCheckInTimeOption;
import com.airbnb.android.utils.NumberUtils;

public class CheckInTimeOption extends GenCheckInTimeOption implements Comparable<CheckInTimeOption> {
    public static final Creator<CheckInTimeOption> CREATOR = new Creator<CheckInTimeOption>() {
        public CheckInTimeOption[] newArray(int size) {
            return new CheckInTimeOption[size];
        }

        public CheckInTimeOption createFromParcel(Parcel source) {
            CheckInTimeOption object = new CheckInTimeOption();
            object.readFromParcel(source);
            return object;
        }
    };
    public static final String HOUR_FLEXIBLE = "Flexible";

    public boolean isFlexibleTime() {
        return HOUR_FLEXIBLE.equalsIgnoreCase(this.mFormattedHour);
    }

    public int compareTo(CheckInTimeOption other) {
        if (this.mFormattedHour == null || this.mFormattedHour.equals(other.mFormattedHour)) {
            return 0;
        }
        if (!isFlexibleTime() && !other.isFlexibleTime()) {
            return NumberUtils.tryParseInt(this.mFormattedHour, -1) - NumberUtils.tryParseInt(other.mFormattedHour, -1);
        }
        if (other.isFlexibleTime()) {
            return -1;
        }
        return 1;
    }
}
