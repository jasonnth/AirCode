package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenPlaceReservation;

public class PlaceReservation extends GenPlaceReservation {
    public static final Creator<PlaceReservation> CREATOR = new Creator<PlaceReservation>() {
        public PlaceReservation[] newArray(int size) {
            return new PlaceReservation[size];
        }

        public PlaceReservation createFromParcel(Parcel source) {
            PlaceReservation object = new PlaceReservation();
            object.readFromParcel(source);
            return object;
        }
    };
}
