package com.jumio.core.util;

import android.content.Context;
import android.graphics.BitmapFactory.Options;
import com.jumio.commons.utils.ImageProvider;
import com.jumio.commons.utils.ImageProvider.FlagReceivedInterface;

public class Resource {
    public static final String SERVER_BASE_URL = "http://mobile-sdk-resources.jumio.com/android/";

    public static int getID(Context c, String name, String defType) {
        try {
            return c.getResources().getIdentifier(name, defType, c.getPackageName());
        } catch (Exception e) {
            return 0;
        }
    }

    public static boolean checkClass(String className, ClassLoader classLoader) {
        try {
            Class.forName(className, false, classLoader);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void getCountryFlag(String countryCode3, Options factoryOptions, FlagReceivedInterface flagReceivedInterface) {
        ImageProvider.getCountryFlagFromServerAsync("http://mobile-sdk-resources.jumio.com/android/", countryCode3, factoryOptions, flagReceivedInterface);
    }
}
