package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.models.CalendarDay;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenListingCalendar implements Parcelable {
    @JsonProperty("days")
    protected List<CalendarDay> mCalendarDays;
    @JsonProperty("dynamic_pricing_control_is_enabled")
    protected boolean mDynamicPricingControlIsEnabled;
    @JsonProperty("dynamic_pricing_metadata_updated_at")
    protected AirDateTime mDynamicPricingMetadataUpdatedAt;
    @JsonProperty("end_date")
    protected AirDate mEndDate;
    @JsonProperty("listing_active")
    protected boolean mListingActive;
    @JsonProperty("listing_id")
    protected long mListingId;
    @JsonProperty("listing_name")
    protected String mListingName;
    @JsonProperty("listing_thumbnail_url")
    protected String mListingThumbnailUrl;
    @JsonProperty("start_date")
    protected AirDate mStartDate;

    protected GenListingCalendar(AirDate startDate, AirDate endDate, AirDateTime dynamicPricingMetadataUpdatedAt, List<CalendarDay> calendarDays, String listingName, String listingThumbnailUrl, boolean dynamicPricingControlIsEnabled, boolean listingActive, long listingId) {
        this();
        this.mStartDate = startDate;
        this.mEndDate = endDate;
        this.mDynamicPricingMetadataUpdatedAt = dynamicPricingMetadataUpdatedAt;
        this.mCalendarDays = calendarDays;
        this.mListingName = listingName;
        this.mListingThumbnailUrl = listingThumbnailUrl;
        this.mDynamicPricingControlIsEnabled = dynamicPricingControlIsEnabled;
        this.mListingActive = listingActive;
        this.mListingId = listingId;
    }

    protected GenListingCalendar() {
    }

    public AirDate getStartDate() {
        return this.mStartDate;
    }

    @JsonProperty("start_date")
    public void setStartDate(AirDate value) {
        this.mStartDate = value;
    }

    public AirDate getEndDate() {
        return this.mEndDate;
    }

    @JsonProperty("end_date")
    public void setEndDate(AirDate value) {
        this.mEndDate = value;
    }

    public AirDateTime getDynamicPricingMetadataUpdatedAt() {
        return this.mDynamicPricingMetadataUpdatedAt;
    }

    @JsonProperty("dynamic_pricing_metadata_updated_at")
    public void setDynamicPricingMetadataUpdatedAt(AirDateTime value) {
        this.mDynamicPricingMetadataUpdatedAt = value;
    }

    public List<CalendarDay> getCalendarDays() {
        return this.mCalendarDays;
    }

    @JsonProperty("days")
    public void setCalendarDays(List<CalendarDay> value) {
        this.mCalendarDays = value;
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

    public boolean isDynamicPricingControlIsEnabled() {
        return this.mDynamicPricingControlIsEnabled;
    }

    @JsonProperty("dynamic_pricing_control_is_enabled")
    public void setDynamicPricingControlIsEnabled(boolean value) {
        this.mDynamicPricingControlIsEnabled = value;
    }

    public boolean isListingActive() {
        return this.mListingActive;
    }

    @JsonProperty("listing_active")
    public void setListingActive(boolean value) {
        this.mListingActive = value;
    }

    public long getListingId() {
        return this.mListingId;
    }

    @JsonProperty("listing_id")
    public void setListingId(long value) {
        this.mListingId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mStartDate, 0);
        parcel.writeParcelable(this.mEndDate, 0);
        parcel.writeParcelable(this.mDynamicPricingMetadataUpdatedAt, 0);
        parcel.writeTypedList(this.mCalendarDays);
        parcel.writeString(this.mListingName);
        parcel.writeString(this.mListingThumbnailUrl);
        parcel.writeBooleanArray(new boolean[]{this.mDynamicPricingControlIsEnabled, this.mListingActive});
        parcel.writeLong(this.mListingId);
    }

    public void readFromParcel(Parcel source) {
        this.mStartDate = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mEndDate = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mDynamicPricingMetadataUpdatedAt = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mCalendarDays = source.createTypedArrayList(CalendarDay.CREATOR);
        this.mListingName = source.readString();
        this.mListingThumbnailUrl = source.readString();
        boolean[] bools = source.createBooleanArray();
        this.mDynamicPricingControlIsEnabled = bools[0];
        this.mListingActive = bools[1];
        this.mListingId = source.readLong();
    }
}
