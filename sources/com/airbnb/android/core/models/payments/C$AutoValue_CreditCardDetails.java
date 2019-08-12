package com.airbnb.android.core.models.payments;

/* renamed from: com.airbnb.android.core.models.payments.$AutoValue_CreditCardDetails reason: invalid class name */
abstract class C$AutoValue_CreditCardDetails extends CreditCardDetails {
    private final String cardNumber;
    private final String countryCode;
    private final String cvv;
    private final String expirationMonth;
    private final String expirationYear;
    private final String postalCode;

    /* renamed from: com.airbnb.android.core.models.payments.$AutoValue_CreditCardDetails$Builder */
    static final class Builder extends com.airbnb.android.core.models.payments.CreditCardDetails.Builder {
        private String cardNumber;
        private String countryCode;
        private String cvv;
        private String expirationMonth;
        private String expirationYear;
        private String postalCode;

        Builder() {
        }

        private Builder(CreditCardDetails source) {
            this.countryCode = source.countryCode();
            this.cardNumber = source.cardNumber();
            this.expirationMonth = source.expirationMonth();
            this.expirationYear = source.expirationYear();
            this.cvv = source.cvv();
            this.postalCode = source.postalCode();
        }

        public com.airbnb.android.core.models.payments.CreditCardDetails.Builder countryCode(String countryCode2) {
            this.countryCode = countryCode2;
            return this;
        }

        public com.airbnb.android.core.models.payments.CreditCardDetails.Builder cardNumber(String cardNumber2) {
            this.cardNumber = cardNumber2;
            return this;
        }

        public com.airbnb.android.core.models.payments.CreditCardDetails.Builder expirationMonth(String expirationMonth2) {
            this.expirationMonth = expirationMonth2;
            return this;
        }

        public com.airbnb.android.core.models.payments.CreditCardDetails.Builder expirationYear(String expirationYear2) {
            this.expirationYear = expirationYear2;
            return this;
        }

        public com.airbnb.android.core.models.payments.CreditCardDetails.Builder cvv(String cvv2) {
            this.cvv = cvv2;
            return this;
        }

        public com.airbnb.android.core.models.payments.CreditCardDetails.Builder postalCode(String postalCode2) {
            this.postalCode = postalCode2;
            return this;
        }

        public CreditCardDetails build() {
            return new AutoValue_CreditCardDetails(this.countryCode, this.cardNumber, this.expirationMonth, this.expirationYear, this.cvv, this.postalCode);
        }
    }

    C$AutoValue_CreditCardDetails(String countryCode2, String cardNumber2, String expirationMonth2, String expirationYear2, String cvv2, String postalCode2) {
        this.countryCode = countryCode2;
        this.cardNumber = cardNumber2;
        this.expirationMonth = expirationMonth2;
        this.expirationYear = expirationYear2;
        this.cvv = cvv2;
        this.postalCode = postalCode2;
    }

    public String countryCode() {
        return this.countryCode;
    }

    public String cardNumber() {
        return this.cardNumber;
    }

    public String expirationMonth() {
        return this.expirationMonth;
    }

    public String expirationYear() {
        return this.expirationYear;
    }

    public String cvv() {
        return this.cvv;
    }

    public String postalCode() {
        return this.postalCode;
    }

    public String toString() {
        return "CreditCardDetails{countryCode=" + this.countryCode + ", cardNumber=" + this.cardNumber + ", expirationMonth=" + this.expirationMonth + ", expirationYear=" + this.expirationYear + ", cvv=" + this.cvv + ", postalCode=" + this.postalCode + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CreditCardDetails)) {
            return false;
        }
        CreditCardDetails that = (CreditCardDetails) o;
        if (this.countryCode != null ? this.countryCode.equals(that.countryCode()) : that.countryCode() == null) {
            if (this.cardNumber != null ? this.cardNumber.equals(that.cardNumber()) : that.cardNumber() == null) {
                if (this.expirationMonth != null ? this.expirationMonth.equals(that.expirationMonth()) : that.expirationMonth() == null) {
                    if (this.expirationYear != null ? this.expirationYear.equals(that.expirationYear()) : that.expirationYear() == null) {
                        if (this.cvv != null ? this.cvv.equals(that.cvv()) : that.cvv() == null) {
                            if (this.postalCode == null) {
                                if (that.postalCode() == null) {
                                    return true;
                                }
                            } else if (this.postalCode.equals(that.postalCode())) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((((((1 * 1000003) ^ (this.countryCode == null ? 0 : this.countryCode.hashCode())) * 1000003) ^ (this.cardNumber == null ? 0 : this.cardNumber.hashCode())) * 1000003) ^ (this.expirationMonth == null ? 0 : this.expirationMonth.hashCode())) * 1000003) ^ (this.expirationYear == null ? 0 : this.expirationYear.hashCode())) * 1000003) ^ (this.cvv == null ? 0 : this.cvv.hashCode())) * 1000003;
        if (this.postalCode != null) {
            i = this.postalCode.hashCode();
        }
        return h ^ i;
    }

    public com.airbnb.android.core.models.payments.CreditCardDetails.Builder toBuilder() {
        return new Builder(this);
    }
}
