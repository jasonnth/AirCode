package com.airbnb.android.lib.payments.networking.requests.requestbodies;

import com.airbnb.android.core.enums.HelpCenterArticle;
import com.airbnb.android.core.payments.models.clientparameters.GiftCreditParams;
import com.fasterxml.jackson.annotation.JsonProperty;

final class AutoValue_GiftCreditBillPriceQuoteRequestBody extends GiftCreditBillPriceQuoteRequestBody {
    private final long amountMicros;
    private final long amountMicrosNative;
    private final long amountMicrosUsd;
    private final String displayCurrency;
    private final GiftCreditParams giftCreditParams;
    private final boolean isAirbnbCreditIncluded;
    private final String nativeCurrency;
    private final String payment2ProductType;
    private final String paymentInstrumentId;
    private final String productType;
    private final String userId;

    static final class Builder extends com.airbnb.android.lib.payments.networking.requests.requestbodies.GiftCreditBillPriceQuoteRequestBody.Builder {
        private Long amountMicros;
        private Long amountMicrosNative;
        private Long amountMicrosUsd;
        private String displayCurrency;
        private GiftCreditParams giftCreditParams;
        private Boolean isAirbnbCreditIncluded;
        private String nativeCurrency;
        private String payment2ProductType;
        private String paymentInstrumentId;
        private String productType;
        private String userId;

