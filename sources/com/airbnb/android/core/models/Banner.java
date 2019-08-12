package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public class Banner extends RecommendationItem {
    public static final Creator<Banner> CREATOR = new Creator<Banner>() {
        public Banner[] newArray(int size) {
            return new Banner[size];
        }

        public Banner createFromParcel(Parcel source) {
            Banner object = new Banner();
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
        if (this.mId != ((Banner) o).mId) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (int) (this.mId ^ (this.mId >>> 32));
    }
}
