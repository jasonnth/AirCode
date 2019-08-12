package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.enums.ListingStatus;
import com.airbnb.android.core.models.FixItReport;
import com.airbnb.android.core.models.HomeCollectionApplication;
import com.airbnb.android.core.models.SnoozeMode;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenListingPickerInfo implements Parcelable {
    @JsonProperty("city")
    protected String mCity;
    @JsonProperty("homes_collections_application")
    protected HomeCollectionApplication mCollectionsApplication;
    @JsonProperty("fixit_report")
    protected FixItReport mFixItReport;
    @JsonProperty("has_ever_been_available")
    protected boolean mHasEverBeenAvailable;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("instant_book_eligible")
    protected boolean mInstantBookEligible;
    @JsonProperty("instant_book_enabled")
    protected boolean mInstantBookEnabled;
    @JsonProperty("list_your_space_last_finished_step_id")
    protected String mListYourSpaceLastFinishedStepId;
    @JsonProperty("name")
    protected String mName;
    @JsonProperty("room_type_category")
    protected String mRoomTypeCategory;
    @JsonProperty("smart_pricing_available")
    protected boolean mSmartPricingAvailable;
    @JsonProperty("smart_pricing_enabled")
    protected boolean mSmartPricingEnabled;
    @JsonProperty("snooze_mode")
    protected SnoozeMode mSnoozeMode;
    @JsonProperty("status")
    protected ListingStatus mStatus;
    @JsonProperty("steps_remaining")
    protected int mStepsRemaining;
    @JsonProperty("steps_total")
    protected int mStepsTotal;
    @JsonProperty("thumbnail_url")
    protected String mThumbnailUrl;

    protected GenListingPickerInfo(FixItReport fixItReport, HomeCollectionApplication collectionsApplication, ListingStatus status, SnoozeMode snoozeMode, String city, String listYourSpaceLastFinishedStepId, String name, String roomTypeCategory, String thumbnailUrl, boolean hasEverBeenAvailable, boolean instantBookEnabled, boolean instantBookEligible, boolean smartPricingAvailable, boolean smartPricingEnabled, int stepsRemaining, int stepsTotal, long id) {
        this();
        this.mFixItReport = fixItReport;
        this.mCollectionsApplication = collectionsApplication;
        this.mStatus = status;
        this.mSnoozeMode = snoozeMode;
        this.mCity = city;
        this.mListYourSpaceLastFinishedStepId = listYourSpaceLastFinishedStepId;
        this.mName = name;
        this.mRoomTypeCategory = roomTypeCategory;
        this.mThumbnailUrl = thumbnailUrl;
        this.mHasEverBeenAvailable = hasEverBeenAvailable;
        this.mInstantBookEnabled = instantBookEnabled;
        this.mInstantBookEligible = instantBookEligible;
        this.mSmartPricingAvailable = smartPricingAvailable;
        this.mSmartPricingEnabled = smartPricingEnabled;
        this.mStepsRemaining = stepsRemaining;
        this.mStepsTotal = stepsTotal;
        this.mId = id;
    }

    protected GenListingPickerInfo() {
    }

    public FixItReport getFixItReport() {
        return this.mFixItReport;
    }

    @JsonProperty("fixit_report")
    public void setFixItReport(FixItReport value) {
        this.mFixItReport = value;
    }

    public HomeCollectionApplication getCollectionsApplication() {
        return this.mCollectionsApplication;
    }

    @JsonProperty("homes_collections_application")
    public void setCollectionsApplication(HomeCollectionApplication value) {
        this.mCollectionsApplication = value;
    }

    public ListingStatus getStatus() {
        return this.mStatus;
    }

    @JsonProperty("status")
    public void setStatus(ListingStatus value) {
        this.mStatus = value;
    }

    public SnoozeMode getSnoozeMode() {
        return this.mSnoozeMode;
    }

    @JsonProperty("snooze_mode")
    public void setSnoozeMode(SnoozeMode value) {
        this.mSnoozeMode = value;
    }

    public String getCity() {
        return this.mCity;
    }

    @JsonProperty("city")
    public void setCity(String value) {
        this.mCity = value;
    }

    public String getListYourSpaceLastFinishedStepId() {
        return this.mListYourSpaceLastFinishedStepId;
    }

    @JsonProperty("list_your_space_last_finished_step_id")
    public void setListYourSpaceLastFinishedStepId(String value) {
        this.mListYourSpaceLastFinishedStepId = value;
    }

    public String getName() {
        return this.mName;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.mName = value;
    }

    public String getRoomTypeCategory() {
        return this.mRoomTypeCategory;
    }

    @JsonProperty("room_type_category")
    public void setRoomTypeCategory(String value) {
        this.mRoomTypeCategory = value;
    }

    public String getThumbnailUrl() {
        return this.mThumbnailUrl;
    }

    @JsonProperty("thumbnail_url")
    public void setThumbnailUrl(String value) {
        this.mThumbnailUrl = value;
    }

    public boolean hasEverBeenAvailable() {
        return this.mHasEverBeenAvailable;
    }

    @JsonProperty("has_ever_been_available")
    public void setHasEverBeenAvailable(boolean value) {
        this.mHasEverBeenAvailable = value;
    }

    public boolean isInstantBookEnabled() {
        return this.mInstantBookEnabled;
    }

    @JsonProperty("instant_book_enabled")
    public void setInstantBookEnabled(boolean value) {
        this.mInstantBookEnabled = value;
    }

    public boolean isInstantBookEligible() {
        return this.mInstantBookEligible;
    }

    @JsonProperty("instant_book_eligible")
    public void setInstantBookEligible(boolean value) {
        this.mInstantBookEligible = value;
    }

    public boolean isSmartPricingAvailable() {
        return this.mSmartPricingAvailable;
    }

    @JsonProperty("smart_pricing_available")
    public void setSmartPricingAvailable(boolean value) {
        this.mSmartPricingAvailable = value;
    }

    public boolean isSmartPricingEnabled() {
        return this.mSmartPricingEnabled;
    }

    @JsonProperty("smart_pricing_enabled")
    public void setSmartPricingEnabled(boolean value) {
        this.mSmartPricingEnabled = value;
    }

    public int getStepsRemaining() {
        return this.mStepsRemaining;
    }

    @JsonProperty("steps_remaining")
    public void setStepsRemaining(int value) {
        this.mStepsRemaining = value;
    }

    public int getStepsTotal() {
        return this.mStepsTotal;
    }

    @JsonProperty("steps_total")
    public void setStepsTotal(int value) {
        this.mStepsTotal = value;
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
        parcel.writeParcelable(this.mFixItReport, 0);
        parcel.writeParcelable(this.mCollectionsApplication, 0);
        parcel.writeParcelable(this.mStatus, 0);
        parcel.writeParcelable(this.mSnoozeMode, 0);
        parcel.writeString(this.mCity);
        parcel.writeString(this.mListYourSpaceLastFinishedStepId);
        parcel.writeString(this.mName);
        parcel.writeString(this.mRoomTypeCategory);
        parcel.writeString(this.mThumbnailUrl);
        parcel.writeBooleanArray(new boolean[]{this.mHasEverBeenAvailable, this.mInstantBookEnabled, this.mInstantBookEligible, this.mSmartPricingAvailable, this.mSmartPricingEnabled});
        parcel.writeInt(this.mStepsRemaining);
        parcel.writeInt(this.mStepsTotal);
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mFixItReport = (FixItReport) source.readParcelable(FixItReport.class.getClassLoader());
        this.mCollectionsApplication = (HomeCollectionApplication) source.readParcelable(HomeCollectionApplication.class.getClassLoader());
        this.mStatus = (ListingStatus) source.readParcelable(ListingStatus.class.getClassLoader());
        this.mSnoozeMode = (SnoozeMode) source.readParcelable(SnoozeMode.class.getClassLoader());
        this.mCity = source.readString();
        this.mListYourSpaceLastFinishedStepId = source.readString();
        this.mName = source.readString();
        this.mRoomTypeCategory = source.readString();
        this.mThumbnailUrl = source.readString();
        boolean[] bools = source.createBooleanArray();
        this.mHasEverBeenAvailable = bools[0];
        this.mInstantBookEnabled = bools[1];
        this.mInstantBookEligible = bools[2];
        this.mSmartPricingAvailable = bools[3];
        this.mSmartPricingEnabled = bools[4];
        this.mStepsRemaining = source.readInt();
        this.mStepsTotal = source.readInt();
        this.mId = source.readLong();
    }
}
