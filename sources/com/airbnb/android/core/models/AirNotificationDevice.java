package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenAirNotificationDevice;

public class AirNotificationDevice extends GenAirNotificationDevice {
    public static final Creator<AirNotificationDevice> CREATOR = new Creator<AirNotificationDevice>() {
        public AirNotificationDevice[] newArray(int size) {
            return new AirNotificationDevice[size];
        }

        public AirNotificationDevice createFromParcel(Parcel source) {
            AirNotificationDevice object = new AirNotificationDevice();
            object.readFromParcel(source);
            return object;
        }
    };
    public static final String DEVICE_TYPE_ANDROID_GCM = "android_gcm";
    public static final String DEVICE_TYPE_ANDROID_JPUSH = "android_china_jpush";
}
