package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenBookingSettings;

public class BookingSettings extends GenBookingSettings {
    public static final Creator<BookingSettings> CREATOR = new Creator<BookingSettings>() {
        public BookingSettings[] newArray(int size) {
            return new BookingSettings[size];
        }

        public BookingSettings createFromParcel(Parcel source) {
            BookingSettings object = new BookingSettings();
            object.readFromParcel(source);
            return object;
        }
    };
}
