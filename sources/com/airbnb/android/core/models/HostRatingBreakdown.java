package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenHostRatingBreakdown;

public class HostRatingBreakdown extends GenHostRatingBreakdown {
    public static final Creator<HostRatingBreakdown> CREATOR = new Creator<HostRatingBreakdown>() {
        public HostRatingBreakdown[] newArray(int size) {
            return new HostRatingBreakdown[size];
        }

        public HostRatingBreakdown createFromParcel(Parcel source) {
            HostRatingBreakdown object = new HostRatingBreakdown();
            object.readFromParcel(source);
            return object;
        }
    };

    public boolean hasSimilarHostFiveStarRating() {
        return getMarketAverageFiveStarRatingPercentage() != null && getMarketAverageFiveStarRatingPercentage().intValue() > 0;
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HostRatingBreakdown that = (HostRatingBreakdown) o;
        if (Float.compare(that.mOverall, this.mOverall) != 0 || Float.compare(that.mAccuracy, this.mAccuracy) != 0 || Float.compare(that.mCommunication, this.mCommunication) != 0 || Float.compare(that.mCleanliness, this.mCleanliness) != 0 || Float.compare(that.mLocation, this.mLocation) != 0 || Float.compare(that.mCheckIn, this.mCheckIn) != 0 || Float.compare(that.mValue, this.mValue) != 0) {
            return false;
        }
        if (this.mMarketAverageFiveStarRatingPercentage != null) {
            z = this.mMarketAverageFiveStarRatingPercentage.equals(that.mMarketAverageFiveStarRatingPercentage);
        } else if (that.mMarketAverageFiveStarRatingPercentage != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int result;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7 = 0;
        if (this.mMarketAverageFiveStarRatingPercentage != null) {
            result = this.mMarketAverageFiveStarRatingPercentage.hashCode();
        } else {
            result = 0;
        }
        int i8 = result * 31;
        if (this.mOverall != 0.0f) {
            i = Float.floatToIntBits(this.mOverall);
        } else {
            i = 0;
        }
        int i9 = (i8 + i) * 31;
        if (this.mAccuracy != 0.0f) {
            i2 = Float.floatToIntBits(this.mAccuracy);
        } else {
            i2 = 0;
        }
        int i10 = (i9 + i2) * 31;
        if (this.mCommunication != 0.0f) {
            i3 = Float.floatToIntBits(this.mCommunication);
        } else {
            i3 = 0;
        }
        int i11 = (i10 + i3) * 31;
        if (this.mCleanliness != 0.0f) {
            i4 = Float.floatToIntBits(this.mCleanliness);
        } else {
            i4 = 0;
        }
        int i12 = (i11 + i4) * 31;
        if (this.mLocation != 0.0f) {
            i5 = Float.floatToIntBits(this.mLocation);
        } else {
            i5 = 0;
        }
        int i13 = (i12 + i5) * 31;
        if (this.mCheckIn != 0.0f) {
            i6 = Float.floatToIntBits(this.mCheckIn);
        } else {
            i6 = 0;
        }
        int i14 = (i13 + i6) * 31;
        if (this.mValue != 0.0f) {
            i7 = Float.floatToIntBits(this.mValue);
        }
        return i14 + i7;
    }
}
