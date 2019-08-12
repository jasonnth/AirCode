package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenUserBlock implements Parcelable {
    @JsonProperty("active")
    protected boolean mActive;
    @JsonProperty("blocked_user_id")
    protected long mBlockedUserId;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("user_id")
    protected long mUserId;

    protected GenUserBlock(boolean active, long blockedUserId, long id, long userId) {
        this();
        this.mActive = active;
        this.mBlockedUserId = blockedUserId;
        this.mId = id;
        this.mUserId = userId;
    }

    protected GenUserBlock() {
    }

    public boolean isActive() {
        return this.mActive;
    }

    @JsonProperty("active")
    public void setActive(boolean value) {
        this.mActive = value;
    }

    public long getBlockedUserId() {
        return this.mBlockedUserId;
    }

    @JsonProperty("blocked_user_id")
    public void setBlockedUserId(long value) {
        this.mBlockedUserId = value;
    }

    public long getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(long value) {
        this.mId = value;
    }

    public long getUserId() {
        return this.mUserId;
    }

    @JsonProperty("user_id")
    public void setUserId(long value) {
        this.mUserId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeBooleanArray(new boolean[]{this.mActive});
        parcel.writeLong(this.mBlockedUserId);
        parcel.writeLong(this.mId);
        parcel.writeLong(this.mUserId);
    }

    public void readFromParcel(Parcel source) {
        this.mActive = source.createBooleanArray()[0];
        this.mBlockedUserId = source.readLong();
        this.mId = source.readLong();
        this.mUserId = source.readLong();
    }
}
