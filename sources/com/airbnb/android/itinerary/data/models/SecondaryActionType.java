package com.airbnb.android.itinerary.data.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.arguments.P3Arguments;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum SecondaryActionType implements Parcelable {
    Map(P3Arguments.FROM_MAP),
    Deeplink("deeplink"),
    Unknown("");
    
    public static final Creator<SecondaryActionType> CREATOR = null;
    private final String key;

    static {
        CREATOR = new Creator<SecondaryActionType>() {
            public SecondaryActionType createFromParcel(Parcel source) {
                return SecondaryActionType.values()[source.readInt()];
            }

            public SecondaryActionType[] newArray(int size) {
                return new SecondaryActionType[size];
            }
        };
    }

    private SecondaryActionType(String key2) {
        this.key = key2;
    }

    @JsonCreator
    public static SecondaryActionType from(String key2) {
        SecondaryActionType[] values = values();
        int length = values.length;
        for (int i = 0; i < length; i++) {
            SecondaryActionType type = values[i];
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
