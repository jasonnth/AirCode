package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenThreadInboxInformation;

public class ThreadInboxInformation extends GenThreadInboxInformation {
    public static final Creator<ThreadInboxInformation> CREATOR = new Creator<ThreadInboxInformation>() {
        public ThreadInboxInformation[] newArray(int size) {
            return new ThreadInboxInformation[size];
        }

        public ThreadInboxInformation createFromParcel(Parcel source) {
            ThreadInboxInformation object = new ThreadInboxInformation();
            object.readFromParcel(source);
            return object;
        }
    };
}
