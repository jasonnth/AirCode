package com.airbnb.android.core.enums;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.airbnb.android.itinerary.TripEventModel;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ListingRegistrationContentStyle implements Parcelable {
    Bullet("bullet"),
    Checkbox("checkbox"),
    Paragraph("paragraph"),
    Header(TripEventModel.HEADER),
    NumberList("number_list"),
    Checkmark("checkmark"),
    Unknown("unknown");
    
    public static final Creator<ListingRegistrationContentStyle> CREATOR = null;
    private final String serverKey;

    static {
        CREATOR = new Creator<ListingRegistrationContentStyle>() {
            public ListingRegistrationContentStyle createFromParcel(Parcel in) {
                return ListingRegistrationContentStyle.values()[in.readInt()];
            }

            public ListingRegistrationContentStyle[] newArray(int size) {
                return new ListingRegistrationContentStyle[size];
            }
        };
    }

    private ListingRegistrationContentStyle(String serverKey2) {
        this.serverKey = serverKey2.toLowerCase();
    }

    public static ListingRegistrationContentStyle fromKey(String key) {
        ListingRegistrationContentStyle[] values;
        for (ListingRegistrationContentStyle type : values()) {
            if (key.equalsIgnoreCase(type.serverKey)) {
                return type;
            }
        }
        return Unknown;
    }

    @JsonValue
    public String getServerKey() {
        return this.serverKey;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ordinal());
    }
}
