package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenUserCommunityCommitment;

public class UserCommunityCommitment extends GenUserCommunityCommitment {
    public static final Creator<UserCommunityCommitment> CREATOR = new Creator<UserCommunityCommitment>() {
        public UserCommunityCommitment[] newArray(int size) {
            return new UserCommunityCommitment[size];
        }

        public UserCommunityCommitment createFromParcel(Parcel source) {
            UserCommunityCommitment object = new UserCommunityCommitment();
            object.readFromParcel(source);
            return object;
        }
    };
}
