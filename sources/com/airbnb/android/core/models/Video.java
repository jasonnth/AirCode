package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.CoreApplication;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Video implements Parcelable {
    public static final Creator<Video> CREATOR = new Creator<Video>() {
        public Video[] newArray(int size) {
            return new Video[size];
        }

        public Video createFromParcel(Parcel source) {
            Video object = new Video();
            object.readFromParcel(source);
            return object;
        }
    };
    @JsonProperty("mp4_600k")
    protected String largeUrl;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("mp4_400k")
    protected String mediumUrl;
    @JsonProperty("mp4_200k")
    protected String smallUrl;
    @JsonProperty("mp4_800k")
    protected String xLargeUrl;

    public String getUrl() {
        switch (CoreApplication.instance().component().networkMonitor().getNetworkClass()) {
            case TYPE_ROAMING:
            case TYPE_2G:
            case TYPE_3G:
                return this.smallUrl;
            case TYPE_4G:
                return this.mediumUrl;
            case TYPE_WIFI:
                return this.xLargeUrl;
            default:
                return this.largeUrl;
        }
    }

    @JsonProperty("mp4_200k")
    public void setSmallUrl(String value) {
        this.smallUrl = value;
    }

    @JsonProperty("mp4_400k")
    public void setMediumUrl(String value) {
        this.mediumUrl = value;
    }

    @JsonProperty("mp4_600k")
    public void setLargeUrl(String value) {
        this.largeUrl = value;
    }

    @JsonProperty("mp4_800k")
    public void setXLargeUrl(String value) {
        this.xLargeUrl = value;
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
        parcel.writeString(this.smallUrl);
        parcel.writeString(this.mediumUrl);
        parcel.writeString(this.largeUrl);
        parcel.writeString(this.xLargeUrl);
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.smallUrl = source.readString();
        this.mediumUrl = source.readString();
        this.largeUrl = source.readString();
        this.xLargeUrl = source.readString();
        this.mId = source.readLong();
    }
}
