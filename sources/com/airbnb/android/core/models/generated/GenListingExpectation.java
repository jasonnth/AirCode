package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenListingExpectation implements Parcelable {
    @JsonProperty("added_details")
    protected String mAddedDetails;
    @JsonProperty("checked")
    protected boolean mChecked;
    @JsonProperty("description")
    protected String mDescription;
    @JsonProperty("placeholder")
    protected String mPlaceholder;
    @JsonProperty("title")
    protected String mTitle;
    @JsonProperty("type")
    protected String mType;

    protected GenListingExpectation(String type, String title, String description, String placeholder, String addedDetails, boolean checked) {
        this();
        this.mType = type;
        this.mTitle = title;
        this.mDescription = description;
        this.mPlaceholder = placeholder;
        this.mAddedDetails = addedDetails;
        this.mChecked = checked;
    }

    protected GenListingExpectation() {
    }

    public String getType() {
        return this.mType;
    }

    @JsonProperty("type")
    public void setType(String value) {
        this.mType = value;
    }

    public String getTitle() {
        return this.mTitle;
    }

    @JsonProperty("title")
    public void setTitle(String value) {
        this.mTitle = value;
    }

    public String getDescription() {
        return this.mDescription;
    }

    @JsonProperty("description")
    public void setDescription(String value) {
        this.mDescription = value;
    }

    public String getPlaceholder() {
        return this.mPlaceholder;
    }

    @JsonProperty("placeholder")
    public void setPlaceholder(String value) {
        this.mPlaceholder = value;
    }

    public String getAddedDetails() {
        return this.mAddedDetails;
    }

    @JsonProperty("added_details")
    public void setAddedDetails(String value) {
        this.mAddedDetails = value;
    }

    public boolean isChecked() {
        return this.mChecked;
    }

    @JsonProperty("checked")
    public void setChecked(boolean value) {
        this.mChecked = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mType);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mDescription);
        parcel.writeString(this.mPlaceholder);
        parcel.writeString(this.mAddedDetails);
        parcel.writeBooleanArray(new boolean[]{this.mChecked});
    }

    public void readFromParcel(Parcel source) {
        this.mType = source.readString();
        this.mTitle = source.readString();
        this.mDescription = source.readString();
        this.mPlaceholder = source.readString();
        this.mAddedDetails = source.readString();
        this.mChecked = source.createBooleanArray()[0];
    }
}
