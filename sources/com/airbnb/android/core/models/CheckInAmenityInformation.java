package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenCheckInAmenityInformation;

public class CheckInAmenityInformation extends GenCheckInAmenityInformation {
    public static final Creator<CheckInAmenityInformation> CREATOR = new Creator<CheckInAmenityInformation>() {
        public CheckInAmenityInformation[] newArray(int size) {
            return new CheckInAmenityInformation[size];
        }

        public CheckInAmenityInformation createFromParcel(Parcel source) {
            CheckInAmenityInformation object = new CheckInAmenityInformation();
            object.readFromParcel(source);
            return object;
        }
    };
}
