package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenPopularDestination;

public class PopularDestination extends GenPopularDestination {
    public static final Creator<PopularDestination> CREATOR = new Creator<PopularDestination>() {
        public PopularDestination[] newArray(int size) {
            return new PopularDestination[size];
        }

        public PopularDestination createFromParcel(Parcel source) {
            PopularDestination object = new PopularDestination();
            object.readFromParcel(source);
            return object;
        }
    };
}
