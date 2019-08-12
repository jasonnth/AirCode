package com.airbnb.android.core.requests;

import android.content.Context;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.utils.Strap;
import com.fasterxml.jackson.databind.JsonNode;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import retrofit2.Query;

@Deprecated
public class PlacesSearchRequest extends ExternalRequest<JsonNode> {
    private static final String PLACES_API_BASE = "https://maps.googleapis.com/";
    private static final String TYPE_AUTOCOMPLETE = "maps/api/place/autocomplete/json";
    private final Strap params;

    public PlacesSearchRequest(Context context, Strap params2) {
        super(PLACES_API_BASE);
        this.params = params2;
    }

    public String getPath() {
        return TYPE_AUTOCOMPLETE;
    }

    public Type successResponseType() {
        return JsonNode.class;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mix((Map<String, String>) this.params);
    }
}
