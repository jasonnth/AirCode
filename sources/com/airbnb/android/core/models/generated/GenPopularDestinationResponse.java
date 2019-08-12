package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.PopularDestinationGroup;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenPopularDestinationResponse implements Parcelable {
    @JsonProperty("hot_destinations")
    protected List<PopularDestinationGroup> mHotDestinations;

    protected GenPopularDestinationResponse(List<PopularDestinationGroup> hotDestinations) {
        this();
        this.mHotDestinations = hotDestinations;
    }

    protected GenPopularDestinationResponse() {
    }

    public List<PopularDestinationGroup> getHotDestinations() {
        return this.mHotDestinations;
    }

    @JsonProperty("hot_destinations")
    public void setHotDestinations(List<PopularDestinationGroup> value) {
        this.mHotDestinations = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeTypedList(this.mHotDestinations);
    }

    public void readFromParcel(Parcel source) {
        this.mHotDestinations = source.createTypedArrayList(PopularDestinationGroup.CREATOR);
    }
}
