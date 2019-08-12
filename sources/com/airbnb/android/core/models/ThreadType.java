package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.BugsnagWrapper;

public enum ThreadType implements Parcelable {
    PlaceBooking("booking_direct_thread"),
    TripDirect("trip_direct_thread"),
    TripGroup("trip_channel_thread"),
    Cohost("cohosting_direct_thread"),
    HelpThread("help_thread"),
    SupportMessagingThread("support_messaging_thread"),
    RestaurantThread("restaurant_channel_thread"),
    Unknown("unknown");
    
    public static final Creator<ThreadType> CREATOR = null;
    private final String serverKey;

    static {
        CREATOR = new Creator<ThreadType>() {
            public ThreadType createFromParcel(Parcel in) {
                return ThreadType.values()[in.readInt()];
            }

            public ThreadType[] newArray(int size) {
                return new ThreadType[size];
            }
        };
    }

    private ThreadType(String serverKey2) {
        this.serverKey = serverKey2;
    }

    public boolean isTripThread() {
        return this == TripGroup || this == TripDirect;
    }

    public boolean isCohostingThread() {
        return this == Cohost;
    }

    public static ThreadType fromKey(String key) {
        ThreadType[] values = values();
        int length = values.length;
        for (int i = 0; i < length; i++) {
            ThreadType status = values[i];
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

    public boolean isRestaurantThread() {
        return this == RestaurantThread;
    }
}
