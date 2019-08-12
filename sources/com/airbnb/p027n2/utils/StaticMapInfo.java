package com.airbnb.p027n2.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import com.airbnb.n2.R;
import java.util.Locale;
import net.p318sf.scuba.smartcards.ISO7816;
import net.p318sf.scuba.smartcards.ISOFileInfo;
import org.jmrtd.lds.CVCAFile;

/* renamed from: com.airbnb.n2.utils.StaticMapInfo */
public class StaticMapInfo {
    private static final byte[] decodeable = {38, 107, 101, 121, 61, 65, 73, 122, 97, 83, 121, CVCAFile.CAR_TAG, 105, 56, 67, 69, 109, 73, 54, 77, ISOFileInfo.FCI_BYTE, 106, 53, 75, 67, 120, ISOFileInfo.FMD_BYTE, 49, 75, 84, 99, 90, 86, ISO7816.INS_INCREASE, 80, 71, 71, 55, 116, 51, 119, 103, 121, 56, 38};
    private double mLat;
    private double mLng;
    private MapType mMapType;
    private String mStaticMapCenterUrl = "";
    private String mStaticMapMarkerUrl = "";
    public String mStaticMapPathUrl = "";
    private String mStaticMapSizeUrl = "";

    /* renamed from: com.airbnb.n2.utils.StaticMapInfo$MapType */
    public enum MapType {
        GoogleWeb("https://maps.googleapis.com", "/maps/api/staticmap?sensor=false&language=%1$s&region=%2$s&format=jpg&", "center=%1$.6f,%2$.6f&zoom=%3$d&", "center=%1$s&zoom=%2$s&", "size=%1$dx%2$d", "scale=%1$d&", "markers=%1$.6f,%2$.6f&", "markers=color:0x%1$s%%7Clabel:%2$s%%7C%3$.6f,%4$.6f&", "path=weight:2%%7Ccolor:%1$s%%7Cfillcolor:%2$s", "%%7C%1$.6f,%2$.6f"),
        GoogleWebDls("https://maps.googleapis.com", "/maps/api/staticmap?sensor=false&language=%1$s&region=%2$s&format=jpg&", "center=%1$.6f,%2$.6f&zoom=%3$d&", "center=%1$s&zoom=%2$s&", "size=%1$dx%2$d", "scale=%1$d&", "markers=icon:https://abb-assets.s3.amazonaws.com/android/assets/static_map_location_pin.png|%1$.6f,%2$.6f&", "markers=color:0x%1$s%%7Clabel:%2$s%%7C%3$.6f,%4$.6f&", "path=weight:2%%7Ccolor:%1$s%%7Cfillcolor:%2$s", "%%7C%1$.6f,%2$.6f"),
        GoogleWebChina("http://ditu.google.cn", "/maps/api/staticmap?sensor=false&language=%1$s&region=%2$s&format=jpg&", "center=%1$.6f,%2$.6f&zoom=%3$d&", "center=%1$s&zoom=%2$s&", "size=%1$dx%2$d", "scale=%1$d&", "markers=%1$.6f,%2$.6f&", "markers=color:0x%1$s%%7Clabel:%2$s%%7C%3$.6f,%4$.6f&", "path=weight:2%%7Ccolor:%1$s%%7Cfillcolor:%2$s", "%%7C%1$.6f,%2$.6f");
        
        public final String mCenter;
        public final String mCenterLocation;
        public final String mCirclePathPrefix;
        public final String mCirclePathSection;
        public final String mColorMarker;
        public final String mDomain;
        public final String mMarker;
        public final String mScale;
        public final String mSize;
        public final String mUrl;

        private MapType(String domain, String url, String center, String centerLocation, String size, String scale, String marker, String colorMarker, String circlePathPrefix, String circlePathSection) {
            this.mDomain = domain;
            this.mUrl = url;
            this.mCenter = center;
            this.mCenterLocation = centerLocation;
            this.mSize = size;
            this.mScale = scale;
            this.mMarker = marker;
            this.mColorMarker = colorMarker;
            this.mCirclePathPrefix = circlePathPrefix;
            this.mCirclePathSection = circlePathSection;
        }
    }

    public static MapType getStaticMapType(boolean isUserInChina, boolean useDlsMapType) {
        if (isUserInChina) {
            return MapType.GoogleWebChina;
        }
        if (useDlsMapType) {
            return MapType.GoogleWebDls;
        }
        return MapType.GoogleWeb;
    }

    public void setup(boolean isUserInChina, boolean useDlsMapType) {
        this.mMapType = getStaticMapType(isUserInChina, useDlsMapType);
    }

