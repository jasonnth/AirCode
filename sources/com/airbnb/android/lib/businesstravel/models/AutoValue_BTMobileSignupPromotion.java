package com.airbnb.android.lib.businesstravel.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_BTMobileSignupPromotion extends C$AutoValue_BTMobileSignupPromotion {
    public static final Creator<AutoValue_BTMobileSignupPromotion> CREATOR = new Creator<AutoValue_BTMobileSignupPromotion>() {
        public AutoValue_BTMobileSignupPromotion createFromParcel(Parcel in) {
            return new AutoValue_BTMobileSignupPromotion(in.readString(), in.readString(), in.readString());
        }

        public AutoValue_BTMobileSignupPromotion[] newArray(int size) {
            return new AutoValue_BTMobileSignupPromotion[size];
        }
    };

    AutoValue_BTMobileSignupPromotion(String title, String body, String boldText) {
        super(title, body, boldText);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title());
        dest.writeString(body());
        dest.writeString(boldText());
    }

    public int describeContents() {
        return 0;
    }
}
