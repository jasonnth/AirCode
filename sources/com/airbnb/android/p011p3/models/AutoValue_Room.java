package com.airbnb.android.p011p3.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.List;

/* renamed from: com.airbnb.android.p3.models.AutoValue_Room */
final class AutoValue_Room extends C$AutoValue_Room {
    public static final Creator<AutoValue_Room> CREATOR = new Creator<AutoValue_Room>() {
        public AutoValue_Room createFromParcel(Parcel in) {
            return new AutoValue_Room(in.readLong(), in.readString(), in.readArrayList(String.class.getClassLoader()), in.readArrayList(RoomPhoto.class.getClassLoader()), in.readArrayList(RoomDetail.class.getClassLoader()));
        }

        public AutoValue_Room[] newArray(int size) {
            return new AutoValue_Room[size];
        }
    };

    AutoValue_Room(long id, String name, List<String> highlights, List<RoomPhoto> photos, List<RoomDetail> details) {
        super(id, name, highlights, photos, details);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mo11086id());
        dest.writeString(name());
        dest.writeList(highlights());
        dest.writeList(photos());
        dest.writeList(details());
    }

    public int describeContents() {
        return 0;
    }
}
