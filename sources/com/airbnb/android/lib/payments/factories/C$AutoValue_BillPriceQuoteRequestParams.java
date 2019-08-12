package com.airbnb.android.lib.payments.factories;

import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.payments.models.QuickPayClientType;
import com.airbnb.android.core.payments.models.clientparameters.QuickPayParameters;

/* renamed from: com.airbnb.android.lib.payments.factories.$AutoValue_BillPriceQuoteRequestParams reason: invalid class name */
abstract class C$AutoValue_BillPriceQuoteRequestParams extends BillPriceQuoteRequestParams {
    private final QuickPayClientType clientType;
    private final String couponCode;
    private final String displayCurrency;
    private final boolean includeAirbnbCredit;
    private final PaymentOption paymentOption;
    private final QuickPayParameters quickPayParameters;
    private final boolean userAgreedToCurrencyMismatch;
    private final String zipRetry;

    /* renamed from: com.airbnb.android.lib.payments.factories.$AutoValue_BillPriceQuoteRequestParams$Builder */
    static final class Builder extends com.airbnb.android.lib.payments.factories.BillPriceQuoteRequestParams.Builder {
        private QuickPayClientType clientType;
        private String couponCode;
        private String displayCurrency;
        private Boolean includeAirbnbCredit;
        private PaymentOption paymentOption;
        private QuickPayParameters quickPayParameters;
        private Boolean userAgreedToCurrencyMismatch;
        private String zipRetry;

        Builder() {
        }

        public com.airbnb.android.lib.payments.factories.BillPriceQuoteRequestParams.Builder clientType(QuickPayClientType clientType2) {
            if (clientType2 == null) {
                throw new NullPointerException("Null clientType");
            }
            this.clientType = clientType2;
            return this;
        }

        public com.airbnb.android.lib.payments.factories.BillPriceQuoteRequestParams.Builder paymentOption(PaymentOption paymentOption2) {
            if (paymentOption2 == null) {
                throw new NullPointerException("Null paymentOption");
            }
            this.paymentOption = paymentOption2;
            return this;
        }

        public com.airbnb.android.lib.payments.factories.BillPriceQuoteRequestParams.Builder quickPayParameters(QuickPayParameters quickPayParameters2) {
            if (quickPayParameters2 == null) {
                throw new NullPointerException("Null quickPayParameters");
            }
            this.quickPayParameters = quickPayParameters2;
            return this;
        }

        public com.airbnb.android.lib.payments.factories.BillPriceQuoteRequestParams.Builder includeAirbnbCredit(boolean includeAirbnbCredit2) {
            this.includeAirbnbCredit = Boolean.valueOf(includeAirbnbCredit2);
            return this;
        }

        public com.airbnb.android.lib.payments.factories.BillPriceQuoteRequestParams.Builder userAgreedToCurrencyMismatch(boolean userAgreedToCurrencyMismatch2) {
            this.userAgreedToCurrencyMismatch = Boolean.valueOf(userAgreedToCurrencyMismatch2);
            return this;
        }

        public com.airbnb.android.lib.payments.factories.BillPriceQuoteRequestParams.Builder displayCurrency(String displayCurrency2) {
            if (displayCurrency2 == null) {
                throw new NullPointerException("Null displayCurrency");
            }
            this.displayCurrency = displayCurrency2;
            return this;
        }

        public com.airbnb.android.lib.payments.factories.BillPriceQuoteRequestParams.Builder zipRetry(String zipRetry2) {
            this.zipRetry = zipRetry2;
            return this;
        }

        public com.airbnb.android.lib.payments.factories.BillPriceQuoteRequestParams.Builder couponCode(String couponCode2) {
            this.couponCode = couponCode2;
            return this;
        }

