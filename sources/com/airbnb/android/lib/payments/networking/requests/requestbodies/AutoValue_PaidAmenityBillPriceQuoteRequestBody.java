package com.airbnb.android.lib.payments.networking.requests.requestbodies;

import com.airbnb.android.core.enums.HelpCenterArticle;
import com.fasterxml.jackson.annotation.JsonProperty;

final class AutoValue_PaidAmenityBillPriceQuoteRequestBody extends PaidAmenityBillPriceQuoteRequestBody {
    private final String displayCurrency;
    private final boolean isAirbnbCreditIncluded;
    private final int numberOfUnits;
    private final long paidAmenityId;
    private final String paymentInstrumentId;
    private final String productType;
    private final String reservationConfirmationCode;
    private final String userId;

    static final class Builder extends com.airbnb.android.lib.payments.networking.requests.requestbodies.PaidAmenityBillPriceQuoteRequestBody.Builder {
        private String displayCurrency;
        private Boolean isAirbnbCreditIncluded;
        private Integer numberOfUnits;
        private Long paidAmenityId;
        private String paymentInstrumentId;
        private String productType;
        private String reservationConfirmationCode;
        private String userId;

        Builder() {
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.PaidAmenityBillPriceQuoteRequestBody.Builder userId(String userId2) {
            if (userId2 == null) {
                throw new NullPointerException("Null userId");
            }
            this.userId = userId2;
            return this;
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.PaidAmenityBillPriceQuoteRequestBody.Builder paymentInstrumentId(String paymentInstrumentId2) {
            if (paymentInstrumentId2 == null) {
                throw new NullPointerException("Null paymentInstrumentId");
            }
            this.paymentInstrumentId = paymentInstrumentId2;
            return this;
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.PaidAmenityBillPriceQuoteRequestBody.Builder displayCurrency(String displayCurrency2) {
            if (displayCurrency2 == null) {
                throw new NullPointerException("Null displayCurrency");
            }
            this.displayCurrency = displayCurrency2;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public com.airbnb.android.lib.payments.networking.requests.requestbodies.PaidAmenityBillPriceQuoteRequestBody.Builder productType(String productType2) {
            if (productType2 == null) {
                throw new NullPointerException("Null productType");
            }
            this.productType = productType2;
            return this;
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.PaidAmenityBillPriceQuoteRequestBody.Builder isAirbnbCreditIncluded(boolean isAirbnbCreditIncluded2) {
            this.isAirbnbCreditIncluded = Boolean.valueOf(isAirbnbCreditIncluded2);
            return this;
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.PaidAmenityBillPriceQuoteRequestBody.Builder paidAmenityId(long paidAmenityId2) {
            this.paidAmenityId = Long.valueOf(paidAmenityId2);
            return this;
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.PaidAmenityBillPriceQuoteRequestBody.Builder numberOfUnits(int numberOfUnits2) {
            this.numberOfUnits = Integer.valueOf(numberOfUnits2);
            return this;
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.PaidAmenityBillPriceQuoteRequestBody.Builder reservationConfirmationCode(String reservationConfirmationCode2) {
            if (reservationConfirmationCode2 == null) {
                throw new NullPointerException("Null reservationConfirmationCode");
            }
            this.reservationConfirmationCode = reservationConfirmationCode2;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public PaidAmenityBillPriceQuoteRequestBody autoBuild() {
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
            if (this.paidAmenityId == null) {
                missing = missing + " paidAmenityId";
            }
            if (this.numberOfUnits == null) {
                missing = missing + " numberOfUnits";
            }
            if (this.reservationConfirmationCode == null) {
                missing = missing + " reservationConfirmationCode";
            }
            if (missing.isEmpty()) {
                return new AutoValue_PaidAmenityBillPriceQuoteRequestBody(this.userId, this.paymentInstrumentId, this.displayCurrency, this.productType, this.isAirbnbCreditIncluded.booleanValue(), this.paidAmenityId.longValue(), this.numberOfUnits.intValue(), this.reservationConfirmationCode);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    private AutoValue_PaidAmenityBillPriceQuoteRequestBody(String userId2, String paymentInstrumentId2, String displayCurrency2, String productType2, boolean isAirbnbCreditIncluded2, long paidAmenityId2, int numberOfUnits2, String reservationConfirmationCode2) {
        this.userId = userId2;
        this.paymentInstrumentId = paymentInstrumentId2;
        this.displayCurrency = displayCurrency2;
        this.productType = productType2;
        this.isAirbnbCreditIncluded = isAirbnbCreditIncluded2;
        this.paidAmenityId = paidAmenityId2;
        this.numberOfUnits = numberOfUnits2;
        this.reservationConfirmationCode = reservationConfirmationCode2;
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

    @JsonProperty("paid_amenity_id")
    public long paidAmenityId() {
        return this.paidAmenityId;
    }

    @JsonProperty("number_of_units")
    public int numberOfUnits() {
        return this.numberOfUnits;
    }

    @JsonProperty("reservation_confirmation_code")
    public String reservationConfirmationCode() {
        return this.reservationConfirmationCode;
    }

    public String toString() {
        return "PaidAmenityBillPriceQuoteRequestBody{userId=" + this.userId + ", paymentInstrumentId=" + this.paymentInstrumentId + ", displayCurrency=" + this.displayCurrency + ", productType=" + this.productType + ", isAirbnbCreditIncluded=" + this.isAirbnbCreditIncluded + ", paidAmenityId=" + this.paidAmenityId + ", numberOfUnits=" + this.numberOfUnits + ", reservationConfirmationCode=" + this.reservationConfirmationCode + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PaidAmenityBillPriceQuoteRequestBody)) {
            return false;
        }
        PaidAmenityBillPriceQuoteRequestBody that = (PaidAmenityBillPriceQuoteRequestBody) o;
        if (!this.userId.equals(that.userId()) || !this.paymentInstrumentId.equals(that.paymentInstrumentId()) || !this.displayCurrency.equals(that.displayCurrency()) || !this.productType.equals(that.productType()) || this.isAirbnbCreditIncluded != that.isAirbnbCreditIncluded() || this.paidAmenityId != that.paidAmenityId() || this.numberOfUnits != that.numberOfUnits() || !this.reservationConfirmationCode.equals(that.reservationConfirmationCode())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((int) (((long) (((((((((((1 * 1000003) ^ this.userId.hashCode()) * 1000003) ^ this.paymentInstrumentId.hashCode()) * 1000003) ^ this.displayCurrency.hashCode()) * 1000003) ^ this.productType.hashCode()) * 1000003) ^ (this.isAirbnbCreditIncluded ? 1231 : HelpCenterArticle.VERIFIED_ID_LEARN_MORE)) * 1000003)) ^ ((this.paidAmenityId >>> 32) ^ this.paidAmenityId))) * 1000003) ^ this.numberOfUnits) * 1000003) ^ this.reservationConfirmationCode.hashCode();
    }
}
