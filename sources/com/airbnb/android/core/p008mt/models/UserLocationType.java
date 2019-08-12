package com.airbnb.android.core.p008mt.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonCreator;

/* renamed from: com.airbnb.android.core.mt.models.UserLocationType */
public enum UserLocationType implements Parcelable {
    Local("local"),
    TravelerOnTrip("traveler_on_trip"),
    UpcomingTrip("upcoming_trip"),
    Unknown("");
    
    public static final Creator<UserLocationType> CREATOR = null;
    private final String key;

    static {
        CREATOR = new Creator<UserLocationType>() {
            public UserLocationType createFromParcel(Parcel source) {
                return UserLocationType.values()[source.readInt()];
            }

            public UserLocationType[] newArray(int size) {
                return new UserLocationType[size];
            }
        };
    }

    private UserLocationType(String key2) {
        this.key = key2;
    }

    @JsonCreator
    public static UserLocationType from(String givenKey) {
        UserLocationType[] values;
        for (UserLocationType type : values()) {
            if (type.key.equals(givenKey)) {
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
