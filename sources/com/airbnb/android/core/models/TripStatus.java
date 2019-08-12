package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.BugsnagWrapper;
import com.facebook.react.modules.appstate.AppStateModule;

public enum TripStatus implements Parcelable {
    Pending("pending"),
    Active(AppStateModule.APP_STATE_ACTIVE),
    Accepted("accepted"),
    Declined("declined"),
    Canceled("canceled"),
    Deleted("deleted"),
    Expired("expired"),
    Unknown("unknown");
    
    public static final Creator<TripStatus> CREATOR = null;
    private final String serverKey;

    static {
        CREATOR = new Creator<TripStatus>() {
            public TripStatus createFromParcel(Parcel in) {
                return TripStatus.values()[in.readInt()];
            }

            public TripStatus[] newArray(int size) {
                return new TripStatus[size];
            }
        };
    }

    private TripStatus(String serverKey2) {
        this.serverKey = serverKey2;
    }

    public static TripStatus fromKey(String key) {
        TripStatus[] values = values();
        int length = values.length;
        for (int i = 0; i < length; i++) {
            TripStatus status = values[i];
            if (key.equals(status.serverKey) || status.name().equals(key)) {
                return status;
            }
        }
        BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalArgumentException("Unable to decode: " + key));
        return Unknown;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ordinal());
    }

    public int describeContents() {
        return 0;
    }
}
