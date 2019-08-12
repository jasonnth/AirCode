package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequest;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.responses.DownloadPhrasesResponse;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class DownloadPhrasesRequest extends BaseRequest<DownloadPhrasesResponse> {
    private final String locale;

    public static DownloadPhrasesRequest forLocale(String locale2) {
        return new DownloadPhrasesRequest(locale2);
    }

    public DownloadPhrasesRequest(String locale2) {
        this.locale = locale2;
    }

    public Type successResponseType() {
        return DownloadPhrasesResponse.class;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv("key", "9f1axjd321k41kdo3114qx9ba").mo7895kv(AccountKitGraphConstants.PARAMETER_LOCALE, this.locale).mo7895kv("bust", String.valueOf(System.currentTimeMillis()));
    }

    public String getPath() {
        return "phrases/android.json";
    }
}
