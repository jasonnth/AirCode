package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenInquiriesRepliesCounts implements Parcelable {
    @JsonProperty("n_inquiries")
    protected int mInquiries;
    @JsonProperty("n_replies")
    protected int mReplies;

    protected GenInquiriesRepliesCounts(int inquiries, int replies) {
        this();
        this.mInquiries = inquiries;
        this.mReplies = replies;
    }

    protected GenInquiriesRepliesCounts() {
    }

    public int getInquiries() {
        return this.mInquiries;
    }

    @JsonProperty("n_inquiries")
    public void setInquiries(int value) {
        this.mInquiries = value;
    }

    public int getReplies() {
        return this.mReplies;
    }

    @JsonProperty("n_replies")
    public void setReplies(int value) {
        this.mReplies = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(this.mInquiries);
        parcel.writeInt(this.mReplies);
    }

    public void readFromParcel(Parcel source) {
        this.mInquiries = source.readInt();
        this.mReplies = source.readInt();
    }
}
