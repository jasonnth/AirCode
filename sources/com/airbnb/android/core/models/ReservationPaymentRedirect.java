package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenReservationPaymentRedirect;

public class ReservationPaymentRedirect extends GenReservationPaymentRedirect {
    public static final Creator<ReservationPaymentRedirect> CREATOR = new Creator<ReservationPaymentRedirect>() {
        public ReservationPaymentRedirect[] newArray(int size) {
            return new ReservationPaymentRedirect[size];
        }

        public ReservationPaymentRedirect createFromParcel(Parcel source) {
            ReservationPaymentRedirect object = new ReservationPaymentRedirect();
            object.readFromParcel(source);
            return object;
        }
    };
}
