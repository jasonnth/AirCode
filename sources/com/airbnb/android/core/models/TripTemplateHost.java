package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenTripTemplateHost;

public class TripTemplateHost extends GenTripTemplateHost {
    public static final Creator<TripTemplateHost> CREATOR = new Creator<TripTemplateHost>() {
        public TripTemplateHost[] newArray(int size) {
            return new TripTemplateHost[size];
        }

        public TripTemplateHost createFromParcel(Parcel source) {
            TripTemplateHost object = new TripTemplateHost();
            object.readFromParcel(source);
            return object;
        }
    };
}
