package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenHostRatingDistributionStatistic;

public class HostRatingDistributionStatistic extends GenHostRatingDistributionStatistic {
    public static final Creator<HostRatingDistributionStatistic> CREATOR = new Creator<HostRatingDistributionStatistic>() {
        public HostRatingDistributionStatistic[] newArray(int size) {
            return new HostRatingDistributionStatistic[size];
        }

        public HostRatingDistributionStatistic createFromParcel(Parcel source) {
            HostRatingDistributionStatistic object = new HostRatingDistributionStatistic();
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
        HostRatingDistributionStatistic that = (HostRatingDistributionStatistic) o;
        if (this.mRating != that.mRating) {
            return false;
        }
        if (this.mPercentage != that.mPercentage) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.mRating * 31) + this.mPercentage;
    }
}
