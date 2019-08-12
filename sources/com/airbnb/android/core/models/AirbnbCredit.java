package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenAirbnbCredit;

public class AirbnbCredit extends GenAirbnbCredit {
    public static final Creator<AirbnbCredit> CREATOR = new Creator<AirbnbCredit>() {
        public AirbnbCredit[] newArray(int size) {
            return new AirbnbCredit[size];
        }

        public AirbnbCredit createFromParcel(Parcel source) {
            AirbnbCredit object = new AirbnbCredit();
            object.readFromParcel(source);
            return object;
        }
    };
}
