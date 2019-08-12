package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.UnhandledStateException;
import com.airbnb.android.core.enums.InstantBookAdvanceNotice;
import com.airbnb.android.core.models.generated.GenAdvanceNoticeSetting;
import com.airbnb.android.utils.ListUtils;
import com.google.common.collect.FluentIterable;

public class AdvanceNoticeSetting extends GenAdvanceNoticeSetting {
    private static final int ALLOW_RTB_FALSE = 0;
    private static final int ALLOW_RTB_TRUE = 1;
    public static final Creator<AdvanceNoticeSetting> CREATOR = new Creator<AdvanceNoticeSetting>() {
        public AdvanceNoticeSetting[] newArray(int size) {
            return new AdvanceNoticeSetting[size];
        }

        public AdvanceNoticeSetting createFromParcel(Parcel source) {
            AdvanceNoticeSetting object = new AdvanceNoticeSetting();
            object.readFromParcel(source);
            return object;
        }
    };
    public static final int EARLIEST_CUTOFF_HOURS_BEFORE_MIDNIGHT_VALUE = 18;
    public static final int LATEST_CUTOFF_HOURS_BEFORE_MIDNIGHT_VALUE = 0;
    public static final int SAME_DAY_UNSET_HOUR = -1;

    public AdvanceNoticeSetting() {
    }

    public AdvanceNoticeSetting(int leadtime) {
        super(leadtime, 0);
    }

    public static AdvanceNoticeSetting[] daysValues() {
        return new AdvanceNoticeSetting[]{new AdvanceNoticeSetting(2), new AdvanceNoticeSetting(24), new AdvanceNoticeSetting(48), new AdvanceNoticeSetting(72), new AdvanceNoticeSetting(168)};
    }

    public static AdvanceNoticeSetting[] sameDayHoursValues() {
        return (AdvanceNoticeSetting[]) FluentIterable.m1282of(Integer.valueOf(0), new Integer[0]).append((E[]) ListUtils.range(18, 1)).transform(AdvanceNoticeSetting$$Lambda$1.lambdaFactory$()).toArray(AdvanceNoticeSetting.class);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (getDays() != ((AdvanceNoticeSetting) o).getDays()) {
            return false;
        }
        return true;
    }

    public int getNumDays() {
        InstantBookAdvanceNotice days = getDays();
        switch (days) {
            case SameDay:
                return 0;
            case One:
                return 1;
            case Two:
                return 2;
            case Three:
                return 3;
            case Seven:
                return 7;
            default:
                throw new UnhandledStateException(days);
        }
    }

    public InstantBookAdvanceNotice getDays() {
        return InstantBookAdvanceNotice.getTypeFromKey(getHours());
    }

    public int getHourOfDay() {
        return convertLeadTimeToHourOfDay(getHours());
    }

    public static int convertLeadTimeToHourOfDay(int hours) {
        return 24 - hours;
    }

    public boolean isSameDay() {
        return getDays() == InstantBookAdvanceNotice.SameDay;
    }

    public boolean getAllowRtbBoolean() {
        return getAllowRequestToBook() == 1;
    }

    public void setAllowRtbBoolean(boolean allowRtbBoolean) {
        setAllowRequestToBook(allowRtbBoolean ? 1 : 0);
    }

    public int hashCode() {
        return this.mHours;
    }
}
