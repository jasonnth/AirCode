package com.airbnb.android.react;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import com.airbnb.android.core.utils.ExternalAppUtils;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import okhttp3.HttpUrl;

public class MapProviderPickerModule extends VersionedReactModuleBase {
    private static final int VERSION = 1;

    MapProviderPickerModule(ReactApplicationContext reactContext) {
        super(reactContext, 1);
    }

    public String getName() {
        return "AirbnbMapProviderPickerModule";
    }

    @ReactMethod
    public void showMap(String address) {
        boolean hasGoogleMapsApp = false;
        String url = "http://maps.google.com/maps?q=";
        try {
            url = url + URLEncoder.encode(address, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Uri uri = Uri.parse(HttpUrl.parse(url).toString());
        Activity activity = getCurrentActivity();
        Intent googleMapsIntent = new Intent("android.intent.action.VIEW", uri).setClassName(ExternalAppUtils.GOOGLE_MAPS_APP_ID, "com.google.android.maps.MapsActivity");
        if (!activity.getPackageManager().queryIntentActivities(googleMapsIntent, 0).isEmpty()) {
            hasGoogleMapsApp = true;
        }
        activity.startActivity(hasGoogleMapsApp ? googleMapsIntent : new Intent("android.intent.action.VIEW", uri));
    }
}
