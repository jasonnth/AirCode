package com.airbnb.android.core.utils;

import android.content.Context;
import android.text.TextUtils;
import com.airbnb.android.core.models.AirAddress;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.utils.LocaleUtil;
import java.util.Locale;

public class AirAddressUtil {
    public static AirAddress fromListing(Listing listing) {
        return AirAddress.builder().country(SanitizeUtils.emptyIfNull(listing.getCountry())).countryCode(SanitizeUtils.emptyIfNull(listing.getCountryCode())).streetAddressOne(SanitizeUtils.emptyIfNull(listing.getStreetAddress())).streetAddressTwo(SanitizeUtils.emptyIfNull(listing.getApartment())).city(SanitizeUtils.emptyIfNull(listing.getCity())).state(SanitizeUtils.emptyIfNull(listing.getState())).postalCode(SanitizeUtils.emptyIfNull(listing.getZipCode())).latitude(Double.valueOf(listing.getLatitude())).longitude(Double.valueOf(listing.getLongitude())).build();
    }

    public static AirAddress fromListingAndLocale(Listing listing, Context context) {
        AirAddress listingAddress = fromListing(listing);
        if (!TextUtils.isEmpty(listing.getCountryCode())) {
            return listingAddress;
        }
        Locale deviceLocale = LocaleUtil.getCurrentDeviceLocale(context);
        return listingAddress.toBuilder().country(deviceLocale.getDisplayCountry()).countryCode(deviceLocale.getCountry()).build();
    }

    public static Listing setListingAddress(Listing listing, AirAddress address) {
        listing.setStreetAddress(address.streetAddressOne());
        listing.setApartment(address.streetAddressTwo());
        listing.setCity(address.city());
        listing.setState(address.state());
        listing.setZipCode(address.postalCode());
        listing.setCountry(address.country());
        listing.setCountryCode(address.countryCode());
        listing.setLatitude(address.latitude().doubleValue());
        listing.setLongitude(address.longitude().doubleValue());
        return listing;
    }
}
