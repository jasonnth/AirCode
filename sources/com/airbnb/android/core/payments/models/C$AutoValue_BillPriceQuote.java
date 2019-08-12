package com.airbnb.android.core.payments.models;

import com.airbnb.android.core.models.CurrencyAmount;
import com.airbnb.android.core.models.Price;
import com.airbnb.android.core.payments.models.BillPriceQuote.CancellationInfo;
import com.airbnb.android.core.payments.models.BillPriceQuote.LinkableLegalText;
import java.util.List;

/* renamed from: com.airbnb.android.core.payments.models.$AutoValue_BillPriceQuote reason: invalid class name */
abstract class C$AutoValue_BillPriceQuote extends BillPriceQuote {
    private final CurrencyAmount applicableAirbnbCredit;
    private final CancellationInfo cancellationInfo;
    private final LinkableLegalText cancellationRefundPolicy;
    private final String fxMessage;
    private final List<Price> installments;
    private final Price price;
    private final String quoteKey;
    private final LinkableLegalText termsAndConditions;

    /* renamed from: com.airbnb.android.core.payments.models.$AutoValue_BillPriceQuote$Builder */
    static final class Builder extends com.airbnb.android.core.payments.models.BillPriceQuote.Builder {
        private CurrencyAmount applicableAirbnbCredit;
        private CancellationInfo cancellationInfo;
        private LinkableLegalText cancellationRefundPolicy;
        private String fxMessage;
        private List<Price> installments;
        private Price price;
        private String quoteKey;
        private LinkableLegalText termsAndConditions;

        Builder() {
        }

        public com.airbnb.android.core.payments.models.BillPriceQuote.Builder setApplicableAirbnbCredit(CurrencyAmount applicableAirbnbCredit2) {
            if (applicableAirbnbCredit2 == null) {
                throw new NullPointerException("Null applicableAirbnbCredit");
            }
            this.applicableAirbnbCredit = applicableAirbnbCredit2;
            return this;
        }

        public com.airbnb.android.core.payments.models.BillPriceQuote.Builder setPrice(Price price2) {
            if (price2 == null) {
                throw new NullPointerException("Null price");
            }
            this.price = price2;
            return this;
        }

        public com.airbnb.android.core.payments.models.BillPriceQuote.Builder setQuoteKey(String quoteKey2) {
            if (quoteKey2 == null) {
                throw new NullPointerException("Null quoteKey");
            }
            this.quoteKey = quoteKey2;
            return this;
        }

        public com.airbnb.android.core.payments.models.BillPriceQuote.Builder setInstallments(List<Price> installments2) {
            if (installments2 == null) {
                throw new NullPointerException("Null installments");
            }
            this.installments = installments2;
            return this;
        }

        public com.airbnb.android.core.payments.models.BillPriceQuote.Builder setFxMessage(String fxMessage2) {
            this.fxMessage = fxMessage2;
            return this;
        }

        public com.airbnb.android.core.payments.models.BillPriceQuote.Builder setCancellationInfo(CancellationInfo cancellationInfo2) {
            this.cancellationInfo = cancellationInfo2;
            return this;
        }

        public com.airbnb.android.core.payments.models.BillPriceQuote.Builder setTermsAndConditions(LinkableLegalText termsAndConditions2) {
            this.termsAndConditions = termsAndConditions2;
            return this;
        }

        public com.airbnb.android.core.payments.models.BillPriceQuote.Builder setCancellationRefundPolicy(LinkableLegalText cancellationRefundPolicy2) {
            this.cancellationRefundPolicy = cancellationRefundPolicy2;
            return this;
        }

