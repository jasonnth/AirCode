package com.airbnb.android.lib.payments.creditcard.brazil.networking.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/* renamed from: com.airbnb.android.lib.payments.creditcard.brazil.networking.response.$AutoValue_BrazilCep reason: invalid class name */
abstract class C$AutoValue_BrazilCep extends BrazilCep {
    private final String address;
    private final String city;
    private final String state;

    /* renamed from: com.airbnb.android.lib.payments.creditcard.brazil.networking.response.$AutoValue_BrazilCep$Builder */
    static final class Builder extends com.airbnb.android.lib.payments.creditcard.brazil.networking.response.BrazilCep.Builder {
        private String address;
        private String city;
        private String state;

        Builder() {
        }

        public com.airbnb.android.lib.payments.creditcard.brazil.networking.response.BrazilCep.Builder address(String address2) {
            if (address2 == null) {
                throw new NullPointerException("Null address");
            }
            this.address = address2;
            return this;
        }

        public com.airbnb.android.lib.payments.creditcard.brazil.networking.response.BrazilCep.Builder city(String city2) {
            if (city2 == null) {
                throw new NullPointerException("Null city");
            }
            this.city = city2;
            return this;
        }

        public com.airbnb.android.lib.payments.creditcard.brazil.networking.response.BrazilCep.Builder state(String state2) {
            if (state2 == null) {
                throw new NullPointerException("Null state");
            }
            this.state = state2;
            return this;
        }

        public BrazilCep build() {
            String missing = "";
            if (this.address == null) {
                missing = missing + " address";
            }
            if (this.city == null) {
                missing = missing + " city";
            }
            if (this.state == null) {
                missing = missing + " state";
            }
            if (missing.isEmpty()) {
                return new AutoValue_BrazilCep(this.address, this.city, this.state);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_BrazilCep(String address2, String city2, String state2) {
        if (address2 == null) {
            throw new NullPointerException("Null address");
        }
        this.address = address2;
        if (city2 == null) {
            throw new NullPointerException("Null city");
        }
        this.city = city2;
        if (state2 == null) {
            throw new NullPointerException("Null state");
        }
        this.state = state2;
    }

    @JsonProperty("address")
    public String address() {
        return this.address;
    }

    @JsonProperty("city")
    public String city() {
        return this.city;
    }

    @JsonProperty("state")
    public String state() {
        return this.state;
    }

    public String toString() {
        return "BrazilCep{address=" + this.address + ", city=" + this.city + ", state=" + this.state + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BrazilCep)) {
            return false;
        }
        BrazilCep that = (BrazilCep) o;
        if (!this.address.equals(that.address()) || !this.city.equals(that.city()) || !this.state.equals(that.state())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((1 * 1000003) ^ this.address.hashCode()) * 1000003) ^ this.city.hashCode()) * 1000003) ^ this.state.hashCode();
    }
}
