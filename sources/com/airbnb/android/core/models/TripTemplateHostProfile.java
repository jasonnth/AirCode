package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenTripTemplateHostProfile;

public class TripTemplateHostProfile extends GenTripTemplateHostProfile {
    public static final Creator<TripTemplateHostProfile> CREATOR = new Creator<TripTemplateHostProfile>() {
        public TripTemplateHostProfile[] newArray(int size) {
            return new TripTemplateHostProfile[size];
        }

        public TripTemplateHostProfile createFromParcel(Parcel source) {
            TripTemplateHostProfile object = new TripTemplateHostProfile();
            object.readFromParcel(source);
            return object;
        }
    };
}
