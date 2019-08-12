package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.models.ListingRegistrationSubmissionData;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
import java.util.List;

public abstract class GenListingRegistrationSubmission implements Parcelable {
    @JsonProperty("additional_data")
    protected List<ListingRegistrationSubmissionData> mAdditionalData;
    @JsonProperty("answers")
    protected HashMap mAnswers;
    @JsonProperty("created_at")
    protected AirDateTime mCreatedAt;
    @JsonProperty("email")
    protected String mEmail;
    @JsonProperty("full_address")
    protected String mFullAddress;
    @JsonProperty("full_name")
    protected String mFullName;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("listing_registration_id")
    protected long mListingRegistrationId;

    protected GenListingRegistrationSubmission(AirDateTime createdAt, HashMap answers, List<ListingRegistrationSubmissionData> additionalData, String email, String fullAddress, String fullName, long id, long listingRegistrationId) {
        this();
        this.mCreatedAt = createdAt;
        this.mAnswers = answers;
        this.mAdditionalData = additionalData;
        this.mEmail = email;
        this.mFullAddress = fullAddress;
        this.mFullName = fullName;
        this.mId = id;
        this.mListingRegistrationId = listingRegistrationId;
    }

    protected GenListingRegistrationSubmission() {
    }

    public AirDateTime getCreatedAt() {
        return this.mCreatedAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(AirDateTime value) {
        this.mCreatedAt = value;
    }

    public HashMap getAnswers() {
        return this.mAnswers;
    }

    @JsonProperty("answers")
    public void setAnswers(HashMap value) {
        this.mAnswers = value;
    }

    public List<ListingRegistrationSubmissionData> getAdditionalData() {
        return this.mAdditionalData;
    }

    @JsonProperty("additional_data")
    public void setAdditionalData(List<ListingRegistrationSubmissionData> value) {
        this.mAdditionalData = value;
    }

    public String getEmail() {
        return this.mEmail;
    }

    @JsonProperty("email")
    public void setEmail(String value) {
        this.mEmail = value;
    }

    public String getFullAddress() {
        return this.mFullAddress;
    }

    @JsonProperty("full_address")
    public void setFullAddress(String value) {
        this.mFullAddress = value;
    }

    public String getFullName() {
        return this.mFullName;
    }

    @JsonProperty("full_name")
    public void setFullName(String value) {
        this.mFullName = value;
    }

    public long getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(long value) {
        this.mId = value;
    }

    public long getListingRegistrationId() {
        return this.mListingRegistrationId;
    }

    @JsonProperty("listing_registration_id")
    public void setListingRegistrationId(long value) {
        this.mListingRegistrationId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mCreatedAt, 0);
        parcel.writeSerializable(this.mAnswers);
        parcel.writeTypedList(this.mAdditionalData);
        parcel.writeString(this.mEmail);
        parcel.writeString(this.mFullAddress);
        parcel.writeString(this.mFullName);
        parcel.writeLong(this.mId);
        parcel.writeLong(this.mListingRegistrationId);
    }

    public void readFromParcel(Parcel source) {
        this.mCreatedAt = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mAnswers = (HashMap) source.readSerializable();
        this.mAdditionalData = source.createTypedArrayList(ListingRegistrationSubmissionData.CREATOR);
        this.mEmail = source.readString();
        this.mFullAddress = source.readString();
        this.mFullName = source.readString();
        this.mId = source.readLong();
        this.mListingRegistrationId = source.readLong();
    }
}
