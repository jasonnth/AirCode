package com.airbnb.android.core.payments.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_RedirectSettings extends C$AutoValue_RedirectSettings {
    public static final Creator<AutoValue_RedirectSettings> CREATOR = new Creator<AutoValue_RedirectSettings>() {
        public AutoValue_RedirectSettings createFromParcel(Parcel in) {
            return new AutoValue_RedirectSettings(in.readInt() == 0 ? in.readString() : null);
        }

        public AutoValue_RedirectSettings[] newArray(int size) {
            return new AutoValue_RedirectSettings[size];
        }
    };

    AutoValue_RedirectSettings(String url) {
        super(url);
    }

    public void writeToParcel(Parcel dest, int flags) {
        if (url() == null) {
            dest.writeInt(1);
            return;
        }
        dest.writeInt(0);
        dest.writeString(url());
    }

    public int describeContents() {
        return 0;
    }
}
