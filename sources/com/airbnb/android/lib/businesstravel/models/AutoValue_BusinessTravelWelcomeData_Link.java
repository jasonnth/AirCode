package com.airbnb.android.lib.businesstravel.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_BusinessTravelWelcomeData_Link extends C$AutoValue_BusinessTravelWelcomeData_Link {
    public static final Creator<AutoValue_BusinessTravelWelcomeData_Link> CREATOR = new Creator<AutoValue_BusinessTravelWelcomeData_Link>() {
        public AutoValue_BusinessTravelWelcomeData_Link createFromParcel(Parcel in) {
            return new AutoValue_BusinessTravelWelcomeData_Link(in.readString(), in.readString());
        }

        public AutoValue_BusinessTravelWelcomeData_Link[] newArray(int size) {
            return new AutoValue_BusinessTravelWelcomeData_Link[size];
        }
    };

    AutoValue_BusinessTravelWelcomeData_Link(String text, String url) {
        super(text, url);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(text());
        dest.writeString(url());
    }

    public int describeContents() {
        return 0;
    }
}
