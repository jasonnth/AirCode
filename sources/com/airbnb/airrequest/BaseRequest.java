package com.airbnb.airrequest;

import com.airbnb.airrequest.AirRequest.Builder;
import com.airbnb.airrequest.AirRequest.RequestType;
import com.airbnb.rxgroups.RequestSubscription;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import p032rx.Observer;
import retrofit2.Part;
import retrofit2.Query;

public abstract class BaseRequest<T> implements AirRequest {
    private static final String API_VERSION_V1 = "v1/";
    private boolean isDoubleResponse;
    private boolean isPrefetch;
    private boolean isSkipCache;
    protected Observer<AirResponse<T>> observer;
    private Object tag;

    public RequestSubscription execute(RequestExecutor executor) {
        return executor.execute(this);
    }

    public BaseRequest<T> skipCache() {
        return skipCache(true);
    }

    public BaseRequest<T> skipCache(boolean skip) {
        this.isSkipCache = skip;
        return this;
    }

    public boolean isAllowedIfMonkey() {
        return getMethod() == RequestMethod.GET;
    }

    public BaseRequest<T> singleResponse() {
        this.isDoubleResponse = false;
        return this;
    }

    public BaseRequest<T> doubleResponse() {
        this.isDoubleResponse = true;
        return this;
    }

    public BaseRequest<T> doubleResponse(boolean isDoubleResponse2) {
        this.isDoubleResponse = isDoubleResponse2;
        return this;
    }

    public boolean isDoubleResponse() {
        return this.isDoubleResponse;
    }

    public boolean isSkipCache() {
        return this.isSkipCache;
    }

    public Type errorResponseType() {
        return ErrorResponse.class;
    }

    public long getCacheTimeoutMs() {
        return 0;
    }

    public long getCacheOnlyTimeoutMs() {
        return 0;
    }

    public RequestMethod getMethod() {
        return RequestMethod.GET;
    }

    public AirResponse<T> transformResponse(AirResponse<T> response) {
        return response;
    }

    public Map<String, String> getHeaders() {
        return Collections.emptyMap();
    }

    public RequestType getRequestType() {
        return RequestType.SIMPLE;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make();
    }

    public Object getBody() {
        return null;
    }

    public String getContentType() {
        return getBody() != null ? "application/json; charset=UTF-8" : "application/x-www-form-urlencoded; charset=UTF-8";
    }

    public List<Part> getParts() {
        return null;
    }

    public QueryStrap getFields() {
        return null;
    }

    public String getPathPrefix() {
        return API_VERSION_V1;
    }

    public BaseRequest<T> withListener(Observer<AirResponse<T>> listener) {
        this.observer = listener;
        return this;
    }

    public boolean isPrefetch() {
        return this.isPrefetch;
    }

    public Observer<AirResponse<T>> observer() {
        return this.observer != null ? this.observer : EmptyObserver.instance();
    }

    public BaseRequest<T> setPrefetch(boolean isPrefetch2) {
        if (getMethod() != RequestMethod.GET) {
            throw new IllegalStateException("Can only prefetch on a GET request.");
        }
        this.isPrefetch = isPrefetch2;
        return this;
    }

    public Builder toBuilder() {
        return new Builder(this);
    }
}
