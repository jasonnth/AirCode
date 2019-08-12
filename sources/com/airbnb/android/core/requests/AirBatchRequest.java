package com.airbnb.android.core.requests;

import android.text.TextUtils;
import com.airbnb.airrequest.AirResponse;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.CoreGraph;
import com.airbnb.android.core.data.Converter;
import com.airbnb.android.core.data.ConverterFactory;
import com.airbnb.android.core.responses.AirBatchErrorResponse;
import com.airbnb.android.core.responses.AirBatchResponse;
import com.airbnb.android.core.responses.BatchOperation;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.p027n2.utils.TextUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.FluentIterable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import p032rx.Observer;
import retrofit2.Query;
import retrofit2.Response;

public class AirBatchRequest extends BaseRequestV2<AirBatchResponse> {
    ConverterFactory converterFactory;
    private final boolean isTransactional;
    private final AirBatchRequestObserver observer;
    private final Map<BatchOperation, BaseRequestV2<?>> operationsMap;
    private final List<? extends BaseRequestV2<?>> requests;

    static class BatchRequestBody {
        @JsonProperty("_transaction")
        boolean isTransactional;
        @JsonProperty("operations")
        List<BatchOperation> operations = new ArrayList();

        BatchRequestBody(boolean isTransactional2) {
            this.isTransactional = isTransactional2;
        }
    }

    public AirBatchRequest(List<? extends BaseRequestV2<?>> requests2, NonResubscribableRequestListener<AirBatchResponse> listener) {
        this(requests2, false, listener);
    }

    public AirBatchRequest(List<? extends BaseRequestV2<?>> requests2, boolean transactional, NonResubscribableRequestListener<AirBatchResponse> listener) {
        this.operationsMap = new LinkedHashMap();
        withListener((Observer) listener);
        ((CoreGraph) CoreApplication.instance().component()).inject(this);
        this.requests = requests2;
        this.isTransactional = transactional;
        this.observer = new AirBatchRequestObserver(requests2, this.operationsMap, listener);
    }

    AirBatchRequest(List<? extends BaseRequestV2<?>> requests2, boolean transactional, Observer<AirResponse<AirBatchResponse>> listener) {
        this.operationsMap = new LinkedHashMap();
        withListener((Observer) listener);
        ((CoreGraph) CoreApplication.instance().component()).inject(this);
        this.requests = requests2;
        this.isTransactional = transactional;
        this.observer = new AirBatchRequestObserver(requests2, this.operationsMap, listener);
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public String getPath() {
        return "batch/";
    }

    public boolean isAllowedIfMonkey() {
        return FluentIterable.from((Iterable<E>) this.requests).allMatch(AirBatchRequest$$Lambda$1.lambdaFactory$());
    }

    public String getBody() {
        BatchRequestBody requestBody = new BatchRequestBody(this.isTransactional);
        Converter<BatchRequestBody> converter = this.converterFactory.get((Type) BatchRequestBody.class);
        for (BaseRequestV2<?> request : this.requests) {
            BatchOperation batchOperation = new BatchOperation(request.getMethod(), request.getPath(), request.getBody(), QueryStrap.make().mix(request.getQueryParams()));
            this.operationsMap.put(batchOperation, request);
            requestBody.operations.add(batchOperation);
        }
        return new String(converter.toJson(requestBody));
    }

    public Collection<Query> getQueryParams() {
        Collection<Query> queryParams = QueryStrap.make();
        if (!BuildHelper.isDevelopmentBuild()) {
            return queryParams;
        }
        return QueryStrap.make().mo7895kv("debug_identifier", TextUtil.join(FluentIterable.from((Iterable<E>) this.requests).transform(AirBatchRequest$$Lambda$2.lambdaFactory$()).toList())).mix(queryParams);
    }

    public AirResponse<AirBatchResponse> transformResponse(AirResponse<AirBatchResponse> response) {
        for (BatchOperation batchResponse : ((AirBatchResponse) response.body()).operations) {
            BaseRequestV2 request = (BaseRequestV2) this.operationsMap.get(batchResponse);
            if (request != null) {
                batchResponse.convertedResponse = request.transformResponse(new AirResponse(this, Response.success(this.converterFactory.get(request.successResponseType()).fromJson(batchResponse.response()), response.raw()))).body();
            }
        }
        return response;
    }

    public Type errorResponseType() {
        return AirBatchErrorResponse.class;
    }

    public AirBatchRequestObserver observer() {
        return this.observer;
    }

    public String toString() {
        List<String> tags = new ArrayList<>(this.requests.size() + 2);
        for (BaseRequestV2<?> request : this.requests) {
            tags.add(request.getClass().getSimpleName());
        }
        return "AirBatchRequest{" + TextUtils.join(",", tags) + "}";
    }

    public String getRequestsString() {
        String[] classNames = new String[this.requests.size()];
        for (int i = 0; i < this.requests.size(); i++) {
            classNames[i] = ((BaseRequestV2) this.requests.get(i)).getClass().getSimpleName();
        }
        Arrays.sort(classNames);
        return Arrays.toString(classNames);
    }

    public Type successResponseType() {
        return AirBatchResponse.class;
    }

    public boolean hasRequest(Class<? extends BaseRequestV2<?>> klass) {
        return FluentIterable.from((Iterable<E>) this.requests).anyMatch(AirBatchRequest$$Lambda$3.lambdaFactory$(klass));
    }

    public BaseRequestV2<?> findRequestByClass(Class<? extends BaseRequestV2<?>> klass) {
        return (BaseRequestV2) FluentIterable.from((Iterable<E>) this.requests).firstMatch(AirBatchRequest$$Lambda$4.lambdaFactory$(klass)).get();
    }
}
