package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenIncentive implements Parcelable {
    @JsonProperty("description")
    protected String mDescription;
    @JsonProperty("memory")
    protected int mMemory;
    @JsonProperty("name")
    protected String mName;
    @JsonProperty("title")
    protected String mTitle;

    protected GenIncentive(String name, String description, String title, int memory) {
        this();
        this.mName = name;
        this.mDescription = description;
        this.mTitle = title;
        this.mMemory = memory;
    }

    protected GenIncentive() {
    }

    public String getName() {
        return this.mName;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.mName = value;
    }

    public String getDescription() {
        return this.mDescription;
    }

    @JsonProperty("description")
    public void setDescription(String value) {
        this.mDescription = value;
    }

    public String getTitle() {
        return this.mTitle;
    }

    @JsonProperty("title")
    public void setTitle(String value) {
        this.mTitle = value;
    }

    public int getMemory() {
        return this.mMemory;
    }

    @JsonProperty("memory")
    public void setMemory(int value) {
        this.mMemory = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mName);
        parcel.writeString(this.mDescription);
        parcel.writeString(this.mTitle);
        parcel.writeInt(this.mMemory);
    }

    public void readFromParcel(Parcel source) {
        this.mName = source.readString();
        this.mDescription = source.readString();
        this.mTitle = source.readString();
        this.mMemory = source.readInt();
    }
}
