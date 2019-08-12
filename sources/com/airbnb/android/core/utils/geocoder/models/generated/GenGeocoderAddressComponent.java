package com.airbnb.android.core.utils.geocoder.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenGeocoderAddressComponent implements Parcelable {
    @JsonProperty("long_name")
    protected String mLongName;
    @JsonProperty("short_name")
    protected String mShortName;
    @JsonProperty("types")
    protected List<String> mTypes;

    protected GenGeocoderAddressComponent(List<String> types, String longName, String shortName) {
        this();
        this.mTypes = types;
        this.mLongName = longName;
        this.mShortName = shortName;
    }

    protected GenGeocoderAddressComponent() {
    }

    public List<String> getTypes() {
        return this.mTypes;
    }

    @JsonProperty("types")
    public void setTypes(List<String> value) {
        this.mTypes = value;
    }

    public String getLongName() {
        return this.mLongName;
    }

    @JsonProperty("long_name")
    public void setLongName(String value) {
        this.mLongName = value;
    }

    public String getShortName() {
        return this.mShortName;
    }

    @JsonProperty("short_name")
    public void setShortName(String value) {
        this.mShortName = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeStringList(this.mTypes);
        parcel.writeString(this.mLongName);
        parcel.writeString(this.mShortName);
    }

    public void readFromParcel(Parcel source) {
        this.mTypes = source.createStringArrayList();
        this.mLongName = source.readString();
        this.mShortName = source.readString();
    }
}
