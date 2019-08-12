package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenPopularDestinationGroup;

public class PopularDestinationGroup extends GenPopularDestinationGroup {
    public static final Creator<PopularDestinationGroup> CREATOR = new Creator<PopularDestinationGroup>() {
        public PopularDestinationGroup[] newArray(int size) {
            return new PopularDestinationGroup[size];
        }

        public PopularDestinationGroup createFromParcel(Parcel source) {
            PopularDestinationGroup object = new PopularDestinationGroup();
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
        PopularDestinationGroup that = (PopularDestinationGroup) o;
        if (this.mDestinations == null ? that.mDestinations != null : !this.mDestinations.equals(that.mDestinations)) {
            return false;
        }
        if (this.mName != null) {
            return this.mName.equals(that.mName);
        }
        if (that.mName != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result;
        int i = 0;
        if (this.mDestinations != null) {
            result = this.mDestinations.hashCode();
        } else {
            result = 0;
        }
        int i2 = result * 31;
        if (this.mName != null) {
            i = this.mName.hashCode();
        }
        return i2 + i;
    }
}
