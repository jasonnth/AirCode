package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenGovernmentIdResult implements Parcelable {
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("localized_denial_reason")
    protected String mLocalizedDenialReason;
    @JsonProperty("localized_denial_reason_title")
    protected String mLocalizedDenialReasonTitle;
    @JsonProperty("reject_reason")
    protected long mRejectReason;
    @JsonProperty("status")
    protected String mStatus;
    @JsonProperty("user_id")
    protected long mUserId;
    @JsonProperty("vendor_reference")
    protected String mVendorReference;
    @JsonProperty("vendor_status")
    protected long mVendorStatus;

    protected GenGovernmentIdResult(String localizedDenialReasonTitle, String localizedDenialReason, String vendorReference, String status, long id, long rejectReason, long userId, long vendorStatus) {
        this();
        this.mLocalizedDenialReasonTitle = localizedDenialReasonTitle;
        this.mLocalizedDenialReason = localizedDenialReason;
        this.mVendorReference = vendorReference;
        this.mStatus = status;
        this.mId = id;
        this.mRejectReason = rejectReason;
        this.mUserId = userId;
        this.mVendorStatus = vendorStatus;
    }

    protected GenGovernmentIdResult() {
    }

    public String getLocalizedDenialReasonTitle() {
        return this.mLocalizedDenialReasonTitle;
    }

    @JsonProperty("localized_denial_reason_title")
    public void setLocalizedDenialReasonTitle(String value) {
        this.mLocalizedDenialReasonTitle = value;
    }

    public String getLocalizedDenialReason() {
        return this.mLocalizedDenialReason;
    }

    @JsonProperty("localized_denial_reason")
    public void setLocalizedDenialReason(String value) {
        this.mLocalizedDenialReason = value;
    }

    public String getVendorReference() {
        return this.mVendorReference;
    }

    @JsonProperty("vendor_reference")
    public void setVendorReference(String value) {
        this.mVendorReference = value;
    }

    public String getStatus() {
        return this.mStatus;
    }

    @JsonProperty("status")
    public void setStatus(String value) {
        this.mStatus = value;
    }

    public long getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(long value) {
        this.mId = value;
    }

    public long getRejectReason() {
        return this.mRejectReason;
    }

    @JsonProperty("reject_reason")
    public void setRejectReason(long value) {
        this.mRejectReason = value;
    }

    public long getUserId() {
        return this.mUserId;
    }

    @JsonProperty("user_id")
    public void setUserId(long value) {
        this.mUserId = value;
    }

    public long getVendorStatus() {
        return this.mVendorStatus;
    }

    @JsonProperty("vendor_status")
    public void setVendorStatus(long value) {
        this.mVendorStatus = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mLocalizedDenialReasonTitle);
        parcel.writeString(this.mLocalizedDenialReason);
        parcel.writeString(this.mVendorReference);
        parcel.writeString(this.mStatus);
        parcel.writeLong(this.mId);
        parcel.writeLong(this.mRejectReason);
        parcel.writeLong(this.mUserId);
        parcel.writeLong(this.mVendorStatus);
    }

    public void readFromParcel(Parcel source) {
        this.mLocalizedDenialReasonTitle = source.readString();
        this.mLocalizedDenialReason = source.readString();
        this.mVendorReference = source.readString();
        this.mStatus = source.readString();
        this.mId = source.readLong();
        this.mRejectReason = source.readLong();
        this.mUserId = source.readLong();
        this.mVendorStatus = source.readLong();
    }
}
