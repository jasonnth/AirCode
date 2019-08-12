package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.android.core.responses.HostCancellationDisclaimerResponse;
import java.lang.reflect.Type;
import p032rx.Observer;

public class HostCancellationDisclaimerRequest extends BaseRequestV2<HostCancellationDisclaimerResponse> {
    private final String code;

    public static HostCancellationDisclaimerRequest forCancellationDisclaimer(String code2, BaseRequestListener<HostCancellationDisclaimerResponse> listener) {
        return new HostCancellationDisclaimerRequest(code2, listener);
    }

    private HostCancellationDisclaimerRequest(String code2, BaseRequestListener<HostCancellationDisclaimerResponse> listener) {
        withListener((Observer) listener);
        this.code = code2;
    }

    public long getCacheTimeoutMs() {
        return 86400000;
    }

    public String getPath() {
        return "cancellation_disclaimers/reservations/" + this.code;
    }

    public Type successResponseType() {
        return HostCancellationDisclaimerResponse.class;
    }
}
