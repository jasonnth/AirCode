package com.airbnb.android.itinerary.data.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum SecondaryActionButtonType implements Parcelable {
    Airmoji("airmoji"),
    Text("text"),
    Unknown("");
    
    public static final Creator<SecondaryActionButtonType> CREATOR = null;
    private final String key;

    static {
        CREATOR = new Creator<SecondaryActionButtonType>() {
            public SecondaryActionButtonType createFromParcel(Parcel source) {
                return SecondaryActionButtonType.values()[source.readInt()];
            }

            public SecondaryActionButtonType[] newArray(int size) {
                return new SecondaryActionButtonType[size];
            }
        };
    }

    private SecondaryActionButtonType(String key2) {
        this.key = key2;
    }

    @JsonCreator
    public static SecondaryActionButtonType from(String key2) {
        SecondaryActionButtonType[] values = values();
        int length = values.length;
        for (int i = 0; i < length; i++) {
            SecondaryActionButtonType type = values[i];
            if (type.getKey().equals(key2) || type.name().equals(key2)) {
                return type;
            }
        }
        return Unknown;
    }

    public String getKey() {
        return this.key;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ordinal());
    }
}
