package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenAvailabilityCondition;

public class AvailabilityCondition extends GenAvailabilityCondition {
    public static final Creator<AvailabilityCondition> CREATOR = new Creator<AvailabilityCondition>() {
        public AvailabilityCondition[] newArray(int size) {
            return new AvailabilityCondition[size];
        }

        public AvailabilityCondition createFromParcel(Parcel source) {
            AvailabilityCondition object = new AvailabilityCondition();
            object.readFromParcel(source);
            return object;
        }
    };
}
