package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.models.CohostingContract;
import com.airbnb.android.core.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenListingManager implements Parcelable {
    @JsonProperty("contract")
    protected CohostingContract mContract;
    @JsonProperty("created_at")
    protected AirDate mCreatedAt;
    @JsonProperty("id")
    protected String mId;
    @JsonProperty("is_listing_admin")
    protected Boolean mIsListingAdmin;
    @JsonProperty("is_primary_host")
    protected Boolean mIsPrimaryHost;
    @JsonProperty("max_number_of_cohosts_per_listing")
    protected Integer mMaxNumberOfCohostsPerListing;
    @JsonProperty("thread_id")
    protected long mThreadId;
    @JsonProperty("user")
    protected User mUser;

    protected GenListingManager(AirDate createdAt, Boolean isPrimaryHost, Boolean isListingAdmin, CohostingContract contract, Integer maxNumberOfCohostsPerListing, String id, User user, long threadId) {
        this();
        this.mCreatedAt = createdAt;
        this.mIsPrimaryHost = isPrimaryHost;
        this.mIsListingAdmin = isListingAdmin;
        this.mContract = contract;
        this.mMaxNumberOfCohostsPerListing = maxNumberOfCohostsPerListing;
        this.mId = id;
        this.mUser = user;
        this.mThreadId = threadId;
    }

    protected GenListingManager() {
    }

    public AirDate getCreatedAt() {
        return this.mCreatedAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(AirDate value) {
        this.mCreatedAt = value;
    }

    public Boolean isIsPrimaryHost() {
        return this.mIsPrimaryHost;
    }

    @JsonProperty("is_primary_host")
    public void setIsPrimaryHost(Boolean value) {
        this.mIsPrimaryHost = value;
    }

    public Boolean isIsListingAdmin() {
        return this.mIsListingAdmin;
    }

    @JsonProperty("is_listing_admin")
    public void setIsListingAdmin(Boolean value) {
        this.mIsListingAdmin = value;
    }

    public CohostingContract getContract() {
        return this.mContract;
    }

    @JsonProperty("contract")
    public void setContract(CohostingContract value) {
        this.mContract = value;
    }

    public Integer getMaxNumberOfCohostsPerListing() {
        return this.mMaxNumberOfCohostsPerListing;
    }

    @JsonProperty("max_number_of_cohosts_per_listing")
    public void setMaxNumberOfCohostsPerListing(Integer value) {
        this.mMaxNumberOfCohostsPerListing = value;
    }

    public String getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(String value) {
        this.mId = value;
    }

    public User getUser() {
        return this.mUser;
    }

    @JsonProperty("user")
    public void setUser(User value) {
        this.mUser = value;
    }

    public long getThreadId() {
        return this.mThreadId;
    }

    @JsonProperty("thread_id")
    public void setThreadId(long value) {
        this.mThreadId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mCreatedAt, 0);
        parcel.writeValue(this.mIsPrimaryHost);
        parcel.writeValue(this.mIsListingAdmin);
        parcel.writeParcelable(this.mContract, 0);
        parcel.writeValue(this.mMaxNumberOfCohostsPerListing);
        parcel.writeString(this.mId);
        parcel.writeParcelable(this.mUser, 0);
        parcel.writeLong(this.mThreadId);
    }

    public void readFromParcel(Parcel source) {
        this.mCreatedAt = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mIsPrimaryHost = (Boolean) source.readValue(Boolean.class.getClassLoader());
        this.mIsListingAdmin = (Boolean) source.readValue(Boolean.class.getClassLoader());
        this.mContract = (CohostingContract) source.readParcelable(CohostingContract.class.getClassLoader());
        this.mMaxNumberOfCohostsPerListing = (Integer) source.readValue(Integer.class.getClassLoader());
        this.mId = source.readString();
        this.mUser = (User) source.readParcelable(User.class.getClassLoader());
        this.mThreadId = source.readLong();
    }
}
