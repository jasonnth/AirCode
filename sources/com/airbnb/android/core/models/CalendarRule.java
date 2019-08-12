package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenCalendarRule;
import java.util.ArrayList;

public class CalendarRule extends GenCalendarRule {
    public static final Creator<CalendarRule> CREATOR = new Creator<CalendarRule>() {
        public CalendarRule[] newArray(int size) {
            return new CalendarRule[size];
        }

        public CalendarRule createFromParcel(Parcel source) {
            CalendarRule object = new CalendarRule();
            object.readFromParcel(source);
            return object;
        }
    };

    public ArrayList<SeasonalMinNightsCalendarSetting> getSeasonalCalendarSettings() {
        if (this.mSeasonalCalendarSettings == null) {
            this.mSeasonalCalendarSettings = new ArrayList();
        }
        return this.mSeasonalCalendarSettings;
    }

    public WeekendMinNightsCalendarSetting getWeekendMinNightsSetting() {
        if (this.mWeekendMinNightsSetting == null) {
            this.mWeekendMinNightsSetting = new WeekendMinNightsCalendarSetting();
        }
        return this.mWeekendMinNightsSetting;
    }
}
