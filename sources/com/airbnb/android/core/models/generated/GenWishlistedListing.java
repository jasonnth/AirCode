package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.PricingQuote;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenWishlistedListing implements Parcelable {
    @JsonProperty("down_votes")
    protected List<Long> mDownVotes;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("listing")
    protected Listing mListing;
    @JsonProperty("pricing_quote")
    protected PricingQuote mPricingQuote;
    @JsonProperty("up_votes")
    protected List<Long> mUpVotes;

    protected GenWishlistedListing(List<Long> downVotes, List<Long> upVotes, Listing listing, PricingQuote pricingQuote, long id) {
        this();
        this.mDownVotes = downVotes;
        this.mUpVotes = upVotes;
        this.mListing = listing;
        this.mPricingQuote = pricingQuote;
        this.mId = id;
    }

    protected GenWishlistedListing() {
    }

    public List<Long> getDownVotes() {
        return this.mDownVotes;
    }

    @JsonProperty("down_votes")
    public void setDownVotes(List<Long> value) {
        this.mDownVotes = value;
    }

    public List<Long> getUpVotes() {
        return this.mUpVotes;
    }

    @JsonProperty("up_votes")
    public void setUpVotes(List<Long> value) {
        this.mUpVotes = value;
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

    public long getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(long value) {
        this.mId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeValue(this.mDownVotes);
        parcel.writeValue(this.mUpVotes);
        parcel.writeParcelable(this.mListing, 0);
        parcel.writeParcelable(this.mPricingQuote, 0);
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mDownVotes = (List) source.readValue(null);
        this.mUpVotes = (List) source.readValue(null);
        this.mListing = (Listing) source.readParcelable(Listing.class.getClassLoader());
        this.mPricingQuote = (PricingQuote) source.readParcelable(PricingQuote.class.getClassLoader());
        this.mId = source.readLong();
    }
}
