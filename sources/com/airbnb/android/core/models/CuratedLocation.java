package com.airbnb.android.core.models;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.airbnb.android.core.models.generated.GenCuratedLocation;
import com.airbnb.android.core.utils.geocoder.AddressComponentType;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CuratedLocation extends GenCuratedLocation {
    public static final Creator<CuratedLocation> CREATOR = new Creator<CuratedLocation>() {
        public CuratedLocation[] newArray(int size) {
            return new CuratedLocation[size];
        }

        public CuratedLocation createFromParcel(Parcel source) {
            CuratedLocation object = new CuratedLocation();
            object.readFromParcel(source);
            return object;
        }
    };

    public boolean matchesQuery(String query) {
        return this.mLocation.toLowerCase().startsWith(query.toLowerCase());
    }

    public String getSearchText() {
        return this.mLocation + (!TextUtils.isEmpty(this.mDescription) ? ", " + this.mDescription : "");
    }

    public String getType(Context context) {
        if (this.mType == null || !this.mType.hasDescription()) {
            return null;
        }
        return context.getString(this.mType.descriptionResource);
    }

    @JsonProperty("type")
    public void setType(String value) {
        this.mType = AddressComponentType.getFromKey(value);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return this.mPlaceId.equals(((GenCuratedLocation) o).getPlaceId());
    }

    public int hashCode() {
        return this.mPlaceId.hashCode();
    }
}
