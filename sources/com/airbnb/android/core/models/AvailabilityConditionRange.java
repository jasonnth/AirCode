package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenAvailabilityConditionRange;

public class AvailabilityConditionRange extends GenAvailabilityConditionRange {
    public static final Creator<AvailabilityConditionRange> CREATOR = new Creator<AvailabilityConditionRange>() {
        public AvailabilityConditionRange[] newArray(int size) {
            return new AvailabilityConditionRange[size];
        }

        public AvailabilityConditionRange createFromParcel(Parcel source) {
            AvailabilityConditionRange object = new AvailabilityConditionRange();
            object.readFromParcel(source);
            return object;
        }
    };
}
