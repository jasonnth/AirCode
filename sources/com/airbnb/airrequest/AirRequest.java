package com.airbnb.airrequest;

import com.airbnb.rxgroups.RequestSubscription;
import com.facebook.GraphRequest;
import com.facebook.internal.NativeProtocol;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import retrofit2.Part;
import retrofit2.Query;

public interface AirRequest {

    public static class Builder {
        /* access modifiers changed from: private */
        public Object body;
        /* access modifiers changed from: private */
        public long cacheOnlyTimeoutMs = 0;
        /* access modifiers changed from: private */
        public long cacheTimeoutMs = 0;
        /* access modifiers changed from: private */
        public String contentType;
        /* access modifiers changed from: private */
        public Type errorResponseType;
        /* access modifiers changed from: private */
        public final Set<Query> fields = new HashSet();
        /* access modifiers changed from: private */
        public Map<String, String> headers = new HashMap();
        /* access modifiers changed from: private */
        public boolean isDoubleResponse;
        /* access modifiers changed from: private */
        public boolean isSkipCache;
        /* access modifiers changed from: private */
        public RequestMethod method = RequestMethod.GET;
        /* access modifiers changed from: private */
        public List<Query> params = new ArrayList();
        /* access modifiers changed from: private */
        public final List<Part> parts = new ArrayList();
        /* access modifiers changed from: private */
        public String path;
        /* access modifiers changed from: private */
        public String pathPrefix;
        /* access modifiers changed from: private */
        public RequestType requestType = RequestType.SIMPLE;
        /* access modifiers changed from: private */
        public Type successResponseType;

        public Builder() {
        }

        public Builder(AirRequest airRequest) {
            cacheOnlyTimeoutMs(airRequest.getCacheOnlyTimeoutMs()).cacheTimeoutMs(airRequest.getCacheTimeoutMs()).method(airRequest.getMethod()).requestType(airRequest.getRequestType()).headers(airRequest.getHeaders()).pathPrefix(airRequest.getPathPrefix()).params(airRequest.getQueryParams()).fields(airRequest.getFields() != null ? airRequest.getFields() : Collections.emptySet()).parts(airRequest.getParts() != null ? airRequest.getParts() : Collections.emptyList()).doubleResponse(airRequest.isDoubleResponse()).skipCache(airRequest.isSkipCache()).path(airRequest.getPath()).body(airRequest.getBody()).contentType(airRequest.getContentType()).successResponseType(airRequest.successResponseType()).errorResponseType(airRequest.errorResponseType());
        }

        public Builder params(Collection<Query> params2) {
            this.params = new ArrayList((Collection) Utils.checkNotNull(params2, NativeProtocol.WEB_DIALOG_PARAMS));
            return this;
        }

        public Builder addQueryParam(Query param) {
            this.params.add(Utils.checkNotNull(param, "param"));
            return this;
        }

        public Builder addQueryParam(String key, String value) {
            return addQueryParam(new Query(key, value));
        }

        public Builder removeQueryParam(Query param) {
            this.params.remove(param);
            return this;
        }

        public Builder pathPrefix(String pathPrefix2) {
            this.pathPrefix = pathPrefix2;
            return this;
        }

        public Builder path(String path2) {
            this.path = (String) Utils.checkNotNull(path2, "path");
            return this;
        }

        public Builder parts(List<Part> parts2) {
            this.parts.addAll((Collection) Utils.checkNotNull(parts2, "parts"));
            return this;
        }

        public Builder fields(Set<Query> fields2) {
            this.fields.addAll((Collection) Utils.checkNotNull(fields2, GraphRequest.FIELDS_PARAM));
            return this;
        }

        public Builder doubleResponse() {
            return doubleResponse(true);
        }

        public Builder doubleResponse(boolean isDoubleResponse2) {
            this.isDoubleResponse = isDoubleResponse2;
            return this;
        }

        public Builder requestType(RequestType requestType2) {
            this.requestType = (RequestType) Utils.checkNotNull(requestType2, "requestType");
            return this;
        }

        public Builder skipCache() {
            return skipCache(true);
        }