    public String getStaticMapUrl(Context context) {
        Resources res = context.getResources();
        return getStaticMapUrl(res, res.getDisplayMetrics().widthPixels, (int) TypedValue.applyDimension(1, 260.0f, res.getDisplayMetrics()), true);
    }

    public String getStaticMapUrl(Resources res, int width, int height, boolean keyed) {
        int scale = res.getDisplayMetrics().density >= 2.0f ? 2 : 1;
        int width2 = width / scale;
        int height2 = height / scale;
        if (this.mMapType == MapType.GoogleWeb || this.mMapType == MapType.GoogleWebChina) {
            if (width2 > 640) {
                float ratio = 640.0f / ((float) width2);
                width2 = (int) (((float) width2) * ratio);
                height2 = (int) (((float) height2) * ratio);
            }
            if (height2 > 640) {
                float ratio2 = 640.0f / ((float) height2);
                width2 = (int) (((float) width2) * ratio2);
                height2 = (int) (((float) height2) * ratio2);
            }
            height2 += 100;
        }
        this.mStaticMapSizeUrl = String.format(Locale.US, this.mMapType.mSize, new Object[]{Integer.valueOf(width2), Integer.valueOf(height2)});
        String staticMapScaleUrl = String.format(Locale.US, this.mMapType.mScale, new Object[]{Integer.valueOf(scale)});
        Locale locale = Locale.getDefault();
        StringBuilder sb = new StringBuilder().append(this.mMapType.mDomain).append(String.format(Locale.US, this.mMapType.mUrl, new Object[]{locale.getLanguage(), locale.getCountry()}));
        if (keyed && (this.mMapType == MapType.GoogleWeb || this.mMapType == MapType.GoogleWebChina)) {
            sb.append(new String(decodeable));
        }
        sb.append(this.mStaticMapMarkerUrl).append(this.mStaticMapPathUrl).append(this.mStaticMapCenterUrl).append(staticMapScaleUrl).append(this.mStaticMapSizeUrl);
        return sb.toString();
    }

    public MapType getMapType() {
        return this.mMapType;
    }

    public void setMapType(boolean isUserInChina, boolean useDlsMapType) {
        setMapType(getStaticMapType(isUserInChina, useDlsMapType));
    }

    public void setMapType(MapType mapType) {
        this.mMapType = mapType;
    }

    public void centerMap(LatLng latLng, int zoom) {
        centerMap(latLng.lat(), latLng.lng(), zoom);
    }

    public void centerMap(double lat, double lng, int zoom) {
        this.mLat = lat;
        this.mLng = lng;
        this.mStaticMapCenterUrl = String.format(Locale.US, this.mMapType.mCenter, new Object[]{Double.valueOf(this.mLat), Double.valueOf(this.mLng), Integer.valueOf(zoom)});
    }

    public void addMarkerToMap(double latitude, double longitude) {
        addMarkerToMap(LatLng.builder().lat(latitude).lng(longitude).build());
    }

    public void addMarkerToMap(LatLng latLng) {
        this.mStaticMapMarkerUrl += String.format(Locale.US, getMapType().mMarker, new Object[]{Double.valueOf(latLng.lat()), Double.valueOf(latLng.lng())});
    }

    public void addCircleToMap(Context context, LatLng latLng, int circleRadius) {
        Resources resources = context.getResources();
        this.mStaticMapPathUrl = buildUrlPathSection(Integer.toHexString(resources.getColor(R.color.n2_map_circle_border)), Integer.toHexString(resources.getColor(R.color.n2_map_circle)));
        for (int i = 0; i <= 360; i += 5) {
            LatLng circle = LocationUtil.getLocationDistanceFromLocation(latLng, LocationUtil.getXRadius(circleRadius, (float) i), LocationUtil.getYRadius(circleRadius, (float) i));
            this.mStaticMapPathUrl += String.format(Locale.US, this.mMapType.mCirclePathSection, new Object[]{Double.valueOf(circle.lat()), Double.valueOf(circle.lng())});
        }
        this.mStaticMapPathUrl += "&";
    }

    private String buildUrlPathSection(String strokeColor, String fillColor) {
        return String.format(Locale.US, this.mMapType.mCirclePathPrefix, new Object[]{buildColorString(strokeColor), buildColorString(fillColor)});
    }

    private static String buildColorString(String color) {
        return String.format(Locale.US, "0x%1$s%2$s", new Object[]{color.substring(2), color.substring(0, 2)});
    }

    public void clearMapUrls() {
        this.mStaticMapCenterUrl = "";
        this.mStaticMapMarkerUrl = "";
        this.mStaticMapSizeUrl = "";
        this.mStaticMapPathUrl = "";
    }
}
