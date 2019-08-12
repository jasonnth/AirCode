package com.airbnb.android.lib.payments.networking.createbill;

import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.payments.models.BillPriceQuote;
import com.airbnb.android.core.payments.models.BillProductType;
import com.airbnb.android.core.payments.models.clientparameters.QuickPayParameters;

/* renamed from: com.airbnb.android.lib.payments.networking.createbill.$AutoValue_CreateBillParameters reason: invalid class name */
abstract class C$AutoValue_CreateBillParameters extends CreateBillParameters {
    private final BillPriceQuote billPriceQuote;
    private final BillProductType billProductType;
    private final String currency;
    private final String cvvNonce;
    private final String postalCode;
    private final QuickPayParameters quickPayParameters;
    private final PaymentOption selectedPaymentOption;
    private final Boolean shouldIncludeAirbnbCredit;
    private final long userId;

    /* renamed from: com.airbnb.android.lib.payments.networking.createbill.$AutoValue_CreateBillParameters$Builder */
    static final class Builder extends com.airbnb.android.lib.payments.networking.createbill.CreateBillParameters.Builder {
        private BillPriceQuote billPriceQuote;
        private BillProductType billProductType;
        private String currency;
        private String cvvNonce;
        private String postalCode;
        private QuickPayParameters quickPayParameters;
        private PaymentOption selectedPaymentOption;
        private Boolean shouldIncludeAirbnbCredit;
        private Long userId;

        Builder() {
        }

        public com.airbnb.android.lib.payments.networking.createbill.CreateBillParameters.Builder billProductType(BillProductType billProductType2) {
            if (billProductType2 == null) {
                throw new NullPointerException("Null billProductType");
            }
            this.billProductType = billProductType2;
            return this;
        }

        public com.airbnb.android.lib.payments.networking.createbill.CreateBillParameters.Builder billPriceQuote(BillPriceQuote billPriceQuote2) {
            if (billPriceQuote2 == null) {
                throw new NullPointerException("Null billPriceQuote");
            }
            this.billPriceQuote = billPriceQuote2;
            return this;
        }

        public com.airbnb.android.lib.payments.networking.createbill.CreateBillParameters.Builder selectedPaymentOption(PaymentOption selectedPaymentOption2) {
            if (selectedPaymentOption2 == null) {
                throw new NullPointerException("Null selectedPaymentOption");
            }
            this.selectedPaymentOption = selectedPaymentOption2;
            return this;
        }

        public com.airbnb.android.lib.payments.networking.createbill.CreateBillParameters.Builder userId(long userId2) {
            this.userId = Long.valueOf(userId2);
            return this;
        }

        public com.airbnb.android.lib.payments.networking.createbill.CreateBillParameters.Builder currency(String currency2) {
            if (currency2 == null) {
                throw new NullPointerException("Null currency");
            }
            this.currency = currency2;
            return this;
        }

        public com.airbnb.android.lib.payments.networking.createbill.CreateBillParameters.Builder quickPayParameters(QuickPayParameters quickPayParameters2) {
            this.quickPayParameters = quickPayParameters2;
            return this;
        }

        public com.airbnb.android.lib.payments.networking.createbill.CreateBillParameters.Builder shouldIncludeAirbnbCredit(Boolean shouldIncludeAirbnbCredit2) {
            this.shouldIncludeAirbnbCredit = shouldIncludeAirbnbCredit2;
            return this;
        }

        public com.airbnb.android.lib.payments.networking.createbill.CreateBillParameters.Builder postalCode(String postalCode2) {
            this.postalCode = postalCode2;
            return this;
        }

        public com.airbnb.android.lib.payments.networking.createbill.CreateBillParameters.Builder cvvNonce(String cvvNonce2) {
            this.cvvNonce = cvvNonce2;
            return this;
        }

