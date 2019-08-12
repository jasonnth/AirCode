package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenReservationMetadata;

public class ReservationMetadata extends GenReservationMetadata {
    public static final Creator<ReservationMetadata> CREATOR = new Creator<ReservationMetadata>() {
        public ReservationMetadata[] newArray(int size) {
            return new ReservationMetadata[size];
        }

        public ReservationMetadata createFromParcel(Parcel source) {
            ReservationMetadata object = new ReservationMetadata();
            object.readFromParcel(source);
            return object;
        }
    };
}
