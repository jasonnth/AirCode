package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.models.generated.GenSpecialOffer;

public class SpecialOffer extends GenSpecialOffer {
    public static final Creator<SpecialOffer> CREATOR = new Creator<SpecialOffer>() {
        public SpecialOffer[] newArray(int size) {
            return new SpecialOffer[size];
        }

        public SpecialOffer createFromParcel(Parcel source) {
            SpecialOffer object = new SpecialOffer();
            object.readFromParcel(source);
            return object;
        }
    };

    public AirDate getEndDate() {
        return getStartDate().plusDays(getNights());
    }

    public int hashCode() {
        return ((int) (this.mId ^ (this.mId >>> 32))) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (this.mId != ((GenSpecialOffer) obj).getId()) {
            return false;
        }
        return true;
    }
}
