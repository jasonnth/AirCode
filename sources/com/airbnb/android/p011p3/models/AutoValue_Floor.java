package com.airbnb.android.p011p3.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.List;

/* renamed from: com.airbnb.android.p3.models.AutoValue_Floor */
final class AutoValue_Floor extends C$AutoValue_Floor {
    public static final Creator<AutoValue_Floor> CREATOR = new Creator<AutoValue_Floor>() {
        public AutoValue_Floor createFromParcel(Parcel in) {
            return new AutoValue_Floor(in.readLong(), in.readString(), in.readArrayList(Room.class.getClassLoader()));
        }

        public AutoValue_Floor[] newArray(int size) {
            return new AutoValue_Floor[size];
        }
    };

    AutoValue_Floor(long id, String name, List<Room> rooms) {
        super(id, name, rooms);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mo11014id());
        dest.writeString(name());
        dest.writeList(rooms());
    }

    public int describeContents() {
        return 0;
    }
}
