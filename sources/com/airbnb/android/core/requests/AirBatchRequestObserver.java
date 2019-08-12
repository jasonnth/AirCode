package com.airbnb.android.core.requests;

import android.util.Log;
import com.airbnb.airrequest.AirResponse;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.BatchException;
import com.airbnb.airrequest.ErrorResponse;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.CoreGraph;
import com.airbnb.android.core.data.Converter;
import com.airbnb.android.core.data.ConverterFactory;
import com.airbnb.android.core.requests.base.NetworkErrorLogger;
import com.airbnb.android.core.responses.AirBatchErrorResponse;
import com.airbnb.android.core.responses.AirBatchResponse;
import com.airbnb.android.core.responses.BatchOperation;
import com.google.common.base.Joiner;
import com.google.common.collect.FluentIterable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import okhttp3.Request;
import p032rx.Observer;
import retrofit2.Query;
import retrofit2.Response;

public final class AirBatchRequestObserver implements Observer<AirResponse<AirBatchResponse>> {
    private static final String PACKAGE = AirBatchRequestObserver.class.getPackage().getName();
    private static final String TAG = AirBatchRequestObserver.class.getSimpleName();
    ConverterFactory converterFactory;
    private final Observer<AirResponse<AirBatchResponse>> listener;
    private final Map<BatchOperation, BaseRequestV2<?>> operationsMap;
    private final List<? extends BaseRequestV2<?>> requests;
    private final String sourceStackTrace = buildSourceStackTrace();

    AirBatchRequestObserver(List<? extends BaseRequestV2<?>> requests2, Map<BatchOperation, BaseRequestV2<?>> operationsMap2, Observer<AirResponse<AirBatchResponse>> listener2) {
        this.requests = requests2;
        this.operationsMap = operationsMap2;
        this.listener = listener2;
        ((CoreGraph) CoreApplication.instance().component()).inject(this);
    }

    private String buildSourceStackTrace() {
        return FluentIterable.m1283of(Thread.currentThread().getStackTrace()).filter(AirBatchRequestObserver$$Lambda$1.lambdaFactory$()).filter(AirBatchRequestObserver$$Lambda$2.lambdaFactory$()).limit(5).join(Joiner.m1896on("\n"));
    }

    static /* synthetic */ boolean lambda$buildSourceStackTrace$1(StackTraceElement e) {
        return !e.getClassName().contains(PACKAGE);
    }

    public void onCompleted() {
        for (BaseRequestV2<?> request : this.requests) {
            request.observer().onCompleted();
        }
        if (this.listener != null) {
            this.listener.onCompleted();
        }
    }

    public void onError(Throwable e) {
        NetworkException networkException = (NetworkException) e;
        AirBatchErrorResponse data = (AirBatchErrorResponse) networkException.errorResponse();
        if (!(data == null || data.operations == null)) {
            Converter<?> errorConverter = this.converterFactory.get((Type) ErrorResponse.class);
            for (BatchOperation batchResponse : data.operations) {
                BaseRequestV2<?> singleAirRequest = (BaseRequestV2) this.operationsMap.get(batchResponse);
                if (singleAirRequest != null) {
                    ErrorResponse innerError = (ErrorResponse) errorConverter.fromJson(batchResponse.response());
                    if (innerError != null) {
                        singleAirRequest.observer().onError(new BatchException(singleAirRequest, innerError));
                        Request request = networkException.rawResponse() != null ? networkException.rawResponse().request() : null;
                        List<Query> requestParams = new ArrayList<>(singleAirRequest.getQueryParams());
                        requestParams.add(new Query("path", singleAirRequest.getPath()));
                        NetworkErrorLogger.logErrorToBugSnag(request, this.sourceStackTrace, singleAirRequest.getClass().getSimpleName(), innerError.toMap(), requestParams);
                    } else {
                        batchResponse.convertedResponse = this.converterFactory.get(singleAirRequest.successResponseType()).fromJson(batchResponse.response());
                        singleAirRequest.observer().onNext(new AirResponse(singleAirRequest, Response.success(batchResponse.convertedResponse)));
                        singleAirRequest.observer().onCompleted();
                    }
                }
            }
        }
        if (this.listener != null) {
            this.listener.onError(e);
        }
    }

    public void onNext(AirResponse<AirBatchResponse> airResponse) {
        for (BatchOperation batchResponse : ((AirBatchResponse) airResponse.body()).operations) {
            BaseRequestV2 request = (BaseRequestV2) this.operationsMap.get(batchResponse);
            if (request != null) {
                request.observer().onNext(new AirResponse(request, Response.success(batchResponse.convertedResponse)));
            } else {
                Log.w(TAG, "Can't find a request that matches BatchOperation=" + batchResponse);
            }
        }
        if (this.listener != null) {
            this.listener.onNext(airResponse);
        }
    }

    public <T> T findInnerResponseBodyByRequestType(AirResponse<AirBatchResponse> response, Class<? extends BaseRequestV2<?>> klass) {
        for (BatchOperation batchResponse : ((AirBatchResponse) response.body()).operations) {
            BaseRequestV2 request = (BaseRequestV2) this.operationsMap.get(batchResponse);
            if (request != null && request.getClass().equals(klass)) {
                return batchResponse.convertedResponse;
            }
        }
        return null;
    }
}
