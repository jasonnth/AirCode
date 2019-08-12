package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenTimeType;

public class TimeType extends GenTimeType {
    public static final Creator<TimeType> CREATOR = new Creator<TimeType>() {
        public TimeType[] newArray(int size) {
            return new TimeType[size];
        }

        public TimeType createFromParcel(Parcel source) {
            TimeType object = new TimeType();
            object.readFromParcel(source);
            return object;
        }
    };
}
