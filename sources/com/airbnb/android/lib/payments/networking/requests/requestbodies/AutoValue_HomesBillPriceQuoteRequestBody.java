package com.airbnb.android.lib.payments.networking.requests.requestbodies;

import com.airbnb.android.lib.payments.networking.requests.requestbodies.params.PaymentParam;
import com.airbnb.android.lib.payments.networking.requests.requestbodies.params.ProductParam;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

final class AutoValue_HomesBillPriceQuoteRequestBody extends HomesBillPriceQuoteRequestBody {
    private final PaymentParam paymentParams;
    private final List<ProductParam> products;
    private final int version;

    static final class Builder extends com.airbnb.android.lib.payments.networking.requests.requestbodies.HomesBillPriceQuoteRequestBody.Builder {
        private PaymentParam paymentParams;
        private List<ProductParam> products;
        private Integer version;

        Builder() {
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.HomesBillPriceQuoteRequestBody.Builder products(List<ProductParam> products2) {
            if (products2 == null) {
                throw new NullPointerException("Null products");
            }
            this.products = products2;
            return this;
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.HomesBillPriceQuoteRequestBody.Builder paymentParams(PaymentParam paymentParams2) {
            if (paymentParams2 == null) {
                throw new NullPointerException("Null paymentParams");
            }
            this.paymentParams = paymentParams2;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public com.airbnb.android.lib.payments.networking.requests.requestbodies.HomesBillPriceQuoteRequestBody.Builder version(int version2) {
            this.version = Integer.valueOf(version2);
            return this;
        }

        /* access modifiers changed from: 0000 */
        public HomesBillPriceQuoteRequestBody autoBuild() {
            String missing = "";
            if (this.products == null) {
                missing = missing + " products";
            }
            if (this.paymentParams == null) {
                missing = missing + " paymentParams";
            }
            if (this.version == null) {
                missing = missing + " version";
            }
            if (missing.isEmpty()) {
                return new AutoValue_HomesBillPriceQuoteRequestBody(this.products, this.paymentParams, this.version.intValue());
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    private AutoValue_HomesBillPriceQuoteRequestBody(List<ProductParam> products2, PaymentParam paymentParams2, int version2) {
        this.products = products2;
        this.paymentParams = paymentParams2;
        this.version = version2;
    }

    @JsonProperty("products")
    public List<ProductParam> products() {
        return this.products;
    }

    @JsonProperty("payment_params")
    public PaymentParam paymentParams() {
        return this.paymentParams;
    }

    @JsonProperty("version")
    public int version() {
        return this.version;
    }

    public String toString() {
        return "HomesBillPriceQuoteRequestBody{products=" + this.products + ", paymentParams=" + this.paymentParams + ", version=" + this.version + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof HomesBillPriceQuoteRequestBody)) {
            return false;
        }
        HomesBillPriceQuoteRequestBody that = (HomesBillPriceQuoteRequestBody) o;
        if (!this.products.equals(that.products()) || !this.paymentParams.equals(that.paymentParams()) || this.version != that.version()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((1 * 1000003) ^ this.products.hashCode()) * 1000003) ^ this.paymentParams.hashCode()) * 1000003) ^ this.version;
    }
}
