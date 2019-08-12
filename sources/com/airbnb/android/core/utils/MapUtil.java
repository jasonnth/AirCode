package com.airbnb.android.core.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.airbnb.android.core.models.Listing;
import com.google.android.gms.maps.model.LatLng;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MapUtil {
    private static final int DEFAULT_ZOOM = 14;

    public static Intent getMapIntent(Context context, String url) {
        boolean hasGoogleMapsApp = false;
        Uri uri = Uri.parse(url);
        Intent googleMapsIntent = new Intent("android.intent.action.VIEW", uri).setClassName(ExternalAppUtils.GOOGLE_MAPS_APP_ID, "com.google.android.maps.MapsActivity");
        if (!context.getPackageManager().queryIntentActivities(googleMapsIntent, 0).isEmpty()) {
            hasGoogleMapsApp = true;
        }
        return hasGoogleMapsApp ? googleMapsIntent : new Intent("android.intent.action.VIEW", uri);
    }

    public static Intent intentFor(Context context, double lat, double lng, String query) {
        return intentFor(context, lat, lng, 14, query);
    }

    public static Intent intentFor(Context context, double lat, double lng, int zoom, String query) {
        if (!(!context.getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse("geo:")), 0).isEmpty())) {
            String url = "http://maps.google.com/maps?q=";
            if (!TextUtils.isEmpty(query)) {
                try {
                    url = (url + URLEncoder.encode(query, "utf-8")) + "&";
                } catch (UnsupportedEncodingException e) {
                }
            }
            String url2 = url + "loc:" + lat + "+" + lng;
            if (zoom >= 0) {
                url2 = url2 + "&z=" + zoom;
            }
            return getMapIntent(context, url2);
        }
        StringBuilder sb = new StringBuilder("geo:").append(lat).append(",").append(lng).append("?");
        if (!TextUtils.isEmpty(query)) {
            sb.append("q=").append(Uri.encode(query));
        } else if (!(lat == 0.0d && lng == 0.0d)) {
            sb.append("q=loc:").append(lat).append(",").append(lng);
        }
        if (zoom >= 0) {
            sb.append("&z=").append(zoom);
        }
        return new Intent("android.intent.action.VIEW", Uri.parse(sb.toString()));
    }

    public static LatLng getOffsetLatLng(Listing listing, Double offset) {
        return new LatLng(listing.getLatitude() + (offset == null ? -0.01d : offset.doubleValue()), listing.getLongitude());
    }

    public static LatLng getOffsetLatLng(Listing listing) {
        return getOffsetLatLng(listing, null);
    }

    private MapUtil() {
    }
}
