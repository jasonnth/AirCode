package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenGrayUser;

public class GrayUser extends GenGrayUser {
    public static final Creator<GrayUser> CREATOR = new Creator<GrayUser>() {
        public GrayUser[] newArray(int size) {
            return new GrayUser[size];
        }

        public GrayUser createFromParcel(Parcel source) {
            GrayUser object = new GrayUser();
            object.readFromParcel(source);
            return object;
        }
    };
}
