package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenHelpThreadAmenity;

public class HelpThreadAmenity extends GenHelpThreadAmenity {
    public static final Creator<HelpThreadAmenity> CREATOR = new Creator<HelpThreadAmenity>() {
        public HelpThreadAmenity[] newArray(int size) {
            return new HelpThreadAmenity[size];
        }

        public HelpThreadAmenity createFromParcel(Parcel source) {
            HelpThreadAmenity object = new HelpThreadAmenity();
            object.readFromParcel(source);
            return object;
        }
    };

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (this.mId != ((HelpThreadAmenity) o).mId) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (int) (this.mId ^ (this.mId >>> 32));
    }
}
