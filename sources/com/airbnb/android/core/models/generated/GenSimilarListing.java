package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.PricingQuote;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenSimilarListing implements Parcelable {
    @JsonProperty("distance")
    protected String mDistance;
    @JsonProperty("listing")
    protected Listing mListing;
    @JsonProperty("pricing_quote")
    protected PricingQuote mPricingQuote;

    protected GenSimilarListing(Listing listing, PricingQuote pricingQuote, String distance) {
        this();
        this.mListing = listing;
        this.mPricingQuote = pricingQuote;
        this.mDistance = distance;
    }

    protected GenSimilarListing() {
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

    public String getDistance() {
        return this.mDistance;
    }

    @JsonProperty("distance")
    public void setDistance(String value) {
        this.mDistance = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mListing, 0);
        parcel.writeParcelable(this.mPricingQuote, 0);
        parcel.writeString(this.mDistance);
    }

    public void readFromParcel(Parcel source) {
        this.mListing = (Listing) source.readParcelable(Listing.class.getClassLoader());
        this.mPricingQuote = (PricingQuote) source.readParcelable(PricingQuote.class.getClassLoader());
        this.mDistance = source.readString();
    }
}
