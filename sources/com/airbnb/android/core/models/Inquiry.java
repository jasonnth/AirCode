package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenInquiry;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Inquiry extends GenInquiry {
    public static final Creator<Inquiry> CREATOR = new Creator<Inquiry>() {
        public Inquiry[] newArray(int size) {
            return new Inquiry[size];
        }

        public Inquiry createFromParcel(Parcel source) {
            Inquiry object = new Inquiry();
            object.readFromParcel(source);
            return object;
        }
    };

    @JsonProperty("status")
    public void setStatus(String statusString) {
        this.mReservationStatus = ReservationStatus.findStatus(statusString);
    }
}
