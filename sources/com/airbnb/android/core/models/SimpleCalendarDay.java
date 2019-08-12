package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenSimpleCalendarDay;

public class SimpleCalendarDay extends GenSimpleCalendarDay {
    public static final Creator<SimpleCalendarDay> CREATOR = new Creator<SimpleCalendarDay>() {
        public SimpleCalendarDay[] newArray(int size) {
            return new SimpleCalendarDay[size];
        }

        public SimpleCalendarDay createFromParcel(Parcel source) {
            SimpleCalendarDay object = new SimpleCalendarDay();
            object.readFromParcel(source);
            return object;
        }
    };
}
