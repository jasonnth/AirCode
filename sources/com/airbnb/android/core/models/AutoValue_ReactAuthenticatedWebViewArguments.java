package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_ReactAuthenticatedWebViewArguments extends C$AutoValue_ReactAuthenticatedWebViewArguments {
    public static final Creator<AutoValue_ReactAuthenticatedWebViewArguments> CREATOR = new Creator<AutoValue_ReactAuthenticatedWebViewArguments>() {
        public AutoValue_ReactAuthenticatedWebViewArguments createFromParcel(Parcel in) {
            return new AutoValue_ReactAuthenticatedWebViewArguments(in.readString());
        }

        public AutoValue_ReactAuthenticatedWebViewArguments[] newArray(int size) {
            return new AutoValue_ReactAuthenticatedWebViewArguments[size];
        }
    };

    AutoValue_ReactAuthenticatedWebViewArguments(String url) {
        super(url);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getUrl());
    }

    public int describeContents() {
        return 0;
    }
}
