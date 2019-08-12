package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.models.HomesCollectionsApplicationsListing;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenHomeCollectionApplication implements Parcelable {
    @JsonProperty("address")
    protected String mAddress;
    @JsonProperty("amenities")
    protected List<Integer> mAmenities;
    @JsonProperty("booking_start")
    protected AirDateTime mBookingStart;
    @JsonProperty("host_id")
    protected long mHostId;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("job_id")
    protected String mJobId;
    @JsonProperty("listing_id")
    protected long mListindId;
    @JsonProperty("listing")
    protected HomesCollectionsApplicationsListing mListing;
    @JsonProperty("status")
    protected int mStatus;

    protected GenHomeCollectionApplication(AirDateTime bookingStart, HomesCollectionsApplicationsListing listing, List<Integer> amenities, String jobId, String address, int status, long id, long listindId, long hostId) {
        this();
        this.mBookingStart = bookingStart;
        this.mListing = listing;
        this.mAmenities = amenities;
        this.mJobId = jobId;
        this.mAddress = address;
        this.mStatus = status;
        this.mId = id;
        this.mListindId = listindId;
        this.mHostId = hostId;
    }

    protected GenHomeCollectionApplication() {
    }

    public AirDateTime getBookingStart() {
        return this.mBookingStart;
    }

    @JsonProperty("booking_start")
    public void setBookingStart(AirDateTime value) {
        this.mBookingStart = value;
    }

    public HomesCollectionsApplicationsListing getListing() {
        return this.mListing;
    }

    @JsonProperty("listing")
    public void setListing(HomesCollectionsApplicationsListing value) {
        this.mListing = value;
    }

    public List<Integer> getAmenities() {
        return this.mAmenities;
    }

    @JsonProperty("amenities")
    public void setAmenities(List<Integer> value) {
        this.mAmenities = value;
    }

    public String getJobId() {
        return this.mJobId;
    }

    @JsonProperty("job_id")
    public void setJobId(String value) {
        this.mJobId = value;
    }

    public String getAddress() {
        return this.mAddress;
    }

    @JsonProperty("address")
    public void setAddress(String value) {
        this.mAddress = value;
    }

    public int getStatus() {
        return this.mStatus;
    }

    @JsonProperty("status")
    public void setStatus(int value) {
        this.mStatus = value;
    }

    public long getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(long value) {
        this.mId = value;
    }

    public long getListindId() {
        return this.mListindId;
    }

    @JsonProperty("listing_id")
    public void setListindId(long value) {
        this.mListindId = value;
    }

    public long getHostId() {
        return this.mHostId;
    }

    @JsonProperty("host_id")
    public void setHostId(long value) {
        this.mHostId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mBookingStart, 0);
        parcel.writeParcelable(this.mListing, 0);
        parcel.writeValue(this.mAmenities);
        parcel.writeString(this.mJobId);
        parcel.writeString(this.mAddress);
        parcel.writeInt(this.mStatus);
        parcel.writeLong(this.mId);
        parcel.writeLong(this.mListindId);
        parcel.writeLong(this.mHostId);
    }

    public void readFromParcel(Parcel source) {
        this.mBookingStart = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mListing = (HomesCollectionsApplicationsListing) source.readParcelable(HomesCollectionsApplicationsListing.class.getClassLoader());
        this.mAmenities = (List) source.readValue(null);
        this.mJobId = source.readString();
        this.mAddress = source.readString();
        this.mStatus = source.readInt();
        this.mId = source.readLong();
        this.mListindId = source.readLong();
        this.mHostId = source.readLong();
    }
}
