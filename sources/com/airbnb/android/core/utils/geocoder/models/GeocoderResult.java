package com.airbnb.android.core.utils.geocoder.models;

import android.content.Context;
import android.location.Address;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.airbnb.android.core.models.AirAddress;
import com.airbnb.android.core.utils.LocationUtil;
import com.airbnb.android.core.utils.geocoder.AddressComponentType;
import com.airbnb.android.core.utils.geocoder.models.generated.GenGeocoderResult;
import com.airbnb.android.utils.AirbnbConstants;
import com.google.android.gms.maps.model.LatLng;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class GeocoderResult extends GenGeocoderResult {
    public static final Creator<GeocoderResult> CREATOR = new Creator<GeocoderResult>() {
        public GeocoderResult[] newArray(int size) {
            return new GeocoderResult[size];
        }

        public GeocoderResult createFromParcel(Parcel source) {
            GeocoderResult object = new GeocoderResult();
            object.readFromParcel(source);
            return object;
        }
    };
    public static final String FORMATTED_ADDRESS = "formatted_address";
    private Map<AddressComponentType, GeocoderAddressComponent> addressComponentMap;
    private final List<AddressComponentType> sublocalityAddressComponentTypes = Lists.newArrayList((E[]) new AddressComponentType[]{AddressComponentType.Sublocality1, AddressComponentType.Sublocality2, AddressComponentType.Sublocality3, AddressComponentType.Sublocality4, AddressComponentType.Sublocality5});

    public Address toAddress() {
        Address address = new Address(Locale.getDefault());
        Bundle bundle = new Bundle();
        bundle.putString(FORMATTED_ADDRESS, getFormattedAddress());
        address.setExtras(bundle);
        LatLng latLng = getLatLng();
        if (latLng != null) {
            address.setLatitude(latLng.latitude);
            address.setLongitude(latLng.longitude);
        }
        address.setSubThoroughfare(getShortName(AddressComponentType.StreetNumber));
        address.setThoroughfare(getShortName(AddressComponentType.Route));
        address.setLocality(getLongName(AddressComponentType.Locality));
        address.setSubLocality(getLongName(AddressComponentType.Sublocality));
        address.setAdminArea(getLongName(AddressComponentType.Admin1));
        address.setCountryCode(getShortName(AddressComponentType.Country));
        address.setCountryName(getLongName(AddressComponentType.Country));
        address.setPostalCode(getShortName(AddressComponentType.PostalCode));
        return address;
    }

    public AirAddress toAirAddress(Context context) {
        return modifyAddressIfNecessary(AirAddress.builder().streetAddressOne(getStreetAddress()).city(getCity()).state(getState(context)).country(getLongName(AddressComponentType.Country)).countryCode(getShortName(AddressComponentType.Country)).postalCode(getShortName(AddressComponentType.PostalCode)).latitude(Double.valueOf(getLatLng().latitude)).longitude(Double.valueOf(getLatLng().longitude)).build());
    }

    private AirAddress modifyAddressIfNecessary(AirAddress address) {
        String countryCode = address.countryCode();
        char c = 65535;
        switch (countryCode.hashCode()) {
            case 2374:
                if (countryCode.equals(AirbnbConstants.COUNTRY_CODE_JAPAN)) {
                    c = 0;
                    break;
                }
                break;
            case 2407:
                if (countryCode.equals(AirbnbConstants.COUNTRY_CODE_KOREA)) {
                    c = 1;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return toJapanAddress(address);
            case 1:
                return toKoreaAddress(address);
            default:
                return address;
        }
    }

    private GeocoderAddressComponent getComponent(AddressComponentType componentType) {
        if (this.addressComponentMap != null) {
            return (GeocoderAddressComponent) this.addressComponentMap.get(componentType);
        }
        this.addressComponentMap = new HashMap();
        for (GeocoderAddressComponent component : this.mAddressComponents) {
            for (String type : component.getTypes()) {
                AddressComponentType typeEnum = AddressComponentType.getFromKey(type);
                if (typeEnum != null) {
                    this.addressComponentMap.put(typeEnum, component);
                }
            }
        }
        return (GeocoderAddressComponent) this.addressComponentMap.get(componentType);
    }

    public LatLng getLatLng() {
        return getGeometry().getLocation().toLatLng();
    }

    public String getShortName(AddressComponentType componentType) {
        GeocoderAddressComponent component = getComponent(componentType);
        if (component == null) {
            return null;
        }
        return component.getShortName();
    }

    public String getLongName(AddressComponentType componentType) {
        GeocoderAddressComponent component = getComponent(componentType);
        if (component == null) {
            return null;
        }
        return component.getLongName();
    }

    private String getStreetAddress() {
        String streetNumber = getShortName(AddressComponentType.StreetNumber);
        String streetName = getShortName(AddressComponentType.Route);
        return streetNumber != null ? (streetNumber + " " + streetName).trim() : streetName;
    }

    private String getCity() {
        String city;
        String locality = getLongName(AddressComponentType.Locality);
        String admin3 = getLongName(AddressComponentType.Admin3);
        String sublocality = getLongName(AddressComponentType.Sublocality);
        if (locality == null) {
            city = admin3;
        } else {
            city = locality;
        }
        if (city == null) {
            return sublocality;
        }
        return city;
    }

    private String getState(Context context) {
        String adminArea = getLongName(AddressComponentType.Admin1);
        if (getShortName(AddressComponentType.Country).equals("US")) {
            return LocationUtil.getStateCodeFromStateName(context, adminArea);
        }
        return adminArea;
    }

    private AirAddress toJapanAddress(AirAddress address) {
        String streetNumber = TextUtils.join("-", getSubLocalities(address.countryCode()));
        String streetName = getLongName(AddressComponentType.Sublocality);
        return address.toBuilder().streetAddressOne((streetNumber + " " + streetName).trim()).city(getLongName(AddressComponentType.Locality)).build();
    }

    private AirAddress toKoreaAddress(AirAddress address) {
        List<String> subLocalities = getSubLocalities(address.countryCode());
        String neighborhood = "";
        ArrayList<String> citySubLocalities = Lists.newArrayList((Iterable<? extends E>) subLocalities);
        if (subLocalities.size() > 1) {
            neighborhood = (String) subLocalities.get(0);
            citySubLocalities = Lists.newArrayList((Iterable<? extends E>) subLocalities.subList(1, subLocalities.size()));
        }
        String admin1 = getLongName(AddressComponentType.Admin1);
        String locality = getLongName(AddressComponentType.Locality);
        String city = "";
        String state = "";
        if (admin1 == null) {
            state = locality;
            if (citySubLocalities.size() == 0) {
                city = "";
            } else {
                city = TextUtils.join(", ", citySubLocalities);
            }
        } else if (subLocalities.size() > 0 || locality != null) {
            citySubLocalities.add(getShortName(AddressComponentType.Locality));
            city = TextUtils.join(", ", citySubLocalities);
        }
        String streetNumber = getShortName(AddressComponentType.Premise);
        String streetName = neighborhood;
        return address.toBuilder().streetAddressOne(streetNumber == null ? streetName : (streetNumber + " " + streetName).trim()).city(city).state(state).build();
    }

    private List<String> getSubLocalities(String countryCode) {
        List<AddressComponentType> componentTypes = this.sublocalityAddressComponentTypes;
        if (countryCode.equals(AirbnbConstants.COUNTRY_CODE_JAPAN)) {
            componentTypes.remove(AddressComponentType.Sublocality1);
        }
        List<String> sublocalityValues = FluentIterable.from((Iterable<E>) componentTypes).transform(GeocoderResult$$Lambda$1.lambdaFactory$(this)).filter(GeocoderResult$$Lambda$2.lambdaFactory$()).toList();
        if (countryCode.equals(AirbnbConstants.COUNTRY_CODE_KOREA)) {
            return Lists.reverse(sublocalityValues);
        }
        return sublocalityValues;
    }

    static /* synthetic */ boolean lambda$getSubLocalities$0(String x) {
        return x != null;
    }
}
