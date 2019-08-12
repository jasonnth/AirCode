package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenSectionedListingDescription implements Parcelable {
    @JsonProperty("access")
    protected String mAccess;
    @JsonProperty("author_type")
    protected String mAuthorType;
    @JsonProperty("description")
    protected String mDescription;
    @JsonProperty("house_rules")
    protected String mHouseRules;
    @JsonProperty("interaction")
    protected String mInteraction;
    @JsonProperty("locale")
    protected String mLocale;
    @JsonProperty("localized_language_name")
    protected String mLocalizedLanguageName;
    @JsonProperty("name")
    protected String mName;
    @JsonProperty("neighborhood_overview")
    protected String mNeighborhoodOverview;
    @JsonProperty("notes")
    protected String mNotes;
    @JsonProperty("space")
    protected String mSpace;
    @JsonProperty("summary")
    protected String mSummary;
    @JsonProperty("transit")
    protected String mTransit;

    protected GenSectionedListingDescription(String access, String authorType, String description, String houseRules, String interaction, String locale, String localizedLanguageName, String name, String neighborhoodOverview, String notes, String space, String summary, String transit) {
        this();
        this.mAccess = access;
        this.mAuthorType = authorType;
        this.mDescription = description;
        this.mHouseRules = houseRules;
        this.mInteraction = interaction;
        this.mLocale = locale;
        this.mLocalizedLanguageName = localizedLanguageName;
        this.mName = name;
        this.mNeighborhoodOverview = neighborhoodOverview;
        this.mNotes = notes;
        this.mSpace = space;
        this.mSummary = summary;
        this.mTransit = transit;
    }

    protected GenSectionedListingDescription() {
    }

    public String getAccess() {
        return this.mAccess;
    }

    @JsonProperty("access")
    public void setAccess(String value) {
        this.mAccess = value;
    }

    public String getAuthorType() {
        return this.mAuthorType;
    }

    @JsonProperty("author_type")
    public void setAuthorType(String value) {
        this.mAuthorType = value;
    }

    public String getDescription() {
        return this.mDescription;
    }

    @JsonProperty("description")
    public void setDescription(String value) {
        this.mDescription = value;
    }

    public String getHouseRules() {
        return this.mHouseRules;
    }

    @JsonProperty("house_rules")
    public void setHouseRules(String value) {
        this.mHouseRules = value;
    }

    public String getInteraction() {
        return this.mInteraction;
    }

    @JsonProperty("interaction")
    public void setInteraction(String value) {
        this.mInteraction = value;
    }

    public String getLocale() {
        return this.mLocale;
    }

    @JsonProperty("locale")
    public void setLocale(String value) {
        this.mLocale = value;
    }

    public String getLocalizedLanguageName() {
        return this.mLocalizedLanguageName;
    }

    @JsonProperty("localized_language_name")
    public void setLocalizedLanguageName(String value) {
        this.mLocalizedLanguageName = value;
    }

    public String getName() {
        return this.mName;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.mName = value;
    }

    public String getNeighborhoodOverview() {
        return this.mNeighborhoodOverview;
    }

    @JsonProperty("neighborhood_overview")
    public void setNeighborhoodOverview(String value) {
        this.mNeighborhoodOverview = value;
    }

    public String getNotes() {
        return this.mNotes;
    }

    @JsonProperty("notes")
    public void setNotes(String value) {
        this.mNotes = value;
    }

    public String getSpace() {
        return this.mSpace;
    }

    @JsonProperty("space")
    public void setSpace(String value) {
        this.mSpace = value;
    }

    public String getSummary() {
        return this.mSummary;
    }

    @JsonProperty("summary")
    public void setSummary(String value) {
        this.mSummary = value;
    }

    public String getTransit() {
        return this.mTransit;
    }

    @JsonProperty("transit")
    public void setTransit(String value) {
        this.mTransit = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mAccess);
        parcel.writeString(this.mAuthorType);
        parcel.writeString(this.mDescription);
        parcel.writeString(this.mHouseRules);
        parcel.writeString(this.mInteraction);
        parcel.writeString(this.mLocale);
        parcel.writeString(this.mLocalizedLanguageName);
        parcel.writeString(this.mName);
        parcel.writeString(this.mNeighborhoodOverview);
        parcel.writeString(this.mNotes);
        parcel.writeString(this.mSpace);
        parcel.writeString(this.mSummary);
        parcel.writeString(this.mTransit);
    }

    public void readFromParcel(Parcel source) {
        this.mAccess = source.readString();
        this.mAuthorType = source.readString();
        this.mDescription = source.readString();
        this.mHouseRules = source.readString();
        this.mInteraction = source.readString();
        this.mLocale = source.readString();
        this.mLocalizedLanguageName = source.readString();
        this.mName = source.readString();
        this.mNeighborhoodOverview = source.readString();
        this.mNotes = source.readString();
        this.mSpace = source.readString();
        this.mSummary = source.readString();
        this.mTransit = source.readString();
    }
}
