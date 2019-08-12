package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenPartialListing implements Parcelable {
    @JsonProperty("id")
    protected long mListingId;
    @JsonProperty("user_id")
    protected long mListingOwnerUserId;
    @JsonProperty("name")
    protected String mName;

    protected GenPartialListing(String name, long listingOwnerUserId, long listingId) {
        this();
        this.mName = name;
        this.mListingOwnerUserId = listingOwnerUserId;
        this.mListingId = listingId;
    }

    protected GenPartialListing() {
    }

    public String getName() {
        return this.mName;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.mName = value;
    }

    public long getListingOwnerUserId() {
        return this.mListingOwnerUserId;
    }

    @JsonProperty("user_id")
    public void setListingOwnerUserId(long value) {
        this.mListingOwnerUserId = value;
    }

    public long getListingId() {
        return this.mListingId;
    }

    @JsonProperty("id")
    public void setListingId(long value) {
        this.mListingId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mName);
        parcel.writeLong(this.mListingOwnerUserId);
        parcel.writeLong(this.mListingId);
    }

    public void readFromParcel(Parcel source) {
        this.mName = source.readString();
        this.mListingOwnerUserId = source.readLong();
        this.mListingId = source.readLong();
    }
}
