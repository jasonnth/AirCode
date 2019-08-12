package com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes;

import com.fasterxml.jackson.annotation.JsonProperty;

/* renamed from: com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.$AutoValue_HomesReservationDetailsProductParam reason: invalid class name */
abstract class C$AutoValue_HomesReservationDetailsProductParam extends HomesReservationDetailsProductParam {
    private final String messageToHost;

    /* renamed from: com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.$AutoValue_HomesReservationDetailsProductParam$Builder */
    static final class Builder extends com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.HomesReservationDetailsProductParam.Builder {
        private String messageToHost;

        Builder() {
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.HomesReservationDetailsProductParam.Builder messageToHost(String messageToHost2) {
            this.messageToHost = messageToHost2;
            return this;
        }

        public HomesReservationDetailsProductParam build() {
            return new AutoValue_HomesReservationDetailsProductParam(this.messageToHost);
        }
    }

    C$AutoValue_HomesReservationDetailsProductParam(String messageToHost2) {
        this.messageToHost = messageToHost2;
    }

    @JsonProperty("message_to_host")
    public String messageToHost() {
        return this.messageToHost;
    }

    public String toString() {
        return "HomesReservationDetailsProductParam{messageToHost=" + this.messageToHost + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof HomesReservationDetailsProductParam)) {
            return false;
        }
        HomesReservationDetailsProductParam that = (HomesReservationDetailsProductParam) o;
        if (this.messageToHost != null) {
            return this.messageToHost.equals(that.messageToHost());
        }
        if (that.messageToHost() != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (1 * 1000003) ^ (this.messageToHost == null ? 0 : this.messageToHost.hashCode());
    }
}
