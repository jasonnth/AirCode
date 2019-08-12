package com.airbnb.android.core.enums;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public enum ListingRegistrationStatus implements Parcelable {
    Unregistered("unregistered"),
    SubmittedPendingAirbnbApproval("submitted"),
    SubmittedPendingCityApproval("sent"),
    Approved("approved"),
    Denied("denied_pending"),
    DeniedFinal("denied"),
    Exempted("exempted"),
    Appealed("appealed");
    
    public static final Creator<ListingRegistrationStatus> CREATOR = null;
    private final String serverKey;

    static {
        CREATOR = new Creator<ListingRegistrationStatus>() {
            public ListingRegistrationStatus createFromParcel(Parcel in) {
                return ListingRegistrationStatus.values()[in.readInt()];
            }

            public ListingRegistrationStatus[] newArray(int size) {
                return new ListingRegistrationStatus[size];
            }
        };
    }

    private ListingRegistrationStatus(String serverKey2) {
        this.serverKey = serverKey2;
    }

    public static ListingRegistrationStatus fromKey(String key) {
        ListingRegistrationStatus[] values;
        for (ListingRegistrationStatus status : values()) {
            if (key.equals(status.serverKey)) {
                return status;
            }
        }
        return Unregistered;
    }

    public String getServerKey() {
        return this.serverKey;
    }

    public boolean hasBeenSubmitted() {
        return this == SubmittedPendingAirbnbApproval || this == SubmittedPendingCityApproval || this == Approved || this == Denied || this == DeniedFinal;
    }

    public boolean isPending() {
        return this == SubmittedPendingAirbnbApproval || this == SubmittedPendingCityApproval;
    }

    public boolean isDeniedStatus() {
        return this == Denied || this == DeniedFinal;
    }

    public boolean hasBeenSubmittedOrExempted() {
        return hasBeenSubmitted() || this == Exempted;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ordinal());
    }
}
