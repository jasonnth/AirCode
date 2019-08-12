package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenCohostingContract implements Parcelable {
    @JsonProperty("accepted_at")
    protected AirDateTime mAcceptanceDate;
    @JsonProperty("amount_currency")
    protected String mAmountCurrency;
    @JsonProperty("can_access_resolution_center")
    protected Boolean mCanAccessResolutionCenter;
    @JsonProperty("cohost")
    protected User mCohost;
    @JsonProperty("cohost_currency")
    protected String mCohostCurrency;
    @JsonProperty("ended_at")
    protected AirDateTime mEndDate;
    @JsonProperty("fixed_amount")
    protected long mFixedAmount;
    @JsonProperty("formatted_maximum_fee")
    protected String mFormattedMaximumFee;
    @JsonProperty("formatted_minimum_fee")
    protected String mFormattedMinimumFee;
    @JsonProperty("payout_split_rule_description_light")
    protected String mHostingFee;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("include_cleaning_fee")
    protected Boolean mIncludeCleaningFee;
    @JsonProperty("listing_name")
    protected String mListingName;
    @JsonProperty("listing_thumbnail_url")
    protected String mListingThumbnailUrl;
    @JsonProperty("maximum_fee")
    protected long mMaximumFee;
    @JsonProperty("minimum_fee")
    protected long mMinimumFee;
    @JsonProperty("owner")
    protected User mOwner;
    @JsonProperty("percentage")
    protected int mPercentage;
    @JsonProperty("services")
    protected int[] mServices;
    @JsonProperty("source")
    protected int mSource;
    @JsonProperty("started_at")
    protected AirDateTime mStartDate;
    @JsonProperty("contract_status")
    protected int mStatus;

    protected GenCohostingContract(AirDateTime startDate, AirDateTime endDate, AirDateTime acceptanceDate, Boolean includeCleaningFee, Boolean canAccessResolutionCenter, String amountCurrency, String cohostCurrency, String formattedMaximumFee, String formattedMinimumFee, String hostingFee, String listingName, String listingThumbnailUrl, User owner, User cohost, int source, int percentage, int status, int[] services, long id, long fixedAmount, long minimumFee, long maximumFee) {
        this();
        this.mStartDate = startDate;
        this.mEndDate = endDate;
        this.mAcceptanceDate = acceptanceDate;
        this.mIncludeCleaningFee = includeCleaningFee;
        this.mCanAccessResolutionCenter = canAccessResolutionCenter;
        this.mAmountCurrency = amountCurrency;
        this.mCohostCurrency = cohostCurrency;
        this.mFormattedMaximumFee = formattedMaximumFee;
        this.mFormattedMinimumFee = formattedMinimumFee;
        this.mHostingFee = hostingFee;
        this.mListingName = listingName;
        this.mListingThumbnailUrl = listingThumbnailUrl;
        this.mOwner = owner;
        this.mCohost = cohost;
        this.mSource = source;
        this.mPercentage = percentage;
        this.mStatus = status;
        this.mServices = services;
        this.mId = id;
        this.mFixedAmount = fixedAmount;
        this.mMinimumFee = minimumFee;
        this.mMaximumFee = maximumFee;
    }

    protected GenCohostingContract() {
    }

    public AirDateTime getStartDate() {
        return this.mStartDate;
    }

    @JsonProperty("started_at")
    public void setStartDate(AirDateTime value) {
        this.mStartDate = value;
    }

    public AirDateTime getEndDate() {
        return this.mEndDate;
    }

    @JsonProperty("ended_at")
    public void setEndDate(AirDateTime value) {
        this.mEndDate = value;
    }

    public AirDateTime getAcceptanceDate() {
        return this.mAcceptanceDate;
    }

    @JsonProperty("accepted_at")
    public void setAcceptanceDate(AirDateTime value) {
        this.mAcceptanceDate = value;
    }

    public Boolean isIncludeCleaningFee() {
        return this.mIncludeCleaningFee;
    }

    @JsonProperty("include_cleaning_fee")
    public void setIncludeCleaningFee(Boolean value) {
        this.mIncludeCleaningFee = value;
    }

    public Boolean isCanAccessResolutionCenter() {
        return this.mCanAccessResolutionCenter;
    }

    @JsonProperty("can_access_resolution_center")
    public void setCanAccessResolutionCenter(Boolean value) {
        this.mCanAccessResolutionCenter = value;
    }

    public String getAmountCurrency() {
        return this.mAmountCurrency;
    }

    @JsonProperty("amount_currency")
    public void setAmountCurrency(String value) {
        this.mAmountCurrency = value;
    }

    public String getCohostCurrency() {
        return this.mCohostCurrency;
    }

    @JsonProperty("cohost_currency")
    public void setCohostCurrency(String value) {
        this.mCohostCurrency = value;
    }

    public String getFormattedMaximumFee() {
        return this.mFormattedMaximumFee;
    }

    @JsonProperty("formatted_maximum_fee")
    public void setFormattedMaximumFee(String value) {
        this.mFormattedMaximumFee = value;
    }

    public String getFormattedMinimumFee() {
        return this.mFormattedMinimumFee;
    }

    @JsonProperty("formatted_minimum_fee")
    public void setFormattedMinimumFee(String value) {
        this.mFormattedMinimumFee = value;
    }

    public String getHostingFee() {
        return this.mHostingFee;
    }

    @JsonProperty("payout_split_rule_description_light")
    public void setHostingFee(String value) {
        this.mHostingFee = value;
    }

    public String getListingName() {
        return this.mListingName;
    }

    @JsonProperty("listing_name")
    public void setListingName(String value) {
        this.mListingName = value;
    }

    public String getListingThumbnailUrl() {
        return this.mListingThumbnailUrl;
    }

    @JsonProperty("listing_thumbnail_url")
    public void setListingThumbnailUrl(String value) {
        this.mListingThumbnailUrl = value;
    }

    public User getOwner() {
        return this.mOwner;
    }

    @JsonProperty("owner")
    public void setOwner(User value) {
        this.mOwner = value;
    }

    public User getCohost() {
        return this.mCohost;
    }

    @JsonProperty("cohost")
    public void setCohost(User value) {
        this.mCohost = value;
    }

    public int getSource() {
        return this.mSource;
    }

    @JsonProperty("source")
    public void setSource(int value) {
        this.mSource = value;
    }

    public int getPercentage() {
        return this.mPercentage;
    }

    @JsonProperty("percentage")
    public void setPercentage(int value) {
        this.mPercentage = value;
    }

    public int getStatus() {
        return this.mStatus;
    }

    @JsonProperty("contract_status")
    public void setStatus(int value) {
        this.mStatus = value;
    }

    public int[] getServices() {
        return this.mServices;
    }

    @JsonProperty("services")
    public void setServices(int[] value) {
        this.mServices = value;
    }

    public long getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(long value) {
        this.mId = value;
    }

    public long getFixedAmount() {
        return this.mFixedAmount;
    }

    @JsonProperty("fixed_amount")
    public void setFixedAmount(long value) {
        this.mFixedAmount = value;
    }

    public long getMinimumFee() {
        return this.mMinimumFee;
    }

    @JsonProperty("minimum_fee")
    public void setMinimumFee(long value) {
        this.mMinimumFee = value;
    }

    public long getMaximumFee() {
        return this.mMaximumFee;
    }

    @JsonProperty("maximum_fee")
    public void setMaximumFee(long value) {
        this.mMaximumFee = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mStartDate, 0);
        parcel.writeParcelable(this.mEndDate, 0);
        parcel.writeParcelable(this.mAcceptanceDate, 0);
        parcel.writeValue(this.mIncludeCleaningFee);
        parcel.writeValue(this.mCanAccessResolutionCenter);
        parcel.writeString(this.mAmountCurrency);
        parcel.writeString(this.mCohostCurrency);
        parcel.writeString(this.mFormattedMaximumFee);
        parcel.writeString(this.mFormattedMinimumFee);
        parcel.writeString(this.mHostingFee);
        parcel.writeString(this.mListingName);
        parcel.writeString(this.mListingThumbnailUrl);
        parcel.writeParcelable(this.mOwner, 0);
        parcel.writeParcelable(this.mCohost, 0);
        parcel.writeInt(this.mSource);
        parcel.writeInt(this.mPercentage);
        parcel.writeInt(this.mStatus);
        parcel.writeIntArray(this.mServices);
        parcel.writeLong(this.mId);
        parcel.writeLong(this.mFixedAmount);
        parcel.writeLong(this.mMinimumFee);
        parcel.writeLong(this.mMaximumFee);
    }

    public void readFromParcel(Parcel source) {
        this.mStartDate = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mEndDate = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mAcceptanceDate = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mIncludeCleaningFee = (Boolean) source.readValue(Boolean.class.getClassLoader());
        this.mCanAccessResolutionCenter = (Boolean) source.readValue(Boolean.class.getClassLoader());
        this.mAmountCurrency = source.readString();
        this.mCohostCurrency = source.readString();
        this.mFormattedMaximumFee = source.readString();
        this.mFormattedMinimumFee = source.readString();
        this.mHostingFee = source.readString();
        this.mListingName = source.readString();
        this.mListingThumbnailUrl = source.readString();
        this.mOwner = (User) source.readParcelable(User.class.getClassLoader());
        this.mCohost = (User) source.readParcelable(User.class.getClassLoader());
        this.mSource = source.readInt();
        this.mPercentage = source.readInt();
        this.mStatus = source.readInt();
        this.mServices = source.createIntArray();
        this.mId = source.readLong();
        this.mFixedAmount = source.readLong();
        this.mMinimumFee = source.readLong();
        this.mMaximumFee = source.readLong();
    }
}
