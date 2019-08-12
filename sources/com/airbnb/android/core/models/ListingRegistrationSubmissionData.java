package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenListingRegistrationSubmissionData;

public class ListingRegistrationSubmissionData extends GenListingRegistrationSubmissionData {
    public static final Creator<ListingRegistrationSubmissionData> CREATOR = new Creator<ListingRegistrationSubmissionData>() {
        public ListingRegistrationSubmissionData[] newArray(int size) {
            return new ListingRegistrationSubmissionData[size];
        }

        public ListingRegistrationSubmissionData createFromParcel(Parcel source) {
            ListingRegistrationSubmissionData object = new ListingRegistrationSubmissionData();
            object.readFromParcel(source);
            return object;
        }
    };
}
