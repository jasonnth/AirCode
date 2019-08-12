package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenReservationCancellationRefundBreakdown;

public class ReservationCancellationRefundBreakdown extends GenReservationCancellationRefundBreakdown {
    public static final Creator<ReservationCancellationRefundBreakdown> CREATOR = new Creator<ReservationCancellationRefundBreakdown>() {
        public ReservationCancellationRefundBreakdown[] newArray(int size) {
            return new ReservationCancellationRefundBreakdown[size];
        }

        public ReservationCancellationRefundBreakdown createFromParcel(Parcel source) {
            ReservationCancellationRefundBreakdown object = new ReservationCancellationRefundBreakdown();
            object.readFromParcel(source);
            return object;
        }
    };
}
