package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenNotificationPreference;

public class NotificationPreference extends GenNotificationPreference {
    public static final Creator<NotificationPreference> CREATOR = new Creator<NotificationPreference>() {
        public NotificationPreference[] newArray(int size) {
            return new NotificationPreference[size];
        }

        public NotificationPreference createFromParcel(Parcel source) {
            NotificationPreference object = new NotificationPreference();
            object.readFromParcel(source);
            return object;
        }
    };
}
