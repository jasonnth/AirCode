package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenCancellationPolicy implements Parcelable {
    @JsonProperty("body")
    protected List<String> mDetails;
    @JsonProperty("title")
    protected String mFormattedName;
    @JsonProperty("id")
    protected String mName;
    @JsonProperty("subtitle")
    protected String mShortDescription;

    protected GenCancellationPolicy(List<String> details, String name, String formattedName, String shortDescription) {
        this();
        this.mDetails = details;
        this.mName = name;
        this.mFormattedName = formattedName;
        this.mShortDescription = shortDescription;
    }

    protected GenCancellationPolicy() {
    }

    public List<String> getDetails() {
        return this.mDetails;
    }

    @JsonProperty("body")
    public void setDetails(List<String> value) {
        this.mDetails = value;
    }

    public String getName() {
        return this.mName;
    }

    @JsonProperty("id")
    public void setName(String value) {
        this.mName = value;
    }

    public String getFormattedName() {
        return this.mFormattedName;
    }

    @JsonProperty("title")
    public void setFormattedName(String value) {
        this.mFormattedName = value;
    }

    public String getShortDescription() {
        return this.mShortDescription;
    }

    @JsonProperty("subtitle")
    public void setShortDescription(String value) {
        this.mShortDescription = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeStringList(this.mDetails);
        parcel.writeString(this.mName);
        parcel.writeString(this.mFormattedName);
        parcel.writeString(this.mShortDescription);
    }

    public void readFromParcel(Parcel source) {
        this.mDetails = source.createStringArrayList();
        this.mName = source.readString();
        this.mFormattedName = source.readString();
        this.mShortDescription = source.readString();
    }
}
