package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenSelectListingMetadata implements Parcelable {
    @JsonProperty("select_amenities_ids")
    protected List<Integer> mSelectAmenitiesIds;

    protected GenSelectListingMetadata(List<Integer> selectAmenitiesIds) {
        this();
        this.mSelectAmenitiesIds = selectAmenitiesIds;
    }

    protected GenSelectListingMetadata() {
    }

    public List<Integer> getSelectAmenitiesIds() {
        return this.mSelectAmenitiesIds;
    }

    @JsonProperty("select_amenities_ids")
    public void setSelectAmenitiesIds(List<Integer> value) {
        this.mSelectAmenitiesIds = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeValue(this.mSelectAmenitiesIds);
    }

    public void readFromParcel(Parcel source) {
        this.mSelectAmenitiesIds = (List) source.readValue(null);
    }
}
