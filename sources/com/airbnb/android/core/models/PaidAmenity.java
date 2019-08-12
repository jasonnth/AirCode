package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenPaidAmenity;

public class PaidAmenity extends GenPaidAmenity {
    public static final Creator<PaidAmenity> CREATOR = new Creator<PaidAmenity>() {
        public PaidAmenity[] newArray(int size) {
            return new PaidAmenity[size];
        }

        public PaidAmenity createFromParcel(Parcel source) {
            PaidAmenity object = new PaidAmenity();
            object.readFromParcel(source);
            return object;
        }
    };

    public int hashCode() {
        return (int) (this.mId ^ (this.mId >>> 32));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (this.mId != ((PaidAmenity) obj).getId()) {
            return false;
        }
        return true;
    }
}
