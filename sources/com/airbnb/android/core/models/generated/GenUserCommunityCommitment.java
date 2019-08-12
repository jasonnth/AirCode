package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenUserCommunityCommitment implements Parcelable {
    @JsonProperty("agreed_to_community_commitment")
    protected boolean mAgreedToCommunityCommitment;
    @JsonProperty("user_id")
    protected long mUserId;

    protected GenUserCommunityCommitment(boolean agreedToCommunityCommitment, long userId) {
        this();
        this.mAgreedToCommunityCommitment = agreedToCommunityCommitment;
        this.mUserId = userId;
    }

    protected GenUserCommunityCommitment() {
    }

    public boolean isAgreedToCommunityCommitment() {
        return this.mAgreedToCommunityCommitment;
    }

    @JsonProperty("agreed_to_community_commitment")
    public void setAgreedToCommunityCommitment(boolean value) {
        this.mAgreedToCommunityCommitment = value;
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
        parcel.writeBooleanArray(new boolean[]{this.mAgreedToCommunityCommitment});
        parcel.writeLong(this.mUserId);
    }

    public void readFromParcel(Parcel source) {
        this.mAgreedToCommunityCommitment = source.createBooleanArray()[0];
        this.mUserId = source.readLong();
    }
}
