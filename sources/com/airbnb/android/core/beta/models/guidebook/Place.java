package com.airbnb.android.core.beta.models.guidebook;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.airbnb.android.core.beta.models.guidebook.generated.GenPlace;
import com.airbnb.android.core.models.Mappable;
import com.airbnb.p027n2.primitives.AirmojiEnum;

public class Place extends GenPlace implements Mappable {
    public static final Creator<Place> CREATOR = new Creator<Place>() {
        public Place[] newArray(int size) {
            return new Place[size];
        }

        public Place createFromParcel(Parcel source) {
            Place object = new Place();
            object.readFromParcel(source);
            return object;
        }
    };

    public CharSequence getAddressPlusCity() {
        boolean hasAddress;
        boolean hasCity;
        String address = getAddress();
        String city = getCity();
        if (!TextUtils.isEmpty(address)) {
            hasAddress = true;
        } else {
            hasAddress = false;
        }
        if (!TextUtils.isEmpty(city)) {
            hasCity = true;
        } else {
            hasCity = false;
        }
        if (hasAddress && hasCity) {
            return new StringBuilder(address).append("\n").append(city);
        }
        if (hasAddress) {
            return address;
        }
        if (hasCity) {
            return city;
        }
        return "";
    }

    public String getAirmojiForDisplay() {
        return AirmojiEnum.fromKey(getAirmoji());
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Place)) {
            return false;
        }
        if (this.mId != ((Place) o).mId) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (int) (this.mId ^ (this.mId >>> 32));
    }

    public long getMappableId() {
        return getId();
    }

    public double getLatitude() {
        return getLat();
    }

    public double getLongitude() {
        return getLng();
    }
}