        public Builder skipCache(boolean b) {
            this.isSkipCache = b;
            return this;
        }

        public Builder method(RequestMethod method2) {
            this.method = (RequestMethod) Utils.checkNotNull(method2, "method");
            return this;
        }

        public Builder cacheTimeoutMs(long cacheTimeoutMs2) {
            this.cacheTimeoutMs = cacheTimeoutMs2;
            return this;
        }

        public Builder cacheOnlyTimeoutMs(long cacheOnlyTimeoutMs2) {
            this.cacheOnlyTimeoutMs = cacheOnlyTimeoutMs2;
            return this;
        }

        public Builder addHeader(String name, String value) {
            this.headers.put(name, value);
            return this;
        }

        public Builder removeHeader(String name) {
            this.headers.remove(name);
            return this;
        }

        public Builder headers(Map<String, String> headers2) {
            this.headers = new HashMap((Map) Utils.checkNotNull(headers2, "headers"));
            return this;
        }

        public Builder body(Object body2) {
            this.body = body2;
            return this;
        }

        public Builder successResponseType(Type type) {
            this.successResponseType = (Type) Utils.checkNotNull(type, "type");
            return this;
        }

        public Builder errorResponseType(Type type) {
            this.errorResponseType = (Type) Utils.checkNotNull(type, "type");
            return this;
        }

        public Builder contentType(String contentType2) {
            this.contentType = contentType2;
            return this;
        }

        public <T> BaseRequest<T> build() {
            String missing = "";
            if (this.path == null) {
                missing = missing + " path";
            }
            if (this.errorResponseType == null) {
                missing = missing + " errorResponseType";
            }
            if (this.successResponseType == null) {
                missing = missing + " successResponseType";
            }
            if (missing.isEmpty()) {
                return new BaseRequest<T>() {
                    public Type successResponseType() {
                        return Builder.this.successResponseType;
                    }

                    public String getPath() {
                        return Builder.this.path;
                    }

                    public Map<String, String> getHeaders() {
                        return Builder.this.headers;
                    }

                    public String getContentType() {
                        return Builder.this.contentType;
                    }

                    public Type errorResponseType() {
                        return Builder.this.errorResponseType;
                    }

                    public Object getBody() {
                        return Builder.this.body;
                    }

                    public long getCacheTimeoutMs() {
                        return Builder.this.cacheTimeoutMs;
                    }

                    public long getCacheOnlyTimeoutMs() {
                        return Builder.this.cacheOnlyTimeoutMs;
                    }

                    public RequestMethod getMethod() {
                        return Builder.this.method;
                    }

                    public boolean isSkipCache() {
                        return Builder.this.isSkipCache;
                    }

                    public List<Part> getParts() {
                        return Builder.this.parts;
                    }

                    public boolean isDoubleResponse() {
                        return Builder.this.isDoubleResponse;
                    }

                    public RequestType getRequestType() {
                        return Builder.this.requestType;
                    }

                    public Collection<Query> getQueryParams() {
                        return Builder.this.params;
                    }

                    public String getPathPrefix() {
                        return Builder.this.pathPrefix != null ? Builder.this.pathPrefix : super.getPathPrefix();
                    }

                    public QueryStrap getFields() {
                        return QueryStrap.make().mix((Collection) Builder.this.fields);
                    }
                };
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    public enum RequestType {
        SIMPLE,
        MULTIPART,
        FORM_URL
    }

    Type errorResponseType();

    RequestSubscription execute(RequestExecutor requestExecutor);

    Object getBody();

    long getCacheOnlyTimeoutMs();

    long getCacheTimeoutMs();

    String getContentType();

    QueryStrap getFields();

    Map<String, String> getHeaders();

    RequestMethod getMethod();

    List<Part> getParts();

    String getPath();

    String getPathPrefix();

    Collection<Query> getQueryParams();

    RequestType getRequestType();

    boolean isAllowedIfMonkey();

    boolean isDoubleResponse();

    boolean isPrefetch();

    boolean isSkipCache();

    Type successResponseType();

    Builder toBuilder();
}
