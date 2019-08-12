package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.enums.ListingRegistrationStatus;
import com.airbnb.android.core.models.ListingRegistrationExemptionData;
import com.airbnb.android.core.models.ListingRegistrationSubmission;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenListingRegistration implements Parcelable {
    @JsonProperty("exemption_data")
    protected ListingRegistrationExemptionData mExemptionData;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("listing_ids")
    protected List<Long> mListingIds;
    @JsonProperty("listing_registration_submissions")
    protected List<ListingRegistrationSubmission> mListingRegistrationSubmissions;
    @JsonProperty("permit_number")
    protected String mPermitNumber;
    @JsonProperty("regulatory_body")
    protected String mRegulatoryBody;
    @JsonProperty("status")
    protected ListingRegistrationStatus mStatus;
    @JsonProperty("updated_at")
    protected AirDateTime mUpdatedAt;

    protected GenListingRegistration(AirDateTime updatedAt, List<ListingRegistrationSubmission> listingRegistrationSubmissions, List<Long> listingIds, ListingRegistrationExemptionData exemptionData, ListingRegistrationStatus status, String permitNumber, String regulatoryBody, long id) {
        this();
        this.mUpdatedAt = updatedAt;
        this.mListingRegistrationSubmissions = listingRegistrationSubmissions;
        this.mListingIds = listingIds;
        this.mExemptionData = exemptionData;
        this.mStatus = status;
        this.mPermitNumber = permitNumber;
        this.mRegulatoryBody = regulatoryBody;
        this.mId = id;
    }

    protected GenListingRegistration() {
    }

    public AirDateTime getUpdatedAt() {
        return this.mUpdatedAt;
    }

    @JsonProperty("updated_at")
    public void setUpdatedAt(AirDateTime value) {
        this.mUpdatedAt = value;
    }

    public List<ListingRegistrationSubmission> getListingRegistrationSubmissions() {
        return this.mListingRegistrationSubmissions;
    }

    @JsonProperty("listing_registration_submissions")
    public void setListingRegistrationSubmissions(List<ListingRegistrationSubmission> value) {
        this.mListingRegistrationSubmissions = value;
    }

    public List<Long> getListingIds() {
        return this.mListingIds;
    }

    @JsonProperty("listing_ids")
    public void setListingIds(List<Long> value) {
        this.mListingIds = value;
    }

    public ListingRegistrationExemptionData getExemptionData() {
        return this.mExemptionData;
    }

    @JsonProperty("exemption_data")
    public void setExemptionData(ListingRegistrationExemptionData value) {
        this.mExemptionData = value;
    }

    public ListingRegistrationStatus getStatus() {
        return this.mStatus;
    }

    @JsonProperty("status")
    public void setStatus(ListingRegistrationStatus value) {
        this.mStatus = value;
    }

    public String getPermitNumber() {
        return this.mPermitNumber;
    }

    @JsonProperty("permit_number")
    public void setPermitNumber(String value) {
        this.mPermitNumber = value;
    }

    public String getRegulatoryBody() {
        return this.mRegulatoryBody;
    }

    @JsonProperty("regulatory_body")
    public void setRegulatoryBody(String value) {
        this.mRegulatoryBody = value;
    }

    public long getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(long value) {
        this.mId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mUpdatedAt, 0);
        parcel.writeTypedList(this.mListingRegistrationSubmissions);
        parcel.writeValue(this.mListingIds);
        parcel.writeParcelable(this.mExemptionData, 0);
        parcel.writeParcelable(this.mStatus, 0);
        parcel.writeString(this.mPermitNumber);
        parcel.writeString(this.mRegulatoryBody);
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mUpdatedAt = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mListingRegistrationSubmissions = source.createTypedArrayList(ListingRegistrationSubmission.CREATOR);
        this.mListingIds = (List) source.readValue(null);
        this.mExemptionData = (ListingRegistrationExemptionData) source.readParcelable(ListingRegistrationExemptionData.class.getClassLoader());
        this.mStatus = (ListingRegistrationStatus) source.readParcelable(ListingRegistrationStatus.class.getClassLoader());
        this.mPermitNumber = source.readString();
        this.mRegulatoryBody = source.readString();
        this.mId = source.readLong();
    }
}
