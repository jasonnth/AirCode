package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.airbnb.android.core.models.generated.GenReservationAlert;

public class ReservationAlert extends GenReservationAlert {
    public static final Creator<ReservationAlert> CREATOR = new Creator<ReservationAlert>() {
        public ReservationAlert[] newArray(int size) {
            return new ReservationAlert[size];
        }

        public ReservationAlert createFromParcel(Parcel source) {
            ReservationAlert object = new ReservationAlert();
            object.readFromParcel(source);
            return object;
        }
    };
    private static final String TYPE_SEM = "sem";

    public String getLinkUrl() {
        return "https://m.airbnb.com/" + getLinkPath();
    }

    public String messageWithLink() {
        return String.format("%s <a href=\"%s\">%s</a>", new Object[]{getBody(), getLinkUrl(), getLinkText()});
    }

    public boolean isPayForSEMAlert() {
        return TextUtils.equals(getType(), TYPE_SEM);
    }
}
