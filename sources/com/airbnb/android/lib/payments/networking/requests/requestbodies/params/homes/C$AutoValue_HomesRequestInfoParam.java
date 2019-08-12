package com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes;

import com.fasterxml.jackson.annotation.JsonProperty;

/* renamed from: com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.$AutoValue_HomesRequestInfoParam reason: invalid class name */
abstract class C$AutoValue_HomesRequestInfoParam extends HomesRequestInfoParam {
    private final String context;
    private final String mobileClient;

    /* renamed from: com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.$AutoValue_HomesRequestInfoParam$Builder */
    static final class Builder extends com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.HomesRequestInfoParam.Builder {
        private String context;
        private String mobileClient;

        Builder() {
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.HomesRequestInfoParam.Builder context(String context2) {
            if (context2 == null) {
                throw new NullPointerException("Null context");
            }
            this.context = context2;
            return this;
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.HomesRequestInfoParam.Builder mobileClient(String mobileClient2) {
            if (mobileClient2 == null) {
                throw new NullPointerException("Null mobileClient");
            }
            this.mobileClient = mobileClient2;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public HomesRequestInfoParam autoBuild() {
            String missing = "";
            if (this.context == null) {
                missing = missing + " context";
            }
            if (this.mobileClient == null) {
                missing = missing + " mobileClient";
            }
            if (missing.isEmpty()) {
                return new AutoValue_HomesRequestInfoParam(this.context, this.mobileClient);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_HomesRequestInfoParam(String context2, String mobileClient2) {
        if (context2 == null) {
            throw new NullPointerException("Null context");
        }
        this.context = context2;
        if (mobileClient2 == null) {
            throw new NullPointerException("Null mobileClient");
        }
        this.mobileClient = mobileClient2;
    }

    @JsonProperty("context")
    public String context() {
        return this.context;
    }

    @JsonProperty("mobile_client")
    public String mobileClient() {
        return this.mobileClient;
    }

    public String toString() {
        return "HomesRequestInfoParam{context=" + this.context + ", mobileClient=" + this.mobileClient + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof HomesRequestInfoParam)) {
            return false;
        }
        HomesRequestInfoParam that = (HomesRequestInfoParam) o;
        if (!this.context.equals(that.context()) || !this.mobileClient.equals(that.mobileClient())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((1 * 1000003) ^ this.context.hashCode()) * 1000003) ^ this.mobileClient.hashCode();
    }
}