        public BillPriceQuoteRequestParams build() {
            String missing = "";
            if (this.clientType == null) {
                missing = missing + " clientType";
            }
            if (this.paymentOption == null) {
                missing = missing + " paymentOption";
            }
            if (this.quickPayParameters == null) {
                missing = missing + " quickPayParameters";
            }
            if (this.includeAirbnbCredit == null) {
                missing = missing + " includeAirbnbCredit";
            }
            if (this.userAgreedToCurrencyMismatch == null) {
                missing = missing + " userAgreedToCurrencyMismatch";
            }
            if (this.displayCurrency == null) {
                missing = missing + " displayCurrency";
            }
            if (missing.isEmpty()) {
                return new AutoValue_BillPriceQuoteRequestParams(this.clientType, this.paymentOption, this.quickPayParameters, this.includeAirbnbCredit.booleanValue(), this.userAgreedToCurrencyMismatch.booleanValue(), this.displayCurrency, this.zipRetry, this.couponCode);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_BillPriceQuoteRequestParams(QuickPayClientType clientType2, PaymentOption paymentOption2, QuickPayParameters quickPayParameters2, boolean includeAirbnbCredit2, boolean userAgreedToCurrencyMismatch2, String displayCurrency2, String zipRetry2, String couponCode2) {
        if (clientType2 == null) {
            throw new NullPointerException("Null clientType");
        }
        this.clientType = clientType2;
        if (paymentOption2 == null) {
            throw new NullPointerException("Null paymentOption");
        }
        this.paymentOption = paymentOption2;
        if (quickPayParameters2 == null) {
            throw new NullPointerException("Null quickPayParameters");
        }
        this.quickPayParameters = quickPayParameters2;
        this.includeAirbnbCredit = includeAirbnbCredit2;
        this.userAgreedToCurrencyMismatch = userAgreedToCurrencyMismatch2;
        if (displayCurrency2 == null) {
            throw new NullPointerException("Null displayCurrency");
        }
        this.displayCurrency = displayCurrency2;
        this.zipRetry = zipRetry2;
        this.couponCode = couponCode2;
    }

    public QuickPayClientType clientType() {
        return this.clientType;
    }

    public PaymentOption paymentOption() {
        return this.paymentOption;
    }

    public QuickPayParameters quickPayParameters() {
        return this.quickPayParameters;
    }

    public boolean includeAirbnbCredit() {
        return this.includeAirbnbCredit;
    }

    public boolean userAgreedToCurrencyMismatch() {
        return this.userAgreedToCurrencyMismatch;
    }

    public String displayCurrency() {
        return this.displayCurrency;
    }

    public String zipRetry() {
        return this.zipRetry;
    }

    public String couponCode() {
        return this.couponCode;
    }

    public String toString() {
        return "BillPriceQuoteRequestParams{clientType=" + this.clientType + ", paymentOption=" + this.paymentOption + ", quickPayParameters=" + this.quickPayParameters + ", includeAirbnbCredit=" + this.includeAirbnbCredit + ", userAgreedToCurrencyMismatch=" + this.userAgreedToCurrencyMismatch + ", displayCurrency=" + this.displayCurrency + ", zipRetry=" + this.zipRetry + ", couponCode=" + this.couponCode + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BillPriceQuoteRequestParams)) {
            return false;
        }
        BillPriceQuoteRequestParams that = (BillPriceQuoteRequestParams) o;
        if (this.clientType.equals(that.clientType()) && this.paymentOption.equals(that.paymentOption()) && this.quickPayParameters.equals(that.quickPayParameters()) && this.includeAirbnbCredit == that.includeAirbnbCredit() && this.userAgreedToCurrencyMismatch == that.userAgreedToCurrencyMismatch() && this.displayCurrency.equals(that.displayCurrency()) && (this.zipRetry != null ? this.zipRetry.equals(that.zipRetry()) : that.zipRetry() == null)) {
            if (this.couponCode == null) {
                if (that.couponCode() == null) {
                    return true;
                }
            } else if (this.couponCode.equals(that.couponCode())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 1231;
        int i2 = 0;
        int h = ((((((((1 * 1000003) ^ this.clientType.hashCode()) * 1000003) ^ this.paymentOption.hashCode()) * 1000003) ^ this.quickPayParameters.hashCode()) * 1000003) ^ (this.includeAirbnbCredit ? 1231 : 1237)) * 1000003;
        if (!this.userAgreedToCurrencyMismatch) {
            i = 1237;
        }
        int h2 = (((((h ^ i) * 1000003) ^ this.displayCurrency.hashCode()) * 1000003) ^ (this.zipRetry == null ? 0 : this.zipRetry.hashCode())) * 1000003;
        if (this.couponCode != null) {
            i2 = this.couponCode.hashCode();
        }
        return h2 ^ i2;
    }
}
