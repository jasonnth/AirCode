package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenCohostingNotification;

public class CohostingNotification extends GenCohostingNotification {
    public static final Creator<CohostingNotification> CREATOR = new Creator<CohostingNotification>() {
        public CohostingNotification[] newArray(int size) {
            return new CohostingNotification[size];
        }

        public CohostingNotification createFromParcel(Parcel source) {
            CohostingNotification object = new CohostingNotification();
            object.readFromParcel(source);
            return object;
        }
    };

    public enum MuteType {
        UNMUTED,
        MUTED
    }
}
