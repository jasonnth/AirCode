package com.airbnb.android.core.utils.geocoder;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.C0716R;
import com.braintreepayments.api.models.PostalAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum AddressComponentType implements Parcelable {
    TransitStation("transit_station", C0716R.string.transit_station),
    Place("establishment", C0716R.string.place),
    StreetNumber("street_number", 0),
    StreetAddress("street_address", C0716R.string.address),
    Route("route", C0716R.string.street),
    Intersection("intersection", 0),
    Premise("premise", C0716R.string.premise),
    Neighborhood("neighborhood", C0716R.string.neighborhood),
    Sublocality5("sublocality_level_5", C0716R.string.district),
    Sublocality4("sublocality_level_4", C0716R.string.district),
    Sublocality3("sublocality_level_3", C0716R.string.district),
    Sublocality2("sublocality_level_2", C0716R.string.district),
    Sublocality1("sublocality_level_1", C0716R.string.district),
    Sublocality("sublocality", C0716R.string.district),
    PostalCode(PostalAddress.POSTAL_CODE_UNDERSCORE_KEY, 0),
    Locality("locality", C0716R.string.city),
    Area("colloquial_area", C0716R.string.region),
    Admin5("administrative_area_level_5", C0716R.string.region),
    Admin4("administrative_area_level_4", C0716R.string.region),
    Admin3("administrative_area_level_3", C0716R.string.region),
    Admin2("administrative_area_level_2", C0716R.string.region),
    Admin1("administrative_area_level_1", C0716R.string.region),
    Country("country", C0716R.string.country);
    
    public static final Creator<AddressComponentType> CREATOR = null;
    private static final Map<String, AddressComponentType> keyMap = null;
    public final int descriptionResource;
    public final String key;

    static {
        int i;
        AddressComponentType[] values;
        keyMap = new HashMap();
        for (AddressComponentType component : values()) {
            keyMap.put(component.key, component);
        }
        CREATOR = new Creator<AddressComponentType>() {
            public AddressComponentType createFromParcel(Parcel source) {
                return AddressComponentType.values()[source.readInt()];
            }

            public AddressComponentType[] newArray(int size) {
                return new AddressComponentType[size];
            }
        };
    }

    private AddressComponentType(String key2, int descriptionResource2) {
        this.key = key2;
        this.descriptionResource = descriptionResource2;
    }

    public static AddressComponentType getFromKey(String key2) {
        return (AddressComponentType) keyMap.get(key2);
    }

    public static List<AddressComponentType> getFromKeys(List<String> keys) {
        List<AddressComponentType> types = new ArrayList<>();
        for (String key2 : keys) {
            types.add(getFromKey(key2));
        }
        return types;
    }

    public boolean hasDescription() {
        return this.descriptionResource != 0;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ordinal());
    }
}
