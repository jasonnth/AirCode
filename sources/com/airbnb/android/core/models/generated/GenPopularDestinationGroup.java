package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.PopularDestination;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenPopularDestinationGroup implements Parcelable {
    @JsonProperty("destinations")
    protected List<PopularDestination> mDestinations;
    @JsonProperty("name")
    protected String mName;

    protected GenPopularDestinationGroup(List<PopularDestination> destinations, String name) {
        this();
        this.mDestinations = destinations;
        this.mName = name;
    }

    protected GenPopularDestinationGroup() {
    }

    public List<PopularDestination> getDestinations() {
        return this.mDestinations;
    }

    @JsonProperty("destinations")
    public void setDestinations(List<PopularDestination> value) {
        this.mDestinations = value;
    }

    public String getName() {
        return this.mName;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.mName = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeTypedList(this.mDestinations);
        parcel.writeString(this.mName);
    }

    public void readFromParcel(Parcel source) {
        this.mDestinations = source.createTypedArrayList(PopularDestination.CREATOR);
        this.mName = source.readString();
    }
}
