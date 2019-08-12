package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.VerificationGroups;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenVerificationRequirements implements Parcelable {
    @JsonProperty("encrypted_user_id")
    protected String mEncryptedUserId;
    @JsonProperty("requirement")
    protected String mRequirement;
    @JsonProperty("groups")
    protected VerificationGroups mVerificationGroups;

    protected GenVerificationRequirements(String encryptedUserId, String requirement, VerificationGroups verificationGroups) {
        this();
        this.mEncryptedUserId = encryptedUserId;
        this.mRequirement = requirement;
        this.mVerificationGroups = verificationGroups;
    }

    protected GenVerificationRequirements() {
    }

    public String getEncryptedUserId() {
        return this.mEncryptedUserId;
    }

    @JsonProperty("encrypted_user_id")
    public void setEncryptedUserId(String value) {
        this.mEncryptedUserId = value;
    }

    public String getRequirement() {
        return this.mRequirement;
    }

    @JsonProperty("requirement")
    public void setRequirement(String value) {
        this.mRequirement = value;
    }

    public VerificationGroups getVerificationGroups() {
        return this.mVerificationGroups;
    }

    @JsonProperty("groups")
    public void setVerificationGroups(VerificationGroups value) {
        this.mVerificationGroups = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mEncryptedUserId);
        parcel.writeString(this.mRequirement);
        parcel.writeParcelable(this.mVerificationGroups, 0);
    }

    public void readFromParcel(Parcel source) {
        this.mEncryptedUserId = source.readString();
        this.mRequirement = source.readString();
        this.mVerificationGroups = (VerificationGroups) source.readParcelable(VerificationGroups.class.getClassLoader());
    }
}
