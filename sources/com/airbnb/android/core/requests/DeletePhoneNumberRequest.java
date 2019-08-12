package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.net.ApiRequestHeadersInterceptor;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;
import java.util.Collection;
import p032rx.Observer;
import retrofit2.Query;

public class DeletePhoneNumberRequest extends BaseRequestV2<Object> {
    private final long phoneNumberId;

    public DeletePhoneNumberRequest(long phoneNumberId2, BaseRequestListener<Object> listener) {
        withListener((Observer) listener);
        this.phoneNumberId = phoneNumberId2;
    }

    public String getPath() {
        return "phone_numbers/" + this.phoneNumberId;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, "delete");
    }

    public String getBody() {
        return null;
    }

    public Strap getHeaders() {
        return Strap.make().mix(super.getHeaders()).mo11639kv(ApiRequestHeadersInterceptor.HEADER_METHOD_OVERRIDE, "DELETE");
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Type successResponseType() {
        return Object.class;
    }
}
