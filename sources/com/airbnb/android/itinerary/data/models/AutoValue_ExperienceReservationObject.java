package com.airbnb.android.itinerary.data.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_ExperienceReservationObject extends C$AutoValue_ExperienceReservationObject {
    public static final Creator<AutoValue_ExperienceReservationObject> CREATOR = new Creator<AutoValue_ExperienceReservationObject>() {
        public AutoValue_ExperienceReservationObject createFromParcel(Parcel in) {
            return new AutoValue_ExperienceReservationObject(in.readString(), in.readInt() == 0 ? in.readString() : null);
        }

        public AutoValue_ExperienceReservationObject[] newArray(int size) {
            return new AutoValue_ExperienceReservationObject[size];
        }
    };

    AutoValue_ExperienceReservationObject(String id, String reservation) {
        super(id, reservation);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mo10298id());
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
