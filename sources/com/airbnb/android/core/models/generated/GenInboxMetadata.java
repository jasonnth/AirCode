package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenInboxMetadata implements Parcelable {
    @JsonProperty("unread_guest_count")
    protected long mUnreadGuestCount;
    @JsonProperty("unread_host_count")
    protected long mUnreadHostCount;

    protected GenInboxMetadata(long unreadHostCount, long unreadGuestCount) {
        this();
        this.mUnreadHostCount = unreadHostCount;
        this.mUnreadGuestCount = unreadGuestCount;
    }

    protected GenInboxMetadata() {
    }

    public long getUnreadHostCount() {
        return this.mUnreadHostCount;
    }

    @JsonProperty("unread_host_count")
    public void setUnreadHostCount(long value) {
        this.mUnreadHostCount = value;
    }

    public long getUnreadGuestCount() {
        return this.mUnreadGuestCount;
    }

    @JsonProperty("unread_guest_count")
    public void setUnreadGuestCount(long value) {
        this.mUnreadGuestCount = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeLong(this.mUnreadHostCount);
        parcel.writeLong(this.mUnreadGuestCount);
    }

    public void readFromParcel(Parcel source) {
        this.mUnreadHostCount = source.readLong();
        this.mUnreadGuestCount = source.readLong();
    }
}
