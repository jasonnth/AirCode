package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenCountry;

public class Country extends GenCountry {
    public static final Creator<Country> CREATOR = new Creator<Country>() {
        public Country[] newArray(int size) {
            return new Country[size];
        }

        public Country createFromParcel(Parcel source) {
            Country object = new Country();
            object.readFromParcel(source);
            return object;
        }
    };

    public String getAlpha_2() {
        return super.getAlpha_2();
    }

    public String getAlpha_3() {
        return super.getAlpha_3();
    }

    public String getLocalizedName() {
        return super.getLocalizedName();
    }
}
