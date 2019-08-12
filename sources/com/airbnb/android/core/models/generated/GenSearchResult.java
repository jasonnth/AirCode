package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.PricingQuote;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenSearchResult implements Parcelable {
    @JsonProperty("listing")
    protected Listing mListing;
    @JsonProperty("pricing_quote")
    protected PricingQuote mPricingQuote;
    @JsonProperty("recommendation_reason")
    protected String mRecommendationReason;
    @JsonProperty("viewed_at")
    protected long mViewedAt;

    protected GenSearchResult(Listing listing, PricingQuote pricingQuote, String recommendationReason, long viewedAt) {
        this();
        this.mListing = listing;
        this.mPricingQuote = pricingQuote;
        this.mRecommendationReason = recommendationReason;
        this.mViewedAt = viewedAt;
    }

    protected GenSearchResult() {
    }

    public Listing getListing() {
        return this.mListing;
    }

    @JsonProperty("listing")
    public void setListing(Listing value) {
        this.mListing = value;
    }

    public PricingQuote getPricingQuote() {
        return this.mPricingQuote;
    }

    @JsonProperty("pricing_quote")
    public void setPricingQuote(PricingQuote value) {
        this.mPricingQuote = value;
    }

    public String getRecommendationReason() {
        return this.mRecommendationReason;
    }

    @JsonProperty("recommendation_reason")
    public void setRecommendationReason(String value) {
        this.mRecommendationReason = value;
    }

    public long getViewedAt() {
        return this.mViewedAt;
    }

    @JsonProperty("viewed_at")
    public void setViewedAt(long value) {
        this.mViewedAt = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mListing, 0);
        parcel.writeParcelable(this.mPricingQuote, 0);
        parcel.writeString(this.mRecommendationReason);
        parcel.writeLong(this.mViewedAt);
    }

    public void readFromParcel(Parcel source) {
        this.mListing = (Listing) source.readParcelable(Listing.class.getClassLoader());
        this.mPricingQuote = (PricingQuote) source.readParcelable(PricingQuote.class.getClassLoader());
        this.mRecommendationReason = source.readString();
        this.mViewedAt = source.readLong();
    }
}
