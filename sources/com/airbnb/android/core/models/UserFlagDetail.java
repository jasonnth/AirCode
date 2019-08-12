package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenUserFlagDetail;

public class UserFlagDetail extends GenUserFlagDetail {
    public static final Creator<UserFlagDetail> CREATOR = new Creator<UserFlagDetail>() {
        public UserFlagDetail[] newArray(int size) {
            return new UserFlagDetail[size];
        }

        public UserFlagDetail createFromParcel(Parcel source) {
            UserFlagDetail object = new UserFlagDetail();
            object.readFromParcel(source);
            return object;
        }
    };
}
