package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenUserFlag;

public class UserFlag extends GenUserFlag {
    public static final Creator<UserFlag> CREATOR = new Creator<UserFlag>() {
        public UserFlag[] newArray(int size) {
            return new UserFlag[size];
        }

        public UserFlag createFromParcel(Parcel source) {
            UserFlag object = new UserFlag();
            object.readFromParcel(source);
            return object;
        }
    };
}
