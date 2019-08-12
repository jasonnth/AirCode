package com.airbnb.android.core.modules;

import android.content.Context;
import com.airbnb.airrequest.Transformer;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.requests.base.AirRequestHeadersInterceptor;
import com.airbnb.android.core.requests.base.AirlockErrorHandler;
import com.airbnb.android.core.utils.SharedPrefsHelper;

final /* synthetic */ class NetworkModule$$Lambda$2 implements Transformer {
    private final AirRequestHeadersInterceptor arg$1;
    private final AirlockErrorHandler arg$2;
    private final LoggingContextFactory arg$3;
    private final Context arg$4;
    private final SharedPrefsHelper arg$5;

    private NetworkModule$$Lambda$2(AirRequestHeadersInterceptor airRequestHeadersInterceptor, AirlockErrorHandler airlockErrorHandler, LoggingContextFactory loggingContextFactory, Context context, SharedPrefsHelper sharedPrefsHelper) {
        this.arg$1 = airRequestHeadersInterceptor;
        this.arg$2 = airlockErrorHandler;
        this.arg$3 = loggingContextFactory;
        this.arg$4 = context;
        this.arg$5 = sharedPrefsHelper;
    }

    public static Transformer lambdaFactory$(AirRequestHeadersInterceptor airRequestHeadersInterceptor, AirlockErrorHandler airlockErrorHandler, LoggingContextFactory loggingContextFactory, Context context, SharedPrefsHelper sharedPrefsHelper) {
        return new NetworkModule$$Lambda$2(airRequestHeadersInterceptor, airlockErrorHandler, loggingContextFactory, context, sharedPrefsHelper);
    }

    public Object call(Object obj) {
        return this.arg$1.getClass();
    }
}
