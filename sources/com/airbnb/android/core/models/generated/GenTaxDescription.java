package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenTaxDescription implements Parcelable {
    @JsonProperty("name")
    protected String mName;
    @JsonProperty("rule")
    protected String mRule;

    protected GenTaxDescription(String name, String rule) {
        this();
        this.mName = name;
        this.mRule = rule;
    }

    protected GenTaxDescription() {
    }

    public String getName() {
        return this.mName;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.mName = value;
    }

    public String getRule() {
        return this.mRule;
    }

    @JsonProperty("rule")
    public void setRule(String value) {
        this.mRule = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mName);
        parcel.writeString(this.mRule);
    }

    public void readFromParcel(Parcel source) {
        this.mName = source.readString();
        this.mRule = source.readString();
    }
}
