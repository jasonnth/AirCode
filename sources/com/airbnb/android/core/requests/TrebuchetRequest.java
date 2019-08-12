package com.airbnb.android.core.requests;

import com.airbnb.airrequest.AirResponse;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.controllers.TrebuchetController;
import com.airbnb.android.core.net.ApiRequestHeadersInterceptor;
import com.airbnb.android.core.react.TrebuchetReactNativeKeys;
import com.airbnb.android.core.responses.TrebuchetResponse;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.android.utils.Strap;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import java.lang.reflect.Type;
import java.util.List;

public class TrebuchetRequest extends BaseRequestV2<TrebuchetResponse> {
    private static final List<String> ALL_KEYS = ImmutableList.builder().addAll((Iterable) TrebuchetReactNativeKeys.KEYS).addAll((Iterable) TrebuchetKeys.KEYS).build();
    private final TrebuchetController controller;
    private final RequestBody requestBody = new RequestBody(ALL_KEYS);

    static class RequestBody {
        @JsonProperty("ids")
        final List<String> trebuchetKeys;

        RequestBody(List<String> trebuchetKeys2) {
            this.trebuchetKeys = trebuchetKeys2;
        }
    }

    public static TrebuchetRequest create(TrebuchetController controller2) {
        return new TrebuchetRequest(controller2);
    }

    private TrebuchetRequest(TrebuchetController controller2) {
        this.controller = controller2;
    }

    public AirResponse<TrebuchetResponse> transformResponse(AirResponse<TrebuchetResponse> response) {
        this.controller.update(((TrebuchetResponse) response.body()).trebuchetsList);
        return response;
    }

    public String getPath() {
        return "trebuchets";
    }

    public Object getBody() {
        return this.requestBody;
    }

    public Strap getHeaders() {
        return Strap.make().mix(super.getHeaders()).mo11639kv(ApiRequestHeadersInterceptor.HEADER_METHOD_OVERRIDE, "GET");
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Type successResponseType() {
        return TrebuchetResponse.class;
    }
}
