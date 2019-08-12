package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenCalendarPricingSettings;

public class CalendarPricingSettings extends GenCalendarPricingSettings {
    public static final Creator<CalendarPricingSettings> CREATOR = new Creator<CalendarPricingSettings>() {
        public CalendarPricingSettings[] newArray(int size) {
            return new CalendarPricingSettings[size];
        }

        public CalendarPricingSettings createFromParcel(Parcel source) {
            CalendarPricingSettings object = new CalendarPricingSettings();
            object.readFromParcel(source);
            return object;
        }
    };
}
