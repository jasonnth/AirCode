package com.airbnb.android.itinerary.data.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_PlaceReservationObject extends C$AutoValue_PlaceReservationObject {
    public static final Creator<AutoValue_PlaceReservationObject> CREATOR = new Creator<AutoValue_PlaceReservationObject>() {
        public AutoValue_PlaceReservationObject createFromParcel(Parcel in) {
            return new AutoValue_PlaceReservationObject(in.readString(), in.readInt() == 0 ? in.readString() : null);
        }

        public AutoValue_PlaceReservationObject[] newArray(int size) {
            return new AutoValue_PlaceReservationObject[size];
        }
    };

    AutoValue_PlaceReservationObject(String id, String reservation) {
        super(id, reservation);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mo10312id());
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
