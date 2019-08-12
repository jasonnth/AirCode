package com.airbnb.android.core.arguments;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import com.airbnb.android.core.Fragments;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.ReactNativeIntentUtils;

public class ReactNativeFragmentFactory {
    private static final String APP_CITY_HOSTS_MANAGER_AVAILABILITY = "CityHostsManager/Availability";
    private static final String APP_CITY_HOSTS_MANAGER_EXPERIENCES = "CityHostsManager/Experiences";
    private static final String APP_CITY_HOSTS_MANAGER_STATS = "CityHostsManager/Stats";
    private static final String APP_ITINERARY = "Itinerary";

    public static Fragment forModule(String moduleName) {
        return forModule(moduleName, null);
    }

    public static Fragment forModule(String moduleName, Bundle props) {
        return ((FragmentBundleBuilder) FragmentBundler.make(Fragments.reactNative()).putAll(ReactNativeIntentUtils.intentExtras(moduleName, props))).build();
    }

    public static Fragment fragmentForItinerary(Bundle bundle) {
        return forModule(APP_ITINERARY, bundle);
    }

    public static Fragment fragmentForCityHostsManagerAvailability() {
        return forModule(APP_CITY_HOSTS_MANAGER_AVAILABILITY);
    }

    public static Fragment fragmentForCityHostsManagerExperiences() {
        return forModule(APP_CITY_HOSTS_MANAGER_EXPERIENCES);
    }

    public static Fragment fragmentForCityHostsManagerStats() {
        return forModule(APP_CITY_HOSTS_MANAGER_STATS);
    }
}
