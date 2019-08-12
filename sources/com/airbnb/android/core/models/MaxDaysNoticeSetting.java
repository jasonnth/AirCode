package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.models.generated.GenMaxDaysNoticeSetting;

public class MaxDaysNoticeSetting extends GenMaxDaysNoticeSetting {
    public static final Creator<MaxDaysNoticeSetting> CREATOR = new Creator<MaxDaysNoticeSetting>() {
        public MaxDaysNoticeSetting[] newArray(int size) {
            return new MaxDaysNoticeSetting[size];
        }

        public MaxDaysNoticeSetting createFromParcel(Parcel source) {
            MaxDaysNoticeSetting object = new MaxDaysNoticeSetting();
            object.readFromParcel(source);
            return object;
        }
    };
    public static final int MAX_DAYS_NOTICE_1_YEAR = 365;
    public static final int MAX_DAYS_NOTICE_3_MONTHS = 90;
    public static final int MAX_DAYS_NOTICE_6_MONTHS = 180;
    public static final int MAX_DAYS_NOTICE_9_MONTHS = 270;
    public static final int MAX_DAYS_NOTICE_ALL_FUTURE_DATES_AVAILABLE = -1;
    public static final int MAX_DAYS_NOTICE_BLOCKED_BY_DEFAULT = 0;

    public static MaxDaysNoticeSetting newInstance(int days) {
        MaxDaysNoticeSetting option = new MaxDaysNoticeSetting();
        option.setDays(days);
        return option;
    }

    public static MaxDaysNoticeSetting[] values() {
        if (FeatureToggles.allow9MonthBookingWindow()) {
            return new MaxDaysNoticeSetting[]{newInstance(0), newInstance(90), newInstance(180), newInstance(MAX_DAYS_NOTICE_9_MONTHS), newInstance(MAX_DAYS_NOTICE_1_YEAR), newInstance(-1)};
        }
        return new MaxDaysNoticeSetting[]{newInstance(0), newInstance(90), newInstance(180), newInstance(MAX_DAYS_NOTICE_1_YEAR), newInstance(-1)};
    }

    public static int daysToMonths(int days) {
        switch (days) {
            case 90:
                return 3;
            case 180:
                return 6;
            case MAX_DAYS_NOTICE_9_MONTHS /*270*/:
                return 9;
            case MAX_DAYS_NOTICE_1_YEAR /*365*/:
                return 12;
            default:
                return -1;
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (this.mDays != ((MaxDaysNoticeSetting) o).mDays) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.mDays;
    }
}
