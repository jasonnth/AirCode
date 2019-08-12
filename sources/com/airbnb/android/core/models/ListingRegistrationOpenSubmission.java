package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenListingRegistrationOpenSubmission;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ListingRegistrationOpenSubmission extends GenListingRegistrationOpenSubmission {
    public static final Creator<ListingRegistrationOpenSubmission> CREATOR = new Creator<ListingRegistrationOpenSubmission>() {
        public ListingRegistrationOpenSubmission[] newArray(int size) {
            return new ListingRegistrationOpenSubmission[size];
        }

        public ListingRegistrationOpenSubmission createFromParcel(Parcel source) {
            ListingRegistrationOpenSubmission object = new ListingRegistrationOpenSubmission();
            object.readFromParcel(source);
            return object;
        }
    };
}
