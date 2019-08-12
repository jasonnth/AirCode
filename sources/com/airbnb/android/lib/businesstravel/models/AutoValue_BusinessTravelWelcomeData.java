package com.airbnb.android.lib.businesstravel.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.lib.businesstravel.models.BusinessTravelWelcomeData.LegalDisclaimer;

final class AutoValue_BusinessTravelWelcomeData extends C$AutoValue_BusinessTravelWelcomeData {
    public static final Creator<AutoValue_BusinessTravelWelcomeData> CREATOR = new Creator<AutoValue_BusinessTravelWelcomeData>() {
        public AutoValue_BusinessTravelWelcomeData createFromParcel(Parcel in) {
            return new AutoValue_BusinessTravelWelcomeData(in.readString(), in.readString(), in.readString(), in.readString(), (LegalDisclaimer) in.readParcelable(LegalDisclaimer.class.getClassLoader()));
        }

        public AutoValue_BusinessTravelWelcomeData[] newArray(int size) {
            return new AutoValue_BusinessTravelWelcomeData[size];
        }
    };

    AutoValue_BusinessTravelWelcomeData(String marqueeTitle, String marqueeBody, String buttonText, String marqueeImageUrl, LegalDisclaimer legalDisclaimer) {
        super(marqueeTitle, marqueeBody, buttonText, marqueeImageUrl, legalDisclaimer);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(marqueeTitle());
        dest.writeString(marqueeBody());
        dest.writeString(buttonText());
        dest.writeString(marqueeImageUrl());
        dest.writeParcelable(legalDisclaimer(), flags);
    }

    public int describeContents() {
        return 0;
    }
}
