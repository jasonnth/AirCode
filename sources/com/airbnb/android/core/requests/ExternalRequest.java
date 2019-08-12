package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequest;
import com.airbnb.android.core.HostOverrideInterceptor;
import java.util.Collections;
import java.util.Map;

public abstract class ExternalRequest<T> extends BaseRequest<T> {
    private final String host;

    protected ExternalRequest(String host2) {
        this.host = host2;
    }

    public String getPathPrefix() {
        return "";
    }

    public Map<String, String> getHeaders() {
        return Collections.singletonMap(HostOverrideInterceptor.HOST_OVERRIDE_HEADER, this.host);
    }
}
