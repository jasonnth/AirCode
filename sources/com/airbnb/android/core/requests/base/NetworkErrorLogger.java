package com.airbnb.android.core.requests.base;

import com.airbnb.airrequest.AirRequest;
import com.airbnb.airrequest.ErrorResponse;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.bugsnag.MetaDataWrapper;
import com.airbnb.android.core.requests.AirBatchRequest;
import com.airbnb.android.utils.Strap;
import com.bugsnag.android.Severity;
import com.google.common.base.Joiner;
import com.google.common.collect.FluentIterable;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Query;

public class NetworkErrorLogger {
    private static final String BATCH_REQUESTS = "batch_requests";
    private static final String KEY_METHOD = "method";
    private static final String KEY_QUERY_PARAMS = "query_params";
    static final String KEY_SOURCE_STACKTRACE = "source";
    private static final String KEY_URL = "url";

    static void logErrorToBugSnag(NetworkException error, AirRequest airRequest, String sourceStackTrace) {
        Response response = error.rawResponse();
        if (response != null && response.request() != null) {
            Request request = response.request();
            Object errorBody = error.errorResponse();
            if (errorBody != null && (errorBody instanceof ErrorResponse)) {
                Strap otherParams = Strap.make().mix(((ErrorResponse) errorBody).toMap());
                if (airRequest instanceof AirBatchRequest) {
                    otherParams.mo11639kv(BATCH_REQUESTS, ((AirBatchRequest) airRequest).getRequestsString());
                }
                logErrorToBugSnag(request, sourceStackTrace, airRequest.getClass().getSimpleName(), otherParams, null);
            }
        }
    }

    public static void logErrorToBugSnag(Request request, String sourceStackTrace, String requestClass, Map<String, String> otherParams, Collection<Query> queryParams) {
        Strap strap = Strap.make().mo11639kv("source", sourceStackTrace).mix(otherParams);
        if (request != null) {
            strap.mo11639kv("url", request.url().toString()).mo11639kv(KEY_METHOD, request.method());
        }
        if (queryParams != null) {
            strap.mo11639kv(KEY_QUERY_PARAMS, FluentIterable.from((Iterable<E>) queryParams).transform(NetworkErrorLogger$$Lambda$1.lambdaFactory$()).join(Joiner.m1895on(10)));
        }
        formatAndNotifyError(strap, requestClass);
        AirbnbEventLogger.track("android_network_error", strap);
    }

    static /* synthetic */ String lambda$logErrorToBugSnag$0(Query q) {
        return "key: \"" + q.name() + "\", value: \"" + q.value() + "\"";
    }

    private static void formatAndNotifyError(Strap error, String requestClass) {
        StackTraceElement[] stackTrace = {new StackTraceElement(requestClass, error.getString(KEY_METHOD), requestClass, -1)};
        MetaDataWrapper requestMetaData = new MetaDataWrapper();
        for (Entry<String, String> entry : error.entrySet()) {
            requestMetaData.addToTab("Request Info", (String) entry.getKey(), entry.getValue());
        }
        requestMetaData.setGroupingHash(requestClass + error.getString(KEY_METHOD) + error.getString("error") + error.getString("error_type") + error.getString("error_code") + error.getString(BATCH_REQUESTS));
        String errorName = "Network Error " + error.getString("error_code");
        StringBuilder errorMessage = new StringBuilder(requestClass);
        errorMessage.append(" has failed");
        if ("en".equals(Locale.getDefault().getLanguage())) {
            errorMessage.append(": ");
            errorMessage.append(error.getString("error_message"));
            errorMessage.append(" -- ");
            errorMessage.append(error.getString(ErrorResponse.ERROR_DETAILS));
        }
        BugsnagWrapper.notify(errorName, errorMessage.toString(), requestClass, stackTrace, Severity.INFO, requestMetaData);
    }
}
