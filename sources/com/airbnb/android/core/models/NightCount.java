package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenNightCount;

public class NightCount extends GenNightCount {
    public static final Creator<NightCount> CREATOR = new Creator<NightCount>() {
        public NightCount[] newArray(int size) {
            return new NightCount[size];
        }

        public NightCount createFromParcel(Parcel source) {
            NightCount object = new NightCount();
            object.readFromParcel(source);
            return object;
        }
    };

    public boolean hasExceededAllowedNights() {
        return this.mMaxNights <= this.mNightsBooked;
    }
}
