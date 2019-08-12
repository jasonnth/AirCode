package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenUserTravelStatus;

public class UserTravelStatus extends GenUserTravelStatus {
    public static final Creator<UserTravelStatus> CREATOR = new Creator<UserTravelStatus>() {
        public UserTravelStatus[] newArray(int size) {
            return new UserTravelStatus[size];
        }

        public UserTravelStatus createFromParcel(Parcel source) {
            UserTravelStatus object = new UserTravelStatus();
            object.readFromParcel(source);
            return object;
        }
    };
}
