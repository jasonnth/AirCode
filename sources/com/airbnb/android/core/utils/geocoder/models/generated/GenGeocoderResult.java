package com.airbnb.android.core.utils.geocoder.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.utils.geocoder.models.GeocoderAddressComponent;
import com.airbnb.android.core.utils.geocoder.models.GeocoderGeometry;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenGeocoderResult implements Parcelable {
    @JsonProperty("address_components")
    protected List<GeocoderAddressComponent> mAddressComponents;
    @JsonProperty("formatted_address")
    protected String mFormattedAddress;
    @JsonProperty("geometry")
    protected GeocoderGeometry mGeometry;
    @JsonProperty("place_id")
    protected String mPlaceId;
    @JsonProperty("types")
    protected List<String> mTypes;

    protected GenGeocoderResult(GeocoderGeometry geometry, List<GeocoderAddressComponent> addressComponents, List<String> types, String placeId, String formattedAddress) {
        this();
        this.mGeometry = geometry;
        this.mAddressComponents = addressComponents;
        this.mTypes = types;
        this.mPlaceId = placeId;
        this.mFormattedAddress = formattedAddress;
    }

    protected GenGeocoderResult() {
    }

    public GeocoderGeometry getGeometry() {
        return this.mGeometry;
    }

    @JsonProperty("geometry")
    public void setGeometry(GeocoderGeometry value) {
        this.mGeometry = value;
    }

    public List<GeocoderAddressComponent> getAddressComponents() {
        return this.mAddressComponents;
    }

    @JsonProperty("address_components")
    public void setAddressComponents(List<GeocoderAddressComponent> value) {
        this.mAddressComponents = value;
    }

    public List<String> getTypes() {
        return this.mTypes;
    }

    @JsonProperty("types")
    public void setTypes(List<String> value) {
        this.mTypes = value;
    }

    public String getPlaceId() {
        return this.mPlaceId;
    }

    @JsonProperty("place_id")
    public void setPlaceId(String value) {
        this.mPlaceId = value;
    }

    public String getFormattedAddress() {
        return this.mFormattedAddress;
    }

    @JsonProperty("formatted_address")
    public void setFormattedAddress(String value) {
        this.mFormattedAddress = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mGeometry, 0);
        parcel.writeTypedList(this.mAddressComponents);
        parcel.writeStringList(this.mTypes);
        parcel.writeString(this.mPlaceId);
        parcel.writeString(this.mFormattedAddress);
    }

    public void readFromParcel(Parcel source) {
        this.mGeometry = (GeocoderGeometry) source.readParcelable(GeocoderGeometry.class.getClassLoader());
        this.mAddressComponents = source.createTypedArrayList(GeocoderAddressComponent.CREATOR);
        this.mTypes = source.createStringArrayList();
        this.mPlaceId = source.readString();
        this.mFormattedAddress = source.readString();
    }
}
