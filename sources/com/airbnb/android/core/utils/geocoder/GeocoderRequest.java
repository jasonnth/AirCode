package com.airbnb.android.core.utils.geocoder;

import android.content.Context;
import android.location.Location;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.requests.ExternalRequest;
import com.airbnb.android.core.utils.geocoder.models.GeocoderResponse;
import com.airbnb.android.utils.NumberUtils;
import com.airbnb.android.utils.Strap;
import com.google.android.gms.maps.model.LatLng;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import retrofit2.Query;

public class GeocoderRequest extends ExternalRequest<GeocoderResponse> {
    private final Strap params;

    private GeocoderRequest(Context context, Strap params2) {
        super(CoreApplication.instance(context).component().geocoderBaseUrl().url().toString());
        this.params = params2;
    }

    public static GeocoderRequest getFromLocationName(Context context, String query) {
        return new GeocoderRequest(context, paramsForLocationName(query));
    }

    private static Strap paramsForLocationName(String query) {
        return baseParams().mo11639kv("address", query);
    }

    public static GeocoderRequest getFromLocation(Context context, LatLng location) {
        return new GeocoderRequest(context, paramsForLocation(location));
    }

    public static GeocoderRequest getFromLocation(Context context, Location location) {
        return new GeocoderRequest(context, paramsForLocation(new LatLng(location.getLatitude(), location.getLongitude())));
    }

    private static Strap paramsForLocation(LatLng location) {
        return baseParams().mo11639kv("latlng", NumberUtils.formatLatLong(location.latitude) + "," + NumberUtils.formatLatLong(location.longitude));
    }

    private static Strap baseParams() {
        return new Strap().mo11639kv("language", Locale.getDefault().getLanguage()).mo11639kv("sensor", String.valueOf(false));
    }

    public String getPath() {
        return "maps/api/geocode/json";
    }

    public Type successResponseType() {
        return GeocoderResponse.class;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mix((Map<String, String>) this.params);
    }
}
