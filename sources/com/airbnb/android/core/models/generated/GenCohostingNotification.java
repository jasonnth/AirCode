package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenCohostingNotification implements Parcelable {
    @JsonProperty("id")
    protected long mListingId;
    @JsonProperty("mute_type")
    protected int mMuteType;

    protected GenCohostingNotification(int muteType, long listingId) {
        this();
        this.mMuteType = muteType;
        this.mListingId = listingId;
    }

    protected GenCohostingNotification() {
    }

    public int getMuteType() {
        return this.mMuteType;
    }

    @JsonProperty("mute_type")
    public void setMuteType(int value) {
        this.mMuteType = value;
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
        parcel.writeInt(this.mMuteType);
        parcel.writeLong(this.mListingId);
    }

    public void readFromParcel(Parcel source) {
        this.mMuteType = source.readInt();
        this.mListingId = source.readLong();
    }
}
