package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.interfaces.MinNightsCalendarSetting;
import com.airbnb.android.core.models.generated.GenWeekendMinNightsCalendarSetting;

public class WeekendMinNightsCalendarSetting extends GenWeekendMinNightsCalendarSetting implements MinNightsCalendarSetting {
    public static final Creator<WeekendMinNightsCalendarSetting> CREATOR = new Creator<WeekendMinNightsCalendarSetting>() {
        public WeekendMinNightsCalendarSetting[] newArray(int size) {
            return new WeekendMinNightsCalendarSetting[size];
        }

        public WeekendMinNightsCalendarSetting createFromParcel(Parcel source) {
            WeekendMinNightsCalendarSetting object = new WeekendMinNightsCalendarSetting();
            object.readFromParcel(source);
            return object;
        }
    };
}
