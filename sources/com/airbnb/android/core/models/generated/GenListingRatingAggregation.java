package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.HostRatingDistributionStatistic;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenListingRatingAggregation implements Parcelable {
    @JsonProperty("listing_id")
    protected long mListingId;
    @JsonProperty("rating_aggregations")
    protected List<HostRatingDistributionStatistic> mRatingAggregations;

    protected GenListingRatingAggregation(List<HostRatingDistributionStatistic> ratingAggregations, long listingId) {
        this();
        this.mRatingAggregations = ratingAggregations;
        this.mListingId = listingId;
    }

    protected GenListingRatingAggregation() {
    }

    public List<HostRatingDistributionStatistic> getRatingAggregations() {
        return this.mRatingAggregations;
    }

    @JsonProperty("rating_aggregations")
    public void setRatingAggregations(List<HostRatingDistributionStatistic> value) {
        this.mRatingAggregations = value;
    }

    public long getListingId() {
        return this.mListingId;
    }

    @JsonProperty("listing_id")
    public void setListingId(long value) {
        this.mListingId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeTypedList(this.mRatingAggregations);
        parcel.writeLong(this.mListingId);
    }

    public void readFromParcel(Parcel source) {
        this.mRatingAggregations = source.createTypedArrayList(HostRatingDistributionStatistic.CREATOR);
        this.mListingId = source.readLong();
    }
}
