package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenHoursForDisplay;

public class HoursForDisplay extends GenHoursForDisplay {
    public static final Creator<HoursForDisplay> CREATOR = new Creator<HoursForDisplay>() {
        public HoursForDisplay[] newArray(int size) {
            return new HoursForDisplay[size];
        }

        public HoursForDisplay createFromParcel(Parcel source) {
            HoursForDisplay object = new HoursForDisplay();
            object.readFromParcel(source);
            return object;
        }
    };
}
