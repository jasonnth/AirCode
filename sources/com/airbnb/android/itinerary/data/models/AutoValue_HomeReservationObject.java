package com.airbnb.android.itinerary.data.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_HomeReservationObject extends C$AutoValue_HomeReservationObject {
    public static final Creator<AutoValue_HomeReservationObject> CREATOR = new Creator<AutoValue_HomeReservationObject>() {
        public AutoValue_HomeReservationObject createFromParcel(Parcel in) {
            return new AutoValue_HomeReservationObject(in.readString(), in.readInt() == 0 ? in.readString() : null);
        }

        public AutoValue_HomeReservationObject[] newArray(int size) {
            return new AutoValue_HomeReservationObject[size];
        }
    };

    AutoValue_HomeReservationObject(String id, String reservation) {
        super(id, reservation);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mo10305id());
        if (reservation() == null) {
            dest.writeInt(1);
            return;
        }
        dest.writeInt(0);
        dest.writeString(reservation());
    }

    public int describeContents() {
        return 0;
    }
}
