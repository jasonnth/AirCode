package com.airbnb.android.core.enums;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ListingRegistrationInputType implements Parcelable {
    Address("address"),
    Checkbox("checkbox"),
    DateSelection("date_selection"),
    Dropdown("dropdown"),
    FileUpload("file_upload"),
    Radio("radio"),
    Text("text"),
    Unknown("unknown");
    
    public static final Creator<ListingRegistrationInputType> CREATOR = null;
    private final String serverKey;

    static {
        CREATOR = new Creator<ListingRegistrationInputType>() {
            public ListingRegistrationInputType createFromParcel(Parcel in) {
                return ListingRegistrationInputType.values()[in.readInt()];
            }

            public ListingRegistrationInputType[] newArray(int size) {
                return new ListingRegistrationInputType[size];
            }
        };
    }

    private ListingRegistrationInputType(String serverKey2) {
        this.serverKey = serverKey2.toLowerCase();
    }

    public static ListingRegistrationInputType fromKey(String key) {
        ListingRegistrationInputType[] values;
        for (ListingRegistrationInputType type : values()) {
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

    public boolean isTextInputType() {
        return this == Radio || this == Text || this == Dropdown || this == DateSelection;
    }
}
