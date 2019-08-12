package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenListingWirelessInfo implements Parcelable {
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("hosting_id")
    protected long mListingId;
    @JsonProperty("wireless_password")
    protected String mWirelessPassword;
    @JsonProperty("wireless_ssid")
    protected String mWirelessSsid;
    @JsonProperty("wireless_type")
    protected String mWirelessType;

    protected GenListingWirelessInfo(String wirelessType, String wirelessSsid, String wirelessPassword, long id, long listingId) {
        this();
        this.mWirelessType = wirelessType;
        this.mWirelessSsid = wirelessSsid;
        this.mWirelessPassword = wirelessPassword;
        this.mId = id;
        this.mListingId = listingId;
    }

    protected GenListingWirelessInfo() {
    }

    public String getWirelessType() {
        return this.mWirelessType;
    }

    @JsonProperty("wireless_type")
    public void setWirelessType(String value) {
        this.mWirelessType = value;
    }

    public String getWirelessSsid() {
        return this.mWirelessSsid;
    }

    @JsonProperty("wireless_ssid")
    public void setWirelessSsid(String value) {
        this.mWirelessSsid = value;
    }

    public String getWirelessPassword() {
        return this.mWirelessPassword;
    }

    @JsonProperty("wireless_password")
    public void setWirelessPassword(String value) {
        this.mWirelessPassword = value;
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

    @JsonProperty("hosting_id")
    public void setListingId(long value) {
        this.mListingId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mWirelessType);
        parcel.writeString(this.mWirelessSsid);
        parcel.writeString(this.mWirelessPassword);
        parcel.writeLong(this.mId);
        parcel.writeLong(this.mListingId);
    }

    public void readFromParcel(Parcel source) {
        this.mWirelessType = source.readString();
        this.mWirelessSsid = source.readString();
        this.mWirelessPassword = source.readString();
        this.mId = source.readLong();
        this.mListingId = source.readLong();
    }
}