        public BillPriceQuote build() {
            String missing = "";
            if (this.applicableAirbnbCredit == null) {
                missing = missing + " applicableAirbnbCredit";
            }
            if (this.price == null) {
                missing = missing + " price";
            }
            if (this.quoteKey == null) {
                missing = missing + " quoteKey";
            }
            if (this.installments == null) {
                missing = missing + " installments";
            }
            if (missing.isEmpty()) {
                return new AutoValue_BillPriceQuote(this.applicableAirbnbCredit, this.price, this.quoteKey, this.installments, this.fxMessage, this.cancellationInfo, this.termsAndConditions, this.cancellationRefundPolicy);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_BillPriceQuote(CurrencyAmount applicableAirbnbCredit2, Price price2, String quoteKey2, List<Price> installments2, String fxMessage2, CancellationInfo cancellationInfo2, LinkableLegalText termsAndConditions2, LinkableLegalText cancellationRefundPolicy2) {
        if (applicableAirbnbCredit2 == null) {
            throw new NullPointerException("Null applicableAirbnbCredit");
        }
        this.applicableAirbnbCredit = applicableAirbnbCredit2;
        if (price2 == null) {
            throw new NullPointerException("Null price");
        }
        this.price = price2;
        if (quoteKey2 == null) {
            throw new NullPointerException("Null quoteKey");
        }
        this.quoteKey = quoteKey2;
        if (installments2 == null) {
            throw new NullPointerException("Null installments");
        }
        this.installments = installments2;
        this.fxMessage = fxMessage2;
        this.cancellationInfo = cancellationInfo2;
        this.termsAndConditions = termsAndConditions2;
        this.cancellationRefundPolicy = cancellationRefundPolicy2;
    }

    public CurrencyAmount getApplicableAirbnbCredit() {
        return this.applicableAirbnbCredit;
    }

    public Price getPrice() {
        return this.price;
    }

    public String getQuoteKey() {
        return this.quoteKey;
    }

    public List<Price> getInstallments() {
        return this.installments;
    }

    public String getFxMessage() {
        return this.fxMessage;
    }

    public CancellationInfo getCancellationInfo() {
        return this.cancellationInfo;
    }

    public LinkableLegalText getTermsAndConditions() {
        return this.termsAndConditions;
    }

    public LinkableLegalText getCancellationRefundPolicy() {
        return this.cancellationRefundPolicy;
    }

    public String toString() {
        return "BillPriceQuote{applicableAirbnbCredit=" + this.applicableAirbnbCredit + ", price=" + this.price + ", quoteKey=" + this.quoteKey + ", installments=" + this.installments + ", fxMessage=" + this.fxMessage + ", cancellationInfo=" + this.cancellationInfo + ", termsAndConditions=" + this.termsAndConditions + ", cancellationRefundPolicy=" + this.cancellationRefundPolicy + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BillPriceQuote)) {
            return false;
        }
        BillPriceQuote that = (BillPriceQuote) o;
        if (this.applicableAirbnbCredit.equals(that.getApplicableAirbnbCredit()) && this.price.equals(that.getPrice()) && this.quoteKey.equals(that.getQuoteKey()) && this.installments.equals(that.getInstallments()) && (this.fxMessage != null ? this.fxMessage.equals(that.getFxMessage()) : that.getFxMessage() == null) && (this.cancellationInfo != null ? this.cancellationInfo.equals(that.getCancellationInfo()) : that.getCancellationInfo() == null) && (this.termsAndConditions != null ? this.termsAndConditions.equals(that.getTermsAndConditions()) : that.getTermsAndConditions() == null)) {
            if (this.cancellationRefundPolicy == null) {
                if (that.getCancellationRefundPolicy() == null) {
                    return true;
                }
            } else if (this.cancellationRefundPolicy.equals(that.getCancellationRefundPolicy())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((((((((((1 * 1000003) ^ this.applicableAirbnbCredit.hashCode()) * 1000003) ^ this.price.hashCode()) * 1000003) ^ this.quoteKey.hashCode()) * 1000003) ^ this.installments.hashCode()) * 1000003) ^ (this.fxMessage == null ? 0 : this.fxMessage.hashCode())) * 1000003) ^ (this.cancellationInfo == null ? 0 : this.cancellationInfo.hashCode())) * 1000003) ^ (this.termsAndConditions == null ? 0 : this.termsAndConditions.hashCode())) * 1000003;
        if (this.cancellationRefundPolicy != null) {
            i = this.cancellationRefundPolicy.hashCode();
        }
        return h ^ i;
    }
}
