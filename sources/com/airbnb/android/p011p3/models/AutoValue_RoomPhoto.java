package com.airbnb.android.p011p3.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.airbnb.android.p3.models.AutoValue_RoomPhoto */
final class AutoValue_RoomPhoto extends C$AutoValue_RoomPhoto {
    public static final Creator<AutoValue_RoomPhoto> CREATOR = new Creator<AutoValue_RoomPhoto>() {
        public AutoValue_RoomPhoto createFromParcel(Parcel in) {
            return new AutoValue_RoomPhoto(in.readLong(), in.readString(), in.readString(), in.readString());
        }

        public AutoValue_RoomPhoto[] newArray(int size) {
            return new AutoValue_RoomPhoto[size];
        }
    };

    AutoValue_RoomPhoto(long id, String largeUrl, String previewEncodedPng, String dominantSaturatedColor) {
        super(id, largeUrl, previewEncodedPng, dominantSaturatedColor);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mo11101id());
        dest.writeString(largeUrl());
        dest.writeString(previewEncodedPng());
        dest.writeString(dominantSaturatedColor());
    }

    public int describeContents() {
        return 0;
    }
}
