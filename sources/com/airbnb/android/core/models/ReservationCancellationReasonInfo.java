package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenReservationCancellationReasonInfo;

public class ReservationCancellationReasonInfo extends GenReservationCancellationReasonInfo {
    public static final Creator<ReservationCancellationReasonInfo> CREATOR = new Creator<ReservationCancellationReasonInfo>() {
        public ReservationCancellationReasonInfo[] newArray(int size) {
            return new ReservationCancellationReasonInfo[size];
        }

        public ReservationCancellationReasonInfo createFromParcel(Parcel source) {
            ReservationCancellationReasonInfo object = new ReservationCancellationReasonInfo();
            object.readFromParcel(source);
            return object;
        }
    };
}
