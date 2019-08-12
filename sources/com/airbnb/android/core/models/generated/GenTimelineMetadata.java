package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenTimelineMetadata implements Parcelable {
    @JsonProperty("confirmation_codes")
    protected List<String> mConfirmationCodes;
    @JsonProperty("has_pending_trips")
    protected Boolean mHasPendingTrips;
    @JsonProperty("need_mt_tos")
    protected Boolean mNeedMtTos;
    @JsonProperty("need_verification")
    protected Boolean mNeedVerification;
    @JsonProperty("soonest_expires_at")
    protected AirDateTime mSoonestExpiresAt;
    @JsonProperty("soonest_expires_at_time_zone")
    protected String mSoonestExpiresAtTimeZone;
    @JsonProperty("soonest_expires_title")
    protected String mSoonestExpiresTitle;
    @JsonProperty("verification_pending")
    protected Boolean mVerificationPending;

    protected GenTimelineMetadata(AirDateTime soonestExpiresAt, Boolean hasPendingTrips, Boolean needVerification, Boolean verificationPending, Boolean needMtTos, List<String> confirmationCodes, String soonestExpiresAtTimeZone, String soonestExpiresTitle) {
        this();
        this.mSoonestExpiresAt = soonestExpiresAt;
        this.mHasPendingTrips = hasPendingTrips;
        this.mNeedVerification = needVerification;
        this.mVerificationPending = verificationPending;
        this.mNeedMtTos = needMtTos;
        this.mConfirmationCodes = confirmationCodes;
        this.mSoonestExpiresAtTimeZone = soonestExpiresAtTimeZone;
        this.mSoonestExpiresTitle = soonestExpiresTitle;
    }

    protected GenTimelineMetadata() {
    }

    public AirDateTime getSoonestExpiresAt() {
        return this.mSoonestExpiresAt;
    }

    @JsonProperty("soonest_expires_at")
    public void setSoonestExpiresAt(AirDateTime value) {
        this.mSoonestExpiresAt = value;
    }

    public Boolean isHasPendingTrips() {
        return this.mHasPendingTrips;
    }

    @JsonProperty("has_pending_trips")
    public void setHasPendingTrips(Boolean value) {
        this.mHasPendingTrips = value;
    }

    public Boolean isNeedVerification() {
        return this.mNeedVerification;
    }

    @JsonProperty("need_verification")
    public void setNeedVerification(Boolean value) {
        this.mNeedVerification = value;
    }

    public Boolean isVerificationPending() {
        return this.mVerificationPending;
    }

    @JsonProperty("verification_pending")
    public void setVerificationPending(Boolean value) {
        this.mVerificationPending = value;
    }

    public Boolean isNeedMtTos() {
        return this.mNeedMtTos;
    }

    @JsonProperty("need_mt_tos")
    public void setNeedMtTos(Boolean value) {
        this.mNeedMtTos = value;
    }

    public List<String> getConfirmationCodes() {
        return this.mConfirmationCodes;
    }

    @JsonProperty("confirmation_codes")
    public void setConfirmationCodes(List<String> value) {
        this.mConfirmationCodes = value;
    }

    public String getSoonestExpiresAtTimeZone() {
        return this.mSoonestExpiresAtTimeZone;
    }

    @JsonProperty("soonest_expires_at_time_zone")
    public void setSoonestExpiresAtTimeZone(String value) {
        this.mSoonestExpiresAtTimeZone = value;
    }

    public String getSoonestExpiresTitle() {
        return this.mSoonestExpiresTitle;
    }

    @JsonProperty("soonest_expires_title")
    public void setSoonestExpiresTitle(String value) {
        this.mSoonestExpiresTitle = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mSoonestExpiresAt, 0);
        parcel.writeValue(this.mHasPendingTrips);
        parcel.writeValue(this.mNeedVerification);
        parcel.writeValue(this.mVerificationPending);
        parcel.writeValue(this.mNeedMtTos);
        parcel.writeStringList(this.mConfirmationCodes);
        parcel.writeString(this.mSoonestExpiresAtTimeZone);
        parcel.writeString(this.mSoonestExpiresTitle);
    }

    public void readFromParcel(Parcel source) {
        this.mSoonestExpiresAt = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mHasPendingTrips = (Boolean) source.readValue(Boolean.class.getClassLoader());
        this.mNeedVerification = (Boolean) source.readValue(Boolean.class.getClassLoader());
        this.mVerificationPending = (Boolean) source.readValue(Boolean.class.getClassLoader());
        this.mNeedMtTos = (Boolean) source.readValue(Boolean.class.getClassLoader());
        this.mConfirmationCodes = source.createStringArrayList();
        this.mSoonestExpiresAtTimeZone = source.readString();
        this.mSoonestExpiresTitle = source.readString();
    }
}
