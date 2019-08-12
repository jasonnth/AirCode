package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequest;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.ProPhotoResponse;
import com.facebook.internal.AnalyticsEvents;
import java.util.Collection;
import retrofit2.Query;

@Deprecated
public class ProPhotoRequest extends BaseRequest<ProPhotoResponse> {
    private final String listingId;
    private final RequestMethod method;
    private final Type requestType;

    public enum Type {
        APPLY("requested"),
        CANCEL(AnalyticsEvents.PARAMETER_SHARE_OUTCOME_CANCELLED);
        
        final String value;

        private Type(String apiName) {
            this.value = apiName;
        }
    }

    public ProPhotoRequest(String listingId2, Type requestType2, BaseRequestListener<ProPhotoResponse> listener) {
        withListener(listener);
        this.listingId = listingId2;
        this.requestType = requestType2;
        this.method = RequestMethod.POST;
    }

    public ProPhotoRequest(String listingId2, BaseRequestListener<ProPhotoResponse> listener) {
        withListener(listener);
        this.listingId = listingId2;
        this.requestType = null;
        this.method = RequestMethod.GET;
    }

    public String getPath() {
        return "listings/" + this.listingId + "/photography";
    }

    public Collection<Query> getQueryParams() {
        QueryStrap strap = QueryStrap.make();
        if (this.requestType != null) {
            strap.mo7895kv("status", this.requestType.value);
        }
        return strap;
    }

    public RequestMethod getMethod() {
        return this.method;
    }

    public java.lang.reflect.Type successResponseType() {
        return ProPhotoResponse.class;
    }
}
