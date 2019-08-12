package com.airbnb.android.p011p3.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.airbnb.android.p3.models.AutoValue_RoomDetail */
final class AutoValue_RoomDetail extends C$AutoValue_RoomDetail {
    public static final Creator<AutoValue_RoomDetail> CREATOR = new Creator<AutoValue_RoomDetail>() {
        public AutoValue_RoomDetail createFromParcel(Parcel in) {
            return new AutoValue_RoomDetail((RoomPhoto) in.readParcelable(RoomPhoto.class.getClassLoader()), in.readString());
        }

        public AutoValue_RoomDetail[] newArray(int size) {
            return new AutoValue_RoomDetail[size];
        }
    };

    AutoValue_RoomDetail(RoomPhoto photo, String caption) {
        super(photo, caption);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(photo(), flags);
        dest.writeString(caption());
    }

    public int describeContents() {
        return 0;
    }
}
