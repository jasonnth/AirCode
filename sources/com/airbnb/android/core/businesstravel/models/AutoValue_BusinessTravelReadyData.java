package com.airbnb.android.core.businesstravel.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_BusinessTravelReadyData extends C$AutoValue_BusinessTravelReadyData {
    public static final Creator<AutoValue_BusinessTravelReadyData> CREATOR = new Creator<AutoValue_BusinessTravelReadyData>() {
        public AutoValue_BusinessTravelReadyData createFromParcel(Parcel in) {
            return new AutoValue_BusinessTravelReadyData((BusinessTravelReadyFilterCriteria) in.readParcelable(BusinessTravelReadyFilterCriteria.class.getClassLoader()));
        }

        public AutoValue_BusinessTravelReadyData[] newArray(int size) {
            return new AutoValue_BusinessTravelReadyData[size];
        }
    };

    AutoValue_BusinessTravelReadyData(BusinessTravelReadyFilterCriteria businessTravelReadyFilterCriteria) {
        super(businessTravelReadyFilterCriteria);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(businessTravelReadyFilterCriteria(), flags);
    }

    public int describeContents() {
        return 0;
    }
}
