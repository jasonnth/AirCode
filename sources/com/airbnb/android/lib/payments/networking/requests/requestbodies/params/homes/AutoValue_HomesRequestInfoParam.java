package com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_HomesRequestInfoParam extends C$AutoValue_HomesRequestInfoParam {
    public static final Creator<AutoValue_HomesRequestInfoParam> CREATOR = new Creator<AutoValue_HomesRequestInfoParam>() {
        public AutoValue_HomesRequestInfoParam createFromParcel(Parcel in) {
            return new AutoValue_HomesRequestInfoParam(in.readString(), in.readString());
        }

        public AutoValue_HomesRequestInfoParam[] newArray(int size) {
            return new AutoValue_HomesRequestInfoParam[size];
        }
    };

    AutoValue_HomesRequestInfoParam(String context, String mobileClient) {
        super(context, mobileClient);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(context());
        dest.writeString(mobileClient());
    }

    public int describeContents() {
        return 0;
    }
}
