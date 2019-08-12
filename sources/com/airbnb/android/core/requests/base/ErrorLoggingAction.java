package com.airbnb.android.core.requests.base;

import android.content.Context;
import com.airbnb.airrequest.AirRequest;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.C0715L;
import com.airbnb.android.core.JitneyPublisher;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.requests.AirBatchRequest;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.jitney.event.logging.NativeModeType.p160v1.C2446NativeModeType;
import com.airbnb.jitney.event.logging.Systems.p263v1.SystemsNativeNetworkRequestTimeoutEvent.Builder;
import com.bugsnag.android.MetaData;
import com.google.common.base.Joiner;
import com.google.common.collect.FluentIterable;
import java.net.SocketTimeoutException;
import okhttp3.Request;
import okhttp3.Response;
import p032rx.functions.Action1;

public final class ErrorLoggingAction implements Action1<Throwable> {
    private static final String PACKAGE = ErrorLoggingAction.class.getPackage().getName();
    private static final String TAG = ErrorLoggingAction.class.getSimpleName();
    private final Context context;
    private final LoggingContextFactory loggingContextFactory;
    private final SharedPrefsHelper sharedPrefsHelper;

    public ErrorLoggingAction(LoggingContextFactory loggingContextFactory2, Context context2, SharedPrefsHelper sharedPrefsHelper2) {
        this.loggingContextFactory = loggingContextFactory2;
        this.context = context2;
        this.sharedPrefsHelper = sharedPrefsHelper2;
    }

    private static String buildSourceStackTrace() {
        return FluentIterable.m1283of(Thread.currentThread().getStackTrace()).filter(ErrorLoggingAction$$Lambda$1.lambdaFactory$()).filter(ErrorLoggingAction$$Lambda$2.lambdaFactory$()).limit(5).join(Joiner.m1896on("\n"));
    }

    static /* synthetic */ boolean lambda$buildSourceStackTrace$1(StackTraceElement e) {
        return !e.getClassName().contains(PACKAGE);
    }

    public void call(Throwable throwable) {
        String sourceStackTrace = buildSourceStackTrace();
        if (throwable instanceof AirRequestNetworkException) {
            AirRequestNetworkException e = (AirRequestNetworkException) throwable;
            AirRequest airRequest = e.request();
            String requestClass = airRequest.getClass().getSimpleName();
            C0715L.m1192e(TAG, requestClass + " failed: " + throwable.getMessage(), throwable);
            Response response = e.rawResponse();
            if (!(response == null || e.bodyString() == null)) {
                Request request = response.request();
                C0715L.m1191e(TAG, requestClass + " " + request.method() + " request url: " + request.url().toString() + "\nResponse body: " + e.bodyString() + "\n");
                NetworkErrorLogger.logErrorToBugSnag(e, airRequest, sourceStackTrace);
            }
            if (Trebuchet.launch(TrebuchetKeys.TRACK_REQUEST_TIMEOUT) && e.getCause() != null && (e.getCause() instanceof SocketTimeoutException)) {
                logNetworkTimeout(airRequest);
                return;
            }
            return;
        }
        MetaData metadata = new MetaData();
        metadata.addToTab("Request Info", "source", sourceStackTrace);
        BugsnagWrapper.notify(throwable, metadata);
    }

    private void logNetworkTimeout(AirRequest request) {
        JitneyPublisher.publish(new Builder(this.loggingContextFactory.newInstance(), getRequestString(request), NetworkUtil.getJitneyNetworkType(this.context), getAppMode()));
    }

    private C2446NativeModeType getAppMode() {
        return this.sharedPrefsHelper.isGuestMode() ? C2446NativeModeType.Guest : C2446NativeModeType.Host;
    }

    private static String getRequestString(AirRequest request) {
        if (request instanceof AirBatchRequest) {
            return ((AirBatchRequest) request).getRequestsString();
        }
        return request.getClass().getSimpleName();
    }
}
