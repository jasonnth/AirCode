package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenSimilarListing;

public class SimilarListing extends GenSimilarListing {
    public static final Creator<SimilarListing> CREATOR = new Creator<SimilarListing>() {
        public SimilarListing[] newArray(int size) {
            return new SimilarListing[size];
        }

        public SimilarListing createFromParcel(Parcel source) {
            SimilarListing object = new SimilarListing();
            object.readFromParcel(source);
            return object;
        }
    };

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SimilarListing)) {
            return false;
        }
        SimilarListing that = (SimilarListing) o;
        if (this.mListing.equals(that.mListing)) {
            return this.mPricingQuote.equals(that.mPricingQuote);
        }
        return false;
    }

    public int hashCode() {
        return (this.mListing.hashCode() * 31) + this.mPricingQuote.hashCode();
    }
}
