package com.airbnb.android.core.requests;

import android.text.TextUtils;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.responses.SuperhostResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import p032rx.Observer;
import retrofit2.Query;

public class SuperhostRequest extends BaseRequestV2<SuperhostResponse> {
    final String format;
    private long hostId;

    public SuperhostRequest(BaseRequestListener<SuperhostResponse> listener) {
        withListener((Observer) listener);
        this.format = null;
    }

    private SuperhostRequest(String format2, long hostId2) {
        this.format = format2;
        this.hostId = hostId2;
    }

    public static SuperhostRequest forHostStats(long hostId2) {
        return new SuperhostRequest("default", hostId2);
    }

    public Collection<Query> getQueryParams() {
        QueryStrap strap = QueryStrap.make();
        if (!TextUtils.isEmpty(this.format)) {
            strap.mo7895kv(TimelineRequest.ARG_FORMAT, this.format);
        }
        if (this.hostId > 0) {
            strap.mo7894kv("host_id", this.hostId);
        }
        return strap;
    }

    public String getPath() {
        return "superhosts/" + AirbnbAccountManager.currentUserId();
    }

    public long getCacheTimeoutMs() {
        return 86400000;
    }

    public Type successResponseType() {
        return SuperhostResponse.class;
    }
}
