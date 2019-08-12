package com.airbnb.android.core.requests;

import android.content.Context;
import android.text.TextUtils;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.utils.AppLaunchUtils;
import com.airbnb.android.core.utils.geocoder.models.AutocompleteResponse;
import com.airbnb.android.superhero.SuperHeroMessageModel;
import com.airbnb.android.utils.NumberUtils;
import com.google.android.gms.maps.model.LatLng;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Locale;
import retrofit2.Query;

public class AutocompleteRequest extends ExternalRequest<AutocompleteResponse> {
    private static final String TYPE_CITY = "(cities)";
    private static final String TYPE_ESTABLISHMENT = "establishment";
    private static final String TYPE_GEOCODE = "geocode";
    private final String apiKey;
    private final AutoCompleteServerConfig config;
    private final String countryCode;
    private final LatLng latLng;
    private final String query;
    private final String[] types;

    private enum AutoCompleteServerConfig {
        Google("https://maps.googleapis.com/", "maps/api/place/autocomplete/json"),
        Airbnb("https://www.airbnbchina.cn", "location_services/autocomplete");
        
        final String host;
        final String path;

        private AutoCompleteServerConfig(String host2, String path2) {
            this.host = host2;
            this.path = path2;
        }
    }

    private AutocompleteRequest(AutoCompleteServerConfig config2, String query2, LatLng latLng2, String countryCode2, String[] types2, Context context) {
        super(config2.host);
        this.config = config2;
        this.query = query2;
        this.latLng = latLng2;
        this.countryCode = countryCode2;
        this.types = types2;
        this.apiKey = context.getString(C0716R.string.google_api_key);
    }

    public static AutocompleteRequest forGeocode(String query2, Context context) {
        AutoCompleteServerConfig config2 = AutoCompleteServerConfig.Google;
        if (AppLaunchUtils.isUserInChina()) {
            config2 = AutoCompleteServerConfig.Airbnb;
        }
        return new AutocompleteRequest(config2, query2, null, null, new String[]{TYPE_GEOCODE, TYPE_ESTABLISHMENT}, context);
    }

    public static AutocompleteRequest forCountryCode(String query2, String countryCode2, Context context) {
        return new AutocompleteRequest(AutoCompleteServerConfig.Google, query2, null, countryCode2, new String[]{TYPE_GEOCODE}, context);
    }

    public static AutocompleteRequest forCity(String query2, String countryCode2, Context context) {
        return new AutocompleteRequest(AutoCompleteServerConfig.Google, query2, null, countryCode2, new String[]{TYPE_CITY}, context);
    }

    public String getPath() {
        return this.config.path;
    }

    public Type successResponseType() {
        return AutocompleteResponse.class;
    }

    public Collection<Query> getQueryParams() {
        String str;
        QueryStrap kv = QueryStrap.make().mo7895kv("language", Locale.getDefault().getLanguage()).mo7895kv("input", this.query);
        String str2 = "location";
        if (this.latLng == null) {
            str = "0,0";
        } else {
            str = NumberUtils.formatLatLong(this.latLng.latitude) + "," + NumberUtils.formatLatLong(this.latLng.longitude);
        }
        QueryStrap strap = kv.mo7895kv(str2, str);
        if (this.types.length == 1) {
            strap.mo7895kv("types", this.types[0]);
        }
        for (String type : this.types) {
            if (TYPE_GEOCODE.equals(type)) {
                strap.mo7893kv(SuperHeroMessageModel.RADIUS, 20000000);
            }
        }
        if (!TextUtils.isEmpty(this.countryCode)) {
            strap.mo7895kv("components", "country:" + this.countryCode.toLowerCase());
        }
        if (this.config == AutoCompleteServerConfig.Google) {
            strap.mo7895kv("key", this.apiKey);
        }
        return strap;
    }

    public long getCacheTimeoutMs() {
        return 604800000;
    }

    public long getCacheOnlyTimeoutMs() {
        return 3600000;
    }
}
