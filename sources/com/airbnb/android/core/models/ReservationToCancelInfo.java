package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenReservationToCancelInfo;

public class ReservationToCancelInfo extends GenReservationToCancelInfo {
    public static final Creator<ReservationToCancelInfo> CREATOR = new Creator<ReservationToCancelInfo>() {
        public ReservationToCancelInfo[] newArray(int size) {
            return new ReservationToCancelInfo[size];
        }

        public ReservationToCancelInfo createFromParcel(Parcel source) {
            ReservationToCancelInfo object = new ReservationToCancelInfo();
            object.readFromParcel(source);
            return object;
        }
    };
}
