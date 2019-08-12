package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenLocalizedCancellationPolicy implements Parcelable {
    @JsonProperty("localized_description")
    protected String mLocalizedDescription;
    @JsonProperty("localized_title")
    protected String mLocalizedTitle;
    @JsonProperty("policy_id")
    protected String mPolicyId;

    protected GenLocalizedCancellationPolicy(String localizedDescription, String localizedTitle, String policyId) {
        this();
        this.mLocalizedDescription = localizedDescription;
        this.mLocalizedTitle = localizedTitle;
        this.mPolicyId = policyId;
    }

    protected GenLocalizedCancellationPolicy() {
    }

    public String getLocalizedDescription() {
        return this.mLocalizedDescription;
    }

    @JsonProperty("localized_description")
    public void setLocalizedDescription(String value) {
        this.mLocalizedDescription = value;
    }

    public String getLocalizedTitle() {
        return this.mLocalizedTitle;
    }

    @JsonProperty("localized_title")
    public void setLocalizedTitle(String value) {
        this.mLocalizedTitle = value;
    }

    public String getPolicyId() {
        return this.mPolicyId;
    }

    @JsonProperty("policy_id")
    public void setPolicyId(String value) {
        this.mPolicyId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mLocalizedDescription);
        parcel.writeString(this.mLocalizedTitle);
        parcel.writeString(this.mPolicyId);
    }

    public void readFromParcel(Parcel source) {
        this.mLocalizedDescription = source.readString();
        this.mLocalizedTitle = source.readString();
        this.mPolicyId = source.readString();
    }
}
