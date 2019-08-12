package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenMagicalTripAttachmentDetails implements Parcelable {
    @JsonProperty("ends_at")
    protected AirDateTime mEndsAt;
    @JsonProperty("image_url")
    protected String mImageUrl;
    @JsonProperty("listing_id")
    protected long mListingId;
    @JsonProperty("name")
    protected String mName;
    @JsonProperty("scheduled_template_id")
    protected long mScheduledTemplateId;
    @JsonProperty("starts_at")
    protected AirDateTime mStartsAt;
    @JsonProperty("template_id")
    protected long mTemplateId;
    @JsonProperty("template_product_type")
    protected int mTemplateProductType;
    @JsonProperty("trip_id")
    protected long mTripId;

    protected GenMagicalTripAttachmentDetails(AirDateTime endsAt, AirDateTime startsAt, String name, String imageUrl, int templateProductType, long scheduledTemplateId, long templateId, long tripId, long listingId) {
        this();
        this.mEndsAt = endsAt;
        this.mStartsAt = startsAt;
        this.mName = name;
        this.mImageUrl = imageUrl;
        this.mTemplateProductType = templateProductType;
        this.mScheduledTemplateId = scheduledTemplateId;
        this.mTemplateId = templateId;
        this.mTripId = tripId;
        this.mListingId = listingId;
    }

    protected GenMagicalTripAttachmentDetails() {
    }

    public AirDateTime getEndsAt() {
        return this.mEndsAt;
    }

    @JsonProperty("ends_at")
    public void setEndsAt(AirDateTime value) {
        this.mEndsAt = value;
    }

    public AirDateTime getStartsAt() {
        return this.mStartsAt;
    }

    @JsonProperty("starts_at")
    public void setStartsAt(AirDateTime value) {
        this.mStartsAt = value;
    }

    public String getName() {
        return this.mName;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.mName = value;
    }

    public String getImageUrl() {
        return this.mImageUrl;
    }

    @JsonProperty("image_url")
    public void setImageUrl(String value) {
        this.mImageUrl = value;
    }

    public int getTemplateProductType() {
        return this.mTemplateProductType;
    }

    @JsonProperty("template_product_type")
    public void setTemplateProductType(int value) {
        this.mTemplateProductType = value;
    }

    public long getScheduledTemplateId() {
        return this.mScheduledTemplateId;
    }

    @JsonProperty("scheduled_template_id")
    public void setScheduledTemplateId(long value) {
        this.mScheduledTemplateId = value;
    }

    public long getTemplateId() {
        return this.mTemplateId;
    }

    @JsonProperty("template_id")
    public void setTemplateId(long value) {
        this.mTemplateId = value;
    }

    public long getTripId() {
        return this.mTripId;
    }

    @JsonProperty("trip_id")
    public void setTripId(long value) {
        this.mTripId = value;
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
        parcel.writeParcelable(this.mEndsAt, 0);
        parcel.writeParcelable(this.mStartsAt, 0);
        parcel.writeString(this.mName);
        parcel.writeString(this.mImageUrl);
        parcel.writeInt(this.mTemplateProductType);
        parcel.writeLong(this.mScheduledTemplateId);
        parcel.writeLong(this.mTemplateId);
        parcel.writeLong(this.mTripId);
        parcel.writeLong(this.mListingId);
    }

    public void readFromParcel(Parcel source) {
        this.mEndsAt = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mStartsAt = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mName = source.readString();
        this.mImageUrl = source.readString();
        this.mTemplateProductType = source.readInt();
        this.mScheduledTemplateId = source.readLong();
        this.mTemplateId = source.readLong();
        this.mTripId = source.readLong();
        this.mListingId = source.readLong();
    }
}
