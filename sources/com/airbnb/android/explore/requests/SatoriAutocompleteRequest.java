package com.airbnb.android.explore.requests;

import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.requests.ExternalRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Locale;
import retrofit2.Query;

public class SatoriAutocompleteRequest extends ExternalRequest<SatoriAutocompleteResponse> {
    private static final String HOST = "https://www.airbnb.com";
    private static final int NUM_RESULTS = 5;
    private final String query;
    private final String userMarket;

    private SatoriAutocompleteRequest(String query2, String userMarket2) {
        super(HOST);
        this.query = query2;
        this.userMarket = userMarket2;
    }

    public static SatoriAutocompleteRequest forSearch(String query2, String userMarket2) {
        return new SatoriAutocompleteRequest(query2, userMarket2);
    }

    public String getPath() {
        return "location_autocomplete";
    }

    public Type successResponseType() {
        return SatoriAutocompleteResponse.class;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(AccountKitGraphConstants.PARAMETER_LOCALE, Locale.getDefault().toString()).mo7895kv("language", Locale.getDefault().getLanguage()).mo7893kv("num_results", 5).mo7895kv("market", this.userMarket != null ? this.userMarket : "").mo7895kv("user_input", this.query);
    }

    public long getCacheTimeoutMs() {
        return 604800000;
    }

    public long getCacheOnlyTimeoutMs() {
        return 3600000;
    }
}
