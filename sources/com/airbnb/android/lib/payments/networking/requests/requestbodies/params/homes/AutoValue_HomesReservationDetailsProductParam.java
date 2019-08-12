package com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_HomesReservationDetailsProductParam extends C$AutoValue_HomesReservationDetailsProductParam {
    public static final Creator<AutoValue_HomesReservationDetailsProductParam> CREATOR = new Creator<AutoValue_HomesReservationDetailsProductParam>() {
        public AutoValue_HomesReservationDetailsProductParam createFromParcel(Parcel in) {
            return new AutoValue_HomesReservationDetailsProductParam(in.readInt() == 0 ? in.readString() : null);
        }

        public AutoValue_HomesReservationDetailsProductParam[] newArray(int size) {
            return new AutoValue_HomesReservationDetailsProductParam[size];
        }
    };

    AutoValue_HomesReservationDetailsProductParam(String messageToHost) {
        super(messageToHost);
    }

    public void writeToParcel(Parcel dest, int flags) {
        if (messageToHost() == null) {
            dest.writeInt(1);
            return;
        }
        dest.writeInt(0);
        dest.writeString(messageToHost());
    }

    public int describeContents() {
        return 0;
    }
}
