package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.responses.HostReactivationCopyResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import p032rx.Observer;
import retrofit2.Query;

public class HostReactivationCopyRequest extends BaseRequestV2<HostReactivationCopyResponse> {
    public HostReactivationCopyRequest(BaseRequestListener<HostReactivationCopyResponse> listener) {
        withListener((Observer) listener);
    }

    public String getPath() {
        return "host_standards/" + AirbnbAccountManager.currentUserId();
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, "reactivation_flow");
    }

    public Type successResponseType() {
        return HostReactivationCopyResponse.class;
    }
}
