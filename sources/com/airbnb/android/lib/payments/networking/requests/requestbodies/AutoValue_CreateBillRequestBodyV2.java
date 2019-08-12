package com.airbnb.android.lib.payments.networking.requests.requestbodies;

import com.airbnb.android.lib.payments.networking.requests.requestbodies.params.PaymentParam;
import com.fasterxml.jackson.annotation.JsonProperty;

final class AutoValue_CreateBillRequestBodyV2 extends CreateBillRequestBodyV2 {
    private final String _format;
    private final String _intents;
    private final String billPriceQuoteKey;
    private final String idempodenceKey;
    private final PaymentParam paymentParam;
    private final long userId;
    private final int version;

    static final class Builder extends com.airbnb.android.lib.payments.networking.requests.requestbodies.CreateBillRequestBodyV2.Builder {
        private String _format;
        private String _intents;
        private String billPriceQuoteKey;
        private String idempodenceKey;
        private PaymentParam paymentParam;
        private Long userId;
        private Integer version;

        Builder() {
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.CreateBillRequestBodyV2.Builder userId(long userId2) {
            this.userId = Long.valueOf(userId2);
            return this;
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.CreateBillRequestBodyV2.Builder billPriceQuoteKey(String billPriceQuoteKey2) {
            if (billPriceQuoteKey2 == null) {
                throw new NullPointerException("Null billPriceQuoteKey");
            }
            this.billPriceQuoteKey = billPriceQuoteKey2;
            return this;
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.CreateBillRequestBodyV2.Builder idempodenceKey(String idempodenceKey2) {
            if (idempodenceKey2 == null) {
                throw new NullPointerException("Null idempodenceKey");
            }
            this.idempodenceKey = idempodenceKey2;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public com.airbnb.android.lib.payments.networking.requests.requestbodies.CreateBillRequestBodyV2.Builder version(int version2) {
            this.version = Integer.valueOf(version2);
            return this;
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.CreateBillRequestBodyV2.Builder paymentParam(PaymentParam paymentParam2) {
            this.paymentParam = paymentParam2;
            return this;
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.CreateBillRequestBodyV2.Builder _format(String _format2) {
            this._format = _format2;
            return this;
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.CreateBillRequestBodyV2.Builder _intents(String _intents2) {
            this._intents = _intents2;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public CreateBillRequestBodyV2 autoBuild() {
            String missing = "";
            if (this.userId == null) {
                missing = missing + " userId";
            }
            if (this.billPriceQuoteKey == null) {
                missing = missing + " billPriceQuoteKey";
            }
            if (this.idempodenceKey == null) {
                missing = missing + " idempodenceKey";
            }
            if (this.version == null) {
                missing = missing + " version";
            }
            if (missing.isEmpty()) {
                return new AutoValue_CreateBillRequestBodyV2(this.userId.longValue(), this.billPriceQuoteKey, this.idempodenceKey, this.version.intValue(), this.paymentParam, this._format, this._intents);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    private AutoValue_CreateBillRequestBodyV2(long userId2, String billPriceQuoteKey2, String idempodenceKey2, int version2, PaymentParam paymentParam2, String _format2, String _intents2) {
        this.userId = userId2;
        this.billPriceQuoteKey = billPriceQuoteKey2;
        this.idempodenceKey = idempodenceKey2;
        this.version = version2;
        this.paymentParam = paymentParam2;
        this._format = _format2;
        this._intents = _intents2;
    }

    @JsonProperty("user_id")
    public long userId() {
        return this.userId;
    }

    @JsonProperty("bill_price_quote_key")
    public String billPriceQuoteKey() {
        return this.billPriceQuoteKey;
    }

    @JsonProperty("idempotence_key")
    public String idempodenceKey() {
        return this.idempodenceKey;
    }

    @JsonProperty("version")
    public int version() {
        return this.version;
    }

    @JsonProperty("payment_params")
    public PaymentParam paymentParam() {
        return this.paymentParam;
    }

    @JsonProperty("_format")
    public String _format() {
        return this._format;
    }

    @JsonProperty("_intents")
    public String _intents() {
        return this._intents;
    }

    public String toString() {
        return "CreateBillRequestBodyV2{userId=" + this.userId + ", billPriceQuoteKey=" + this.billPriceQuoteKey + ", idempodenceKey=" + this.idempodenceKey + ", version=" + this.version + ", paymentParam=" + this.paymentParam + ", _format=" + this._format + ", _intents=" + this._intents + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CreateBillRequestBodyV2)) {
            return false;
        }
        CreateBillRequestBodyV2 that = (CreateBillRequestBodyV2) o;
        if (this.userId == that.userId() && this.billPriceQuoteKey.equals(that.billPriceQuoteKey()) && this.idempodenceKey.equals(that.idempodenceKey()) && this.version == that.version() && (this.paymentParam != null ? this.paymentParam.equals(that.paymentParam()) : that.paymentParam() == null) && (this._format != null ? this._format.equals(that._format()) : that._format() == null)) {
            if (this._intents == null) {
                if (that._intents() == null) {
                    return true;
                }
            } else if (this._intents.equals(that._intents())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((((((((int) (((long) (1 * 1000003)) ^ ((this.userId >>> 32) ^ this.userId))) * 1000003) ^ this.billPriceQuoteKey.hashCode()) * 1000003) ^ this.idempodenceKey.hashCode()) * 1000003) ^ this.version) * 1000003) ^ (this.paymentParam == null ? 0 : this.paymentParam.hashCode())) * 1000003) ^ (this._format == null ? 0 : this._format.hashCode())) * 1000003;
        if (this._intents != null) {
            i = this._intents.hashCode();
        }
        return h ^ i;
    }
}
