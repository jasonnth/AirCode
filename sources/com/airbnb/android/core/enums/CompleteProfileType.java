package com.airbnb.android.core.enums;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public enum CompleteProfileType implements Parcelable {
    VERIFIED_ID,
    MANAGE_LISTING,
    EDIT_PROFILE_ADD_PHONE,
    EDIT_PROFILE_EDIT_PHONE,
    EDIT_PROFILE_VERIFY_PHONE;
    
    public static final Creator<CompleteProfileType> CREATOR = null;

    static {
        CREATOR = new Creator<CompleteProfileType>() {
            public CompleteProfileType createFromParcel(Parcel source) {
                return CompleteProfileType.values()[source.readInt()];
            }

            public CompleteProfileType[] newArray(int size) {
                return new CompleteProfileType[size];
            }
        };
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ordinal());
    }

    public boolean isEditProfileType() {
        return this == EDIT_PROFILE_ADD_PHONE || this == EDIT_PROFILE_VERIFY_PHONE || this == EDIT_PROFILE_EDIT_PHONE;
    }

    public boolean isManageListingType() {
        return this == MANAGE_LISTING;
    }
}
