package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenSnoozeMode;

public class SnoozeMode extends GenSnoozeMode {
    public static final Creator<SnoozeMode> CREATOR = new Creator<SnoozeMode>() {
        public SnoozeMode[] newArray(int size) {
            return new SnoozeMode[size];
        }

        public SnoozeMode createFromParcel(Parcel source) {
            SnoozeMode object = new SnoozeMode();
            object.readFromParcel(source);
            return object;
        }
    };
}
