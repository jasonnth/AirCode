package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.Amenity;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenAmenitiesItem implements Parcelable {
    @JsonProperty("amenity")
    protected Amenity mAmenity;
    @JsonProperty("enabled")
    protected boolean mEnabled;

    protected GenAmenitiesItem(Amenity amenity, boolean enabled) {
        this();
        this.mAmenity = amenity;
        this.mEnabled = enabled;
    }

    protected GenAmenitiesItem() {
    }

    public Amenity getAmenity() {
        return this.mAmenity;
    }

    public boolean isEnabled() {
        return this.mEnabled;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mAmenity, 0);
        parcel.writeBooleanArray(new boolean[]{this.mEnabled});
    }

    public void readFromParcel(Parcel source) {
        this.mAmenity = (Amenity) source.readParcelable(Amenity.class.getClassLoader());
        this.mEnabled = source.createBooleanArray()[0];
    }
}
