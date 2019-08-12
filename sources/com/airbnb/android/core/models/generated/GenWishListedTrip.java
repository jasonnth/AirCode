package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.PricingQuote;
import com.airbnb.android.core.models.TripTemplate;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenWishListedTrip implements Parcelable {
    @JsonProperty("down_votes")
    protected List<Long> mDownVotes;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("pricing_quote")
    protected PricingQuote mPricingQuote;
    @JsonProperty("mt_template")
    protected TripTemplate mTrip;
    @JsonProperty("up_votes")
    protected List<Long> mUpVotes;

    protected GenWishListedTrip(List<Long> downVotes, List<Long> upVotes, PricingQuote pricingQuote, TripTemplate trip, long id) {
        this();
        this.mDownVotes = downVotes;
        this.mUpVotes = upVotes;
        this.mPricingQuote = pricingQuote;
        this.mTrip = trip;
        this.mId = id;
    }

    protected GenWishListedTrip() {
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

    public PricingQuote getPricingQuote() {
        return this.mPricingQuote;
    }

    @JsonProperty("pricing_quote")
    public void setPricingQuote(PricingQuote value) {
        this.mPricingQuote = value;
    }

    public TripTemplate getTrip() {
        return this.mTrip;
    }

    @JsonProperty("mt_template")
    public void setTrip(TripTemplate value) {
        this.mTrip = value;
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
        parcel.writeParcelable(this.mPricingQuote, 0);
        parcel.writeParcelable(this.mTrip, 0);
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mDownVotes = (List) source.readValue(null);
        this.mUpVotes = (List) source.readValue(null);
        this.mPricingQuote = (PricingQuote) source.readParcelable(PricingQuote.class.getClassLoader());
        this.mTrip = (TripTemplate) source.readParcelable(TripTemplate.class.getClassLoader());
        this.mId = source.readLong();
    }
}
