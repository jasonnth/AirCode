package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenHostEarnings;

public class HostEarnings extends GenHostEarnings {
    public static final Creator<HostEarnings> CREATOR = new Creator<HostEarnings>() {
        public HostEarnings[] newArray(int size) {
            return new HostEarnings[size];
        }

        public HostEarnings createFromParcel(Parcel source) {
            HostEarnings object = new HostEarnings();
            object.readFromParcel(source);
            return object;
        }
    };
}
