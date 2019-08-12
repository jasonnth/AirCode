package com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes;

import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class HomesRequestInfoParam implements Parcelable {
    private static final String CONTEXT_VALUE = "mobile";
    private static final String MOBILE_CLIENT_VALUE = "android";

    public static abstract class Builder {
        /* access modifiers changed from: 0000 */
        public abstract HomesRequestInfoParam autoBuild();

        public abstract Builder context(String str);

        public abstract Builder mobileClient(String str);

        public HomesRequestInfoParam build() {
            context(HomesRequestInfoParam.CONTEXT_VALUE);
            mobileClient("android");
            return autoBuild();
        }
    }

    @JsonProperty("context")
    public abstract String context();

    @JsonProperty("mobile_client")
    public abstract String mobileClient();

    public static Builder builder() {
        return new Builder();
    }
}
