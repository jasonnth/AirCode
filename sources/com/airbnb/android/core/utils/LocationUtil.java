package com.airbnb.android.core.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Address;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.p000v4.content.ContextCompat;
import android.text.TextUtils;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.models.AirAddress;
import com.airbnb.android.core.models.NameCodeItem;
import com.airbnb.android.core.models.SearchGeography;
import com.airbnb.android.utils.AirbnbConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public final class LocationUtil {
    private static final int EARTH_RADIUS_METERS = 6378100;

    private LocationUtil() {
    }

    public static double getMetersBetweenPoints(double lat1, double lng1, double lat2, double lng2) {
        double deltaLat = Math.toRadians(lat2 - lat1);
        double deltaLng = Math.toRadians(lng2 - lng1);
        double a = (Math.sin(deltaLat / 2.0d) * Math.sin(deltaLat / 2.0d)) + (Math.sin(deltaLng / 2.0d) * Math.sin(deltaLng / 2.0d) * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)));
        return 6378100.0d * 2.0d * Math.atan2(Math.sqrt(a), Math.sqrt(1.0d - a));
    }

    @SuppressLint({"MissingPermission"})
    public static Location getLastKnownLocation(Context context) {
        if (!hasLocationPermission(context)) {
            return null;
        }
        try {
            LocationManager locationManager = (LocationManager) context.getSystemService("location");
            Location bestLocation = null;
            for (String provider : locationManager.getProviders(true)) {
                Location l = locationManager.getLastKnownLocation(provider);
                if (l != null && (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy())) {
                    bestLocation = l;
                }
            }
            return bestLocation;
        } catch (RuntimeException e) {
            return null;
        }
    }

    public static boolean hasLocationPermission(Context context) {
        return (hasPermission(context, "android.permission.ACCESS_COARSE_LOCATION") || hasPermission(context, "android.permission.ACCESS_FINE_LOCATION")) && !CoreApplication.instance(context).isTestApplication();
    }

    private static boolean hasPermission(Context context, String permission) {
        try {
            return ContextCompat.checkSelfPermission(context, permission) == 0;
        } catch (RuntimeException e) {
            if (Build.MANUFACTURER.equals("Lenovo")) {
                return false;
            }
            BugsnagWrapper.notify((Throwable) new RuntimeException("Calling checkSelfPermission failed, manufacture: " + Build.MANUFACTURER, e));
            return false;
        }
    }

    public static boolean canShowDistanceTo(SearchGeography geography) {
        String precision = geography.getPrecision();
        if (TextUtils.isEmpty(precision)) {
            return false;
        }
        if (geography.isPreciseLocation() || precision.equals("zip") || precision.equals("zip+4")) {
            return true;
        }
        return false;
    }

    public static String buildLocalityString(Address address) {
        ArrayList<String> strings = new ArrayList<>();
        if (!TextUtils.isEmpty(address.getLocality())) {
            strings.add(address.getLocality());
        }
        if (!TextUtils.isEmpty(address.getAdminArea())) {
            strings.add(address.getAdminArea());
        }
        if (!TextUtils.isEmpty(address.getCountryName())) {
            strings.add(address.getCountryCode());
        }
        return TextUtils.join(", ", strings);
    }

    public static boolean isLanguageBrazilianPortuguese() {
        return Locale.getDefault().equals(new Locale("pt", AirbnbConstants.COUNTRY_CODE_BRAZIL));
    }

    public static boolean isUserPreferredCountryChina() {
        return AirbnbConstants.COUNTRY_CODE_CHINA.equalsIgnoreCase(Locale.getDefault().getCountry());
    }

    public static List<NameCodeItem> getAllStates(Context context) {
        String[] states;
        List<NameCodeItem> allStates = new ArrayList<>();
        for (String stateItem : context.getResources().getStringArray(C0716R.array.states_csv)) {
            NameCodeItem nameCodeItem = new NameCodeItem();
            String[] state = stateItem.split(",");
            nameCodeItem.setCode(state[1]);
            nameCodeItem.setName(state[0]);
            allStates.add(nameCodeItem);
        }
        Collections.sort(allStates, LocationUtil$$Lambda$1.lambdaFactory$());
        return allStates;
    }

    public static String getStateCodeFromStateName(Context context, String stateName) {
        for (String state : context.getResources().getStringArray(C0716R.array.states_csv)) {
            String[] stateItem = state.split(",");
            if (stateItem[0].equals(stateName)) {
                return stateItem[1];
            }
        }
        return null;
    }

    public static String getStateNameFromStateCode(Context context, String stateCode) {
        for (String state : context.getResources().getStringArray(C0716R.array.states_csv)) {
            String[] stateItem = state.split(",");
            if (stateItem[1].equals(stateCode)) {
                return stateItem[0];
            }
        }
        return null;
    }

    public static String formatAddress(String streetAddress, String apartment, String city, String state, String zipCode, String country) {
        List<String> address = new ArrayList<>();
        if (!TextUtils.isEmpty(streetAddress)) {
            address.add(streetAddress);
        }
        if (!TextUtils.isEmpty(apartment)) {
            address.add(apartment);
        }
        if (!TextUtils.isEmpty(city)) {
            address.add(city);
        }
        if (!TextUtils.isEmpty(state)) {
            if (!TextUtils.isEmpty(zipCode)) {
                address.add(state + " " + zipCode);
            } else {
                address.add(state);
            }
        }
        if (!TextUtils.isEmpty(country)) {
            address.add(country);
        }
        return TextUtils.join(", ", address);
    }

    public static String formatAddress(AirAddress address) {
        return formatAddress(address.streetAddressOne(), address.streetAddressTwo(), address.city(), address.state(), address.postalCode(), address.countryCode());
    }
}
