package com.airbnb.android.core.requests;

import com.airbnb.airrequest.AirResponse;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.responses.MarioExperimentResponse;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class MarioExperimentRequest extends BaseRequestV2<MarioExperimentResponse> {
    private static final String ERF_CLIENT_ANDROID = "android";

    public static MarioExperimentRequest newRequest() {
        return new MarioExperimentRequest();
    }

    public Type successResponseType() {
        return MarioExperimentResponse.class;
    }

    public String getPath() {
        return "mario_experiments";
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv("client", "android");
    }

    public long getCacheTimeoutMs() {
        return 604800000;
    }

    public long getCacheOnlyTimeoutMs() {
        return 3600000;
    }

    public AirResponse<MarioExperimentResponse> transformResponse(AirResponse<MarioExperimentResponse> response) {
        CoreApplication.instance().component().resourceManager().storeMario(((MarioExperimentResponse) response.body()).marios);
        return response;
    }
}
