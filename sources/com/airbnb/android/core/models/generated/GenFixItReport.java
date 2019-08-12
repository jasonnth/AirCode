package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.FixItItem;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenFixItReport implements Parcelable {
    @JsonProperty("host_id")
    protected long mHostId;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("items")
    protected List<FixItItem> mItems;
    @JsonProperty("listing_id")
    protected long mListingId;
    @JsonProperty("report_type")
    protected int mReportType;
    @JsonProperty("status")
    protected int mStatus;

    protected GenFixItReport(List<FixItItem> items, int status, int reportType, long hostId, long id, long listingId) {
        this();
        this.mItems = items;
        this.mStatus = status;
        this.mReportType = reportType;
        this.mHostId = hostId;
        this.mId = id;
        this.mListingId = listingId;
    }

    protected GenFixItReport() {
    }

    public List<FixItItem> getItems() {
        return this.mItems;
    }

    @JsonProperty("items")
    public void setItems(List<FixItItem> value) {
        this.mItems = value;
    }

    public int getStatus() {
        return this.mStatus;
    }

    @JsonProperty("status")
    public void setStatus(int value) {
        this.mStatus = value;
    }

    public int getReportType() {
        return this.mReportType;
    }

    @JsonProperty("report_type")
    public void setReportType(int value) {
        this.mReportType = value;
    }

    public long getHostId() {
        return this.mHostId;
    }

    @JsonProperty("host_id")
    public void setHostId(long value) {
        this.mHostId = value;
    }

    public long getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(long value) {
        this.mId = value;
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
        parcel.writeTypedList(this.mItems);
        parcel.writeInt(this.mStatus);
        parcel.writeInt(this.mReportType);
        parcel.writeLong(this.mHostId);
        parcel.writeLong(this.mId);
        parcel.writeLong(this.mListingId);
    }

    public void readFromParcel(Parcel source) {
        this.mItems = source.createTypedArrayList(FixItItem.CREATOR);
        this.mStatus = source.readInt();
        this.mReportType = source.readInt();
        this.mHostId = source.readLong();
        this.mId = source.readLong();
        this.mListingId = source.readLong();
    }
}
