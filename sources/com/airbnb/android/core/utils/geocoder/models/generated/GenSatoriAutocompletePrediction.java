package com.airbnb.android.core.utils.geocoder.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.utils.geocoder.models.AutocompleteTerm;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenSatoriAutocompletePrediction implements Parcelable {
    @JsonProperty("location_name")
    protected String mDescription;
    @JsonProperty("google_place_id")
    protected String mPlaceId;
    @JsonProperty("terms")
    protected List<AutocompleteTerm> mTerms;
    @JsonProperty("types")
    protected List<String> mTypes;

    protected GenSatoriAutocompletePrediction(List<AutocompleteTerm> terms, List<String> types, String description, String placeId) {
        this();
        this.mTerms = terms;
        this.mTypes = types;
        this.mDescription = description;
        this.mPlaceId = placeId;
    }

    protected GenSatoriAutocompletePrediction() {
    }

    public List<AutocompleteTerm> getTerms() {
        return this.mTerms;
    }

    @JsonProperty("terms")
    public void setTerms(List<AutocompleteTerm> value) {
        this.mTerms = value;
    }

    public List<String> getTypes() {
        return this.mTypes;
    }

    @JsonProperty("types")
    public void setTypes(List<String> value) {
        this.mTypes = value;
    }

    public String getDescription() {
        return this.mDescription;
    }

    @JsonProperty("location_name")
    public void setDescription(String value) {
        this.mDescription = value;
    }

    public String getPlaceId() {
        return this.mPlaceId;
    }

    @JsonProperty("google_place_id")
    public void setPlaceId(String value) {
        this.mPlaceId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeTypedList(this.mTerms);
        parcel.writeStringList(this.mTypes);
        parcel.writeString(this.mDescription);
        parcel.writeString(this.mPlaceId);
    }

    public void readFromParcel(Parcel source) {
        this.mTerms = source.createTypedArrayList(AutocompleteTerm.CREATOR);
        this.mTypes = source.createStringArrayList();
        this.mDescription = source.readString();
        this.mPlaceId = source.readString();
    }
}
