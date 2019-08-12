package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenCheckInGuideReservation;

public class CheckInGuideReservation extends GenCheckInGuideReservation {
    public static final Creator<CheckInGuideReservation> CREATOR = new Creator<CheckInGuideReservation>() {
        public CheckInGuideReservation[] newArray(int size) {
            return new CheckInGuideReservation[size];
        }

        public CheckInGuideReservation createFromParcel(Parcel source) {
            CheckInGuideReservation object = new CheckInGuideReservation();
            object.readFromParcel(source);
            return object;
        }
    };
}
