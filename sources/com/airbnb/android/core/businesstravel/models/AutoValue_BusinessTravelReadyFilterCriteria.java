package com.airbnb.android.core.businesstravel.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.List;

final class AutoValue_BusinessTravelReadyFilterCriteria extends C$AutoValue_BusinessTravelReadyFilterCriteria {
    public static final Creator<AutoValue_BusinessTravelReadyFilterCriteria> CREATOR = new Creator<AutoValue_BusinessTravelReadyFilterCriteria>() {
        public AutoValue_BusinessTravelReadyFilterCriteria createFromParcel(Parcel in) {
            return new AutoValue_BusinessTravelReadyFilterCriteria(in.readArrayList(Integer.class.getClassLoader()), in.readArrayList(Integer.class.getClassLoader()), in.readArrayList(Integer.class.getClassLoader()), in.readArrayList(String.class.getClassLoader()));
        }

        public AutoValue_BusinessTravelReadyFilterCriteria[] newArray(int size) {
            return new AutoValue_BusinessTravelReadyFilterCriteria[size];
        }
    };

    AutoValue_BusinessTravelReadyFilterCriteria(List<Integer> amenitiesToFilterOut, List<Integer> hostingAmenities, List<Integer> listingTypes, List<String> roomTypes) {
        super(amenitiesToFilterOut, hostingAmenities, listingTypes, roomTypes);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(amenitiesToFilterOut());
        dest.writeList(hostingAmenities());
        dest.writeList(listingTypes());
        dest.writeList(roomTypes());
    }

    public int describeContents() {
        return 0;
    }
}
