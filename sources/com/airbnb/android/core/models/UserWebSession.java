package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenUserWebSession;

public class UserWebSession extends GenUserWebSession {
    public static final Creator<UserWebSession> CREATOR = new Creator<UserWebSession>() {
        public UserWebSession[] newArray(int size) {
            return new UserWebSession[size];
        }

        public UserWebSession createFromParcel(Parcel source) {
            UserWebSession object = new UserWebSession();
            object.readFromParcel(source);
            return object;
        }
    };
}
