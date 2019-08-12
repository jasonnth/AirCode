package com.airbnb.android.core.requests.base;

import android.content.Context;
import android.content.Intent;
import com.airbnb.airrequest.DefaultErrorResponse;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import p032rx.functions.Action1;

public class UnavailableRegionErrorHandler implements Action1<Throwable> {
    public static final UnavailableRegionErrorHandler INSTANCE = new UnavailableRegionErrorHandler();
    private static final String UNAVAILABLE_REGION_URL = "https://www.airbnb.com/region_unavailable/index.html";

    private UnavailableRegionErrorHandler() {
    }

    public void call(Throwable throwable) {
        if ((throwable instanceof NetworkException) && new DefaultErrorResponse((NetworkException) throwable).isUnavailableRegionsError()) {
            Context context = CoreApplication.appContext();
            Intent webIntent = WebViewIntentBuilder.newBuilder(context, UNAVAILABLE_REGION_URL).toIntent();
            webIntent.setFlags(805306368);
            context.startActivity(webIntent);
        }
    }
}
