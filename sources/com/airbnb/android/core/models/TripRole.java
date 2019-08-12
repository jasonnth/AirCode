package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenTripRole;

public class TripRole extends GenTripRole {
    public static final Creator<TripRole> CREATOR = new Creator<TripRole>() {
        public TripRole[] newArray(int size) {
            return new TripRole[size];
        }

        public TripRole createFromParcel(Parcel source) {
            TripRole object = new TripRole();
            object.readFromParcel(source);
            return object;
        }
    };
    public static final String ROLE_KEY_GUEST = "guest";
    public static final String ROLE_KEY_HOST = "host";
}
