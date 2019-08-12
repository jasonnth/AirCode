package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenThreadInboxInformation implements Parcelable {
    @JsonProperty("inbox_name")
    protected String mRole;
    @JsonProperty("id")
    protected long mThreadId;

    protected GenThreadInboxInformation(String role, long threadId) {
        this();
        this.mRole = role;
        this.mThreadId = threadId;
    }

    protected GenThreadInboxInformation() {
    }

    public String getRole() {
        return this.mRole;
    }

    @JsonProperty("inbox_name")
    public void setRole(String value) {
        this.mRole = value;
    }

    public long getThreadId() {
        return this.mThreadId;
    }

    @JsonProperty("id")
    public void setThreadId(long value) {
        this.mThreadId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mRole);
        parcel.writeLong(this.mThreadId);
    }

    public void readFromParcel(Parcel source) {
        this.mRole = source.readString();
        this.mThreadId = source.readLong();
    }
}