        public CreateBillParameters build() {
            String missing = "";
            if (this.billProductType == null) {
                missing = missing + " billProductType";
            }
            if (this.billPriceQuote == null) {
                missing = missing + " billPriceQuote";
            }
            if (this.selectedPaymentOption == null) {
                missing = missing + " selectedPaymentOption";
            }
            if (this.userId == null) {
                missing = missing + " userId";
            }
            if (this.currency == null) {
                missing = missing + " currency";
            }
            if (missing.isEmpty()) {
                return new AutoValue_CreateBillParameters(this.billProductType, this.billPriceQuote, this.selectedPaymentOption, this.userId.longValue(), this.currency, this.quickPayParameters, this.shouldIncludeAirbnbCredit, this.postalCode, this.cvvNonce);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_CreateBillParameters(BillProductType billProductType2, BillPriceQuote billPriceQuote2, PaymentOption selectedPaymentOption2, long userId2, String currency2, QuickPayParameters quickPayParameters2, Boolean shouldIncludeAirbnbCredit2, String postalCode2, String cvvNonce2) {
        if (billProductType2 == null) {
            throw new NullPointerException("Null billProductType");
        }
        this.billProductType = billProductType2;
        if (billPriceQuote2 == null) {
            throw new NullPointerException("Null billPriceQuote");
        }
        this.billPriceQuote = billPriceQuote2;
        if (selectedPaymentOption2 == null) {
            throw new NullPointerException("Null selectedPaymentOption");
        }
        this.selectedPaymentOption = selectedPaymentOption2;
        this.userId = userId2;
        if (currency2 == null) {
            throw new NullPointerException("Null currency");
        }
        this.currency = currency2;
        this.quickPayParameters = quickPayParameters2;
        this.shouldIncludeAirbnbCredit = shouldIncludeAirbnbCredit2;
        this.postalCode = postalCode2;
        this.cvvNonce = cvvNonce2;
    }

    public BillProductType billProductType() {
        return this.billProductType;
    }

    public BillPriceQuote billPriceQuote() {
        return this.billPriceQuote;
    }

    public PaymentOption selectedPaymentOption() {
        return this.selectedPaymentOption;
    }

    public long userId() {
        return this.userId;
    }

    public String currency() {
        return this.currency;
    }

    public QuickPayParameters quickPayParameters() {
        return this.quickPayParameters;
    }

    public Boolean shouldIncludeAirbnbCredit() {
        return this.shouldIncludeAirbnbCredit;
    }

    public String postalCode() {
        return this.postalCode;
    }

    public String cvvNonce() {
        return this.cvvNonce;
    }

    public String toString() {
        return "CreateBillParameters{billProductType=" + this.billProductType + ", billPriceQuote=" + this.billPriceQuote + ", selectedPaymentOption=" + this.selectedPaymentOption + ", userId=" + this.userId + ", currency=" + this.currency + ", quickPayParameters=" + this.quickPayParameters + ", shouldIncludeAirbnbCredit=" + this.shouldIncludeAirbnbCredit + ", postalCode=" + this.postalCode + ", cvvNonce=" + this.cvvNonce + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CreateBillParameters)) {
            return false;
        }
        CreateBillParameters that = (CreateBillParameters) o;
        if (this.billProductType.equals(that.billProductType()) && this.billPriceQuote.equals(that.billPriceQuote()) && this.selectedPaymentOption.equals(that.selectedPaymentOption()) && this.userId == that.userId() && this.currency.equals(that.currency()) && (this.quickPayParameters != null ? this.quickPayParameters.equals(that.quickPayParameters()) : that.quickPayParameters() == null) && (this.shouldIncludeAirbnbCredit != null ? this.shouldIncludeAirbnbCredit.equals(that.shouldIncludeAirbnbCredit()) : that.shouldIncludeAirbnbCredit() == null) && (this.postalCode != null ? this.postalCode.equals(that.postalCode()) : that.postalCode() == null)) {
            if (this.cvvNonce == null) {
                if (that.cvvNonce() == null) {
                    return true;
                }
            } else if (this.cvvNonce.equals(that.cvvNonce())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((((((int) (((long) (((((((1 * 1000003) ^ this.billProductType.hashCode()) * 1000003) ^ this.billPriceQuote.hashCode()) * 1000003) ^ this.selectedPaymentOption.hashCode()) * 1000003)) ^ ((this.userId >>> 32) ^ this.userId))) * 1000003) ^ this.currency.hashCode()) * 1000003) ^ (this.quickPayParameters == null ? 0 : this.quickPayParameters.hashCode())) * 1000003) ^ (this.shouldIncludeAirbnbCredit == null ? 0 : this.shouldIncludeAirbnbCredit.hashCode())) * 1000003) ^ (this.postalCode == null ? 0 : this.postalCode.hashCode())) * 1000003;
        if (this.cvvNonce != null) {
            i = this.cvvNonce.hashCode();
        }
        return h ^ i;
    }
}
