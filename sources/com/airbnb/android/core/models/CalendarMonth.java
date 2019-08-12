package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenCalendarMonth;

public class CalendarMonth extends GenCalendarMonth {
    public static final Creator<CalendarMonth> CREATOR = new Creator<CalendarMonth>() {
        public CalendarMonth[] newArray(int size) {
            return new CalendarMonth[size];
        }

        public CalendarMonth createFromParcel(Parcel source) {
            CalendarMonth object = new CalendarMonth();
            object.readFromParcel(source);
            return object;
        }
    };
}
