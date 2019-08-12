package com.airbnb.android.core.requests;

import android.content.Context;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.utils.geocoder.models.PlaceDetailsResponse;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import retrofit2.Query;

public class PlaceDetailsRequest extends ExternalRequest<PlaceDetailsResponse> {
    private static final String PARAM_API_KEY = "key";
    private static final String PARAM_LANGUAGE = "language";
    private static final String PARAM_PLACE_ID = "placeid";
    private final String apiKey;
    private final String placeId;

    private PlaceDetailsRequest(Context context, String placeId2) {
        super(CoreApplication.instance(context).component().geocoderBaseUrl().url().toString());
        this.placeId = placeId2;
        this.apiKey = context.getString(C0716R.string.google_api_key);
    }

    public static PlaceDetailsRequest forPlaceId(Context context, String placeId2) {
        return new PlaceDetailsRequest(context, placeId2);
    }

    public Type successResponseType() {
        return PlaceDetailsResponse.class;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mix((Map<String, String>) Strap.make().mo11639kv(PARAM_LANGUAGE, Locale.getDefault().getLanguage()).mo11639kv(PARAM_PLACE_ID, this.placeId).mo11639kv(PARAM_API_KEY, this.apiKey));
    }

    public String getPath() {
        return "maps/api/place/details/json";
    }
}
