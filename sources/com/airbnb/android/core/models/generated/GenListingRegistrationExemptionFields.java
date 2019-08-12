package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenListingRegistrationExemptionFields implements Parcelable {
    @JsonProperty("expiration_date_label")
    protected String mExpirationDateLabel;
    @JsonProperty("pending_toggle_label")
    protected String mPendingToggleLabel;
    @JsonProperty("permit_number_accuracy_warning")
    protected String mPermitNumberAccuracyWarning;
    @JsonProperty("permit_number_label")
    protected String mPermitNumberLabel;
    @JsonProperty("permit_number_placeholder")
    protected String mPermitNumberPlaceholder;
    @JsonProperty("zipcode_label")
    protected String mZipcodeLabel;

    protected GenListingRegistrationExemptionFields(String expirationDateLabel, String pendingToggleLabel, String permitNumberAccuracyWarning, String permitNumberLabel, String permitNumberPlaceholder, String zipcodeLabel) {
        this();
        this.mExpirationDateLabel = expirationDateLabel;
        this.mPendingToggleLabel = pendingToggleLabel;
        this.mPermitNumberAccuracyWarning = permitNumberAccuracyWarning;
        this.mPermitNumberLabel = permitNumberLabel;
        this.mPermitNumberPlaceholder = permitNumberPlaceholder;
        this.mZipcodeLabel = zipcodeLabel;
    }

    protected GenListingRegistrationExemptionFields() {
    }

    public String getExpirationDateLabel() {
        return this.mExpirationDateLabel;
    }

    @JsonProperty("expiration_date_label")
    public void setExpirationDateLabel(String value) {
        this.mExpirationDateLabel = value;
    }

    public String getPendingToggleLabel() {
        return this.mPendingToggleLabel;
    }

    @JsonProperty("pending_toggle_label")
    public void setPendingToggleLabel(String value) {
        this.mPendingToggleLabel = value;
    }

    public String getPermitNumberAccuracyWarning() {
        return this.mPermitNumberAccuracyWarning;
    }

    @JsonProperty("permit_number_accuracy_warning")
    public void setPermitNumberAccuracyWarning(String value) {
        this.mPermitNumberAccuracyWarning = value;
    }

    public String getPermitNumberLabel() {
        return this.mPermitNumberLabel;
    }

    @JsonProperty("permit_number_label")
    public void setPermitNumberLabel(String value) {
        this.mPermitNumberLabel = value;
    }

    public String getPermitNumberPlaceholder() {
        return this.mPermitNumberPlaceholder;
    }

    @JsonProperty("permit_number_placeholder")
    public void setPermitNumberPlaceholder(String value) {
        this.mPermitNumberPlaceholder = value;
    }

    public String getZipcodeLabel() {
        return this.mZipcodeLabel;
    }

    @JsonProperty("zipcode_label")
    public void setZipcodeLabel(String value) {
        this.mZipcodeLabel = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mExpirationDateLabel);
        parcel.writeString(this.mPendingToggleLabel);
        parcel.writeString(this.mPermitNumberAccuracyWarning);
        parcel.writeString(this.mPermitNumberLabel);
        parcel.writeString(this.mPermitNumberPlaceholder);
        parcel.writeString(this.mZipcodeLabel);
    }

    public void readFromParcel(Parcel source) {
        this.mExpirationDateLabel = source.readString();
        this.mPendingToggleLabel = source.readString();
        this.mPermitNumberAccuracyWarning = source.readString();
        this.mPermitNumberLabel = source.readString();
        this.mPermitNumberPlaceholder = source.readString();
        this.mZipcodeLabel = source.readString();
    }
}