        Builder() {
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.GiftCreditBillPriceQuoteRequestBody.Builder userId(String userId2) {
            if (userId2 == null) {
                throw new NullPointerException("Null userId");
            }
            this.userId = userId2;
            return this;
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.GiftCreditBillPriceQuoteRequestBody.Builder paymentInstrumentId(String paymentInstrumentId2) {
            if (paymentInstrumentId2 == null) {
                throw new NullPointerException("Null paymentInstrumentId");
            }
            this.paymentInstrumentId = paymentInstrumentId2;
            return this;
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.GiftCreditBillPriceQuoteRequestBody.Builder displayCurrency(String displayCurrency2) {
            if (displayCurrency2 == null) {
                throw new NullPointerException("Null displayCurrency");
            }
            this.displayCurrency = displayCurrency2;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public com.airbnb.android.lib.payments.networking.requests.requestbodies.GiftCreditBillPriceQuoteRequestBody.Builder productType(String productType2) {
            if (productType2 == null) {
                throw new NullPointerException("Null productType");
            }
            this.productType = productType2;
            return this;
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.GiftCreditBillPriceQuoteRequestBody.Builder isAirbnbCreditIncluded(boolean isAirbnbCreditIncluded2) {
            this.isAirbnbCreditIncluded = Boolean.valueOf(isAirbnbCreditIncluded2);
            return this;
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.GiftCreditBillPriceQuoteRequestBody.Builder payment2ProductType(String payment2ProductType2) {
            if (payment2ProductType2 == null) {
                throw new NullPointerException("Null payment2ProductType");
            }
            this.payment2ProductType = payment2ProductType2;
            return this;
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.GiftCreditBillPriceQuoteRequestBody.Builder nativeCurrency(String nativeCurrency2) {
            if (nativeCurrency2 == null) {
                throw new NullPointerException("Null nativeCurrency");
            }
            this.nativeCurrency = nativeCurrency2;
            return this;
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.GiftCreditBillPriceQuoteRequestBody.Builder amountMicros(long amountMicros2) {
            this.amountMicros = Long.valueOf(amountMicros2);
            return this;
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.GiftCreditBillPriceQuoteRequestBody.Builder amountMicrosUsd(long amountMicrosUsd2) {
            this.amountMicrosUsd = Long.valueOf(amountMicrosUsd2);
            return this;
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.GiftCreditBillPriceQuoteRequestBody.Builder amountMicrosNative(long amountMicrosNative2) {
            this.amountMicrosNative = Long.valueOf(amountMicrosNative2);
            return this;
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.GiftCreditBillPriceQuoteRequestBody.Builder giftCreditParams(GiftCreditParams giftCreditParams2) {
            if (giftCreditParams2 == null) {
                throw new NullPointerException("Null giftCreditParams");
            }
            this.giftCreditParams = giftCreditParams2;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public GiftCreditBillPriceQuoteRequestBody autoBuild() {
            String missing = "";
            if (this.userId == null) {
                missing = missing + " userId";
            }
            if (this.paymentInstrumentId == null) {
                missing = missing + " paymentInstrumentId";
            }
            if (this.displayCurrency == null) {
                missing = missing + " displayCurrency";
            }
            if (this.productType == null) {
                missing = missing + " productType";
            }
            if (this.isAirbnbCreditIncluded == null) {
                missing = missing + " isAirbnbCreditIncluded";
            }
            if (this.payment2ProductType == null) {
                missing = missing + " payment2ProductType";
            }
            if (this.nativeCurrency == null) {
                missing = missing + " nativeCurrency";
            }
            if (this.amountMicros == null) {
                missing = missing + " amountMicros";
            }
            if (this.amountMicrosUsd == null) {
                missing = missing + " amountMicrosUsd";
            }
            if (this.amountMicrosNative == null) {
                missing = missing + " amountMicrosNative";
            }
            if (this.giftCreditParams == null) {
                missing = missing + " giftCreditParams";
            }
            if (missing.isEmpty()) {
                return new AutoValue_GiftCreditBillPriceQuoteRequestBody(this.userId, this.paymentInstrumentId, this.displayCurrency, this.productType, this.isAirbnbCreditIncluded.booleanValue(), this.payment2ProductType, this.nativeCurrency, this.amountMicros.longValue(), this.amountMicrosUsd.longValue(), this.amountMicrosNative.longValue(), this.giftCreditParams);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    private AutoValue_GiftCreditBillPriceQuoteRequestBody(String userId2, String paymentInstrumentId2, String displayCurrency2, String productType2, boolean isAirbnbCreditIncluded2, String payment2ProductType2, String nativeCurrency2, long amountMicros2, long amountMicrosUsd2, long amountMicrosNative2, GiftCreditParams giftCreditParams2) {
        this.userId = userId2;
        this.paymentInstrumentId = paymentInstrumentId2;
        this.displayCurrency = displayCurrency2;
        this.productType = productType2;
        this.isAirbnbCreditIncluded = isAirbnbCreditIncluded2;
        this.payment2ProductType = payment2ProductType2;
        this.nativeCurrency = nativeCurrency2;
        this.amountMicros = amountMicros2;
        this.amountMicrosUsd = amountMicrosUsd2;
        this.amountMicrosNative = amountMicrosNative2;
        this.giftCreditParams = giftCreditParams2;
    }

    @JsonProperty("user_id")
    public String userId() {
        return this.userId;
    }

    @JsonProperty("payment_instrument_id")
    public String paymentInstrumentId() {
        return this.paymentInstrumentId;
    }

    @JsonProperty("display_currency")
    public String displayCurrency() {
        return this.displayCurrency;
    }

    @JsonProperty("product_type")
    public String productType() {
        return this.productType;
    }

    @JsonProperty("include_airbnb_credit")
    public boolean isAirbnbCreditIncluded() {
        return this.isAirbnbCreditIncluded;
    }

    @JsonProperty("payment2_product_type")
    public String payment2ProductType() {
        return this.payment2ProductType;
    }

    @JsonProperty("native_currency")
    public String nativeCurrency() {
        return this.nativeCurrency;
    }

    @JsonProperty("amount_micros")
    public long amountMicros() {
        return this.amountMicros;
    }

    @JsonProperty("amount_micros_usd")
    public long amountMicrosUsd() {
        return this.amountMicrosUsd;
    }

    @JsonProperty("amount_micros_native")
    public long amountMicrosNative() {
        return this.amountMicrosNative;
    }

    @JsonProperty("gift_credit_params")
    public GiftCreditParams giftCreditParams() {
        return this.giftCreditParams;
    }

    public String toString() {
        return "GiftCreditBillPriceQuoteRequestBody{userId=" + this.userId + ", paymentInstrumentId=" + this.paymentInstrumentId + ", displayCurrency=" + this.displayCurrency + ", productType=" + this.productType + ", isAirbnbCreditIncluded=" + this.isAirbnbCreditIncluded + ", payment2ProductType=" + this.payment2ProductType + ", nativeCurrency=" + this.nativeCurrency + ", amountMicros=" + this.amountMicros + ", amountMicrosUsd=" + this.amountMicrosUsd + ", amountMicrosNative=" + this.amountMicrosNative + ", giftCreditParams=" + this.giftCreditParams + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof GiftCreditBillPriceQuoteRequestBody)) {
            return false;
        }
        GiftCreditBillPriceQuoteRequestBody that = (GiftCreditBillPriceQuoteRequestBody) o;
        if (!this.userId.equals(that.userId()) || !this.paymentInstrumentId.equals(that.paymentInstrumentId()) || !this.displayCurrency.equals(that.displayCurrency()) || !this.productType.equals(that.productType()) || this.isAirbnbCreditIncluded != that.isAirbnbCreditIncluded() || !this.payment2ProductType.equals(that.payment2ProductType()) || !this.nativeCurrency.equals(that.nativeCurrency()) || this.amountMicros != that.amountMicros() || this.amountMicrosUsd != that.amountMicrosUsd() || this.amountMicrosNative != that.amountMicrosNative() || !this.giftCreditParams.equals(that.giftCreditParams())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((int) (((long) (((int) (((long) (((int) (((long) (((((((((((((((1 * 1000003) ^ this.userId.hashCode()) * 1000003) ^ this.paymentInstrumentId.hashCode()) * 1000003) ^ this.displayCurrency.hashCode()) * 1000003) ^ this.productType.hashCode()) * 1000003) ^ (this.isAirbnbCreditIncluded ? 1231 : HelpCenterArticle.VERIFIED_ID_LEARN_MORE)) * 1000003) ^ this.payment2ProductType.hashCode()) * 1000003) ^ this.nativeCurrency.hashCode()) * 1000003)) ^ ((this.amountMicros >>> 32) ^ this.amountMicros))) * 1000003)) ^ ((this.amountMicrosUsd >>> 32) ^ this.amountMicrosUsd))) * 1000003)) ^ ((this.amountMicrosNative >>> 32) ^ this.amountMicrosNative))) * 1000003) ^ this.giftCreditParams.hashCode();
    }
}
