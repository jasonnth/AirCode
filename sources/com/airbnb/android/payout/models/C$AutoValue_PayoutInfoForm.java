package com.airbnb.android.payout.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/* renamed from: com.airbnb.android.payout.models.$AutoValue_PayoutInfoForm reason: invalid class name */
abstract class C$AutoValue_PayoutInfoForm extends PayoutInfoForm {
    private final List<String> currencies;
    private final String displayName;
    private final List<ExtraPayoutAttribute> extraPayoutAttributes;
    private final List<PayoutFormField> payoutFormFields;
    private final PayoutInfoFormType payoutMethodType;
    private final String timelinessInfo;
    private final String transactionFeeInfo;

    /* renamed from: com.airbnb.android.payout.models.$AutoValue_PayoutInfoForm$Builder */
    static final class Builder extends com.airbnb.android.payout.models.PayoutInfoForm.Builder {
        private List<String> currencies;
        private String displayName;
        private List<ExtraPayoutAttribute> extraPayoutAttributes;
        private List<PayoutFormField> payoutFormFields;
        private PayoutInfoFormType payoutMethodType;
        private String timelinessInfo;
        private String transactionFeeInfo;

        Builder() {
        }

        public com.airbnb.android.payout.models.PayoutInfoForm.Builder displayName(String displayName2) {
            if (displayName2 == null) {
                throw new NullPointerException("Null displayName");
            }
            this.displayName = displayName2;
            return this;
        }

        public com.airbnb.android.payout.models.PayoutInfoForm.Builder timelinessInfo(String timelinessInfo2) {
            if (timelinessInfo2 == null) {
                throw new NullPointerException("Null timelinessInfo");
            }
            this.timelinessInfo = timelinessInfo2;
            return this;
        }

        public com.airbnb.android.payout.models.PayoutInfoForm.Builder transactionFeeInfo(String transactionFeeInfo2) {
            if (transactionFeeInfo2 == null) {
                throw new NullPointerException("Null transactionFeeInfo");
            }
            this.transactionFeeInfo = transactionFeeInfo2;
            return this;
        }

        public com.airbnb.android.payout.models.PayoutInfoForm.Builder currencies(List<String> currencies2) {
            if (currencies2 == null) {
                throw new NullPointerException("Null currencies");
            }
            this.currencies = currencies2;
            return this;
        }

        public com.airbnb.android.payout.models.PayoutInfoForm.Builder extraPayoutAttributes(List<ExtraPayoutAttribute> extraPayoutAttributes2) {
            this.extraPayoutAttributes = extraPayoutAttributes2;
            return this;
        }

        public com.airbnb.android.payout.models.PayoutInfoForm.Builder payoutFormFields(List<PayoutFormField> payoutFormFields2) {
            this.payoutFormFields = payoutFormFields2;
            return this;
        }

        public com.airbnb.android.payout.models.PayoutInfoForm.Builder payoutMethodType(PayoutInfoFormType payoutMethodType2) {
            if (payoutMethodType2 == null) {
                throw new NullPointerException("Null payoutMethodType");
            }
            this.payoutMethodType = payoutMethodType2;
            return this;
        }

        public PayoutInfoForm build() {
            String missing = "";
            if (this.displayName == null) {
                missing = missing + " displayName";
            }
            if (this.timelinessInfo == null) {
                missing = missing + " timelinessInfo";
            }
            if (this.transactionFeeInfo == null) {
                missing = missing + " transactionFeeInfo";
            }
            if (this.currencies == null) {
                missing = missing + " currencies";
            }
            if (this.payoutMethodType == null) {
                missing = missing + " payoutMethodType";
            }
            if (missing.isEmpty()) {
                return new AutoValue_PayoutInfoForm(this.displayName, this.timelinessInfo, this.transactionFeeInfo, this.currencies, this.extraPayoutAttributes, this.payoutFormFields, this.payoutMethodType);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_PayoutInfoForm(String displayName2, String timelinessInfo2, String transactionFeeInfo2, List<String> currencies2, List<ExtraPayoutAttribute> extraPayoutAttributes2, List<PayoutFormField> payoutFormFields2, PayoutInfoFormType payoutMethodType2) {
        if (displayName2 == null) {
            throw new NullPointerException("Null displayName");
        }
        this.displayName = displayName2;
        if (timelinessInfo2 == null) {
            throw new NullPointerException("Null timelinessInfo");
        }
        this.timelinessInfo = timelinessInfo2;
        if (transactionFeeInfo2 == null) {
            throw new NullPointerException("Null transactionFeeInfo");
        }
        this.transactionFeeInfo = transactionFeeInfo2;
        if (currencies2 == null) {
            throw new NullPointerException("Null currencies");
        }
        this.currencies = currencies2;
        this.extraPayoutAttributes = extraPayoutAttributes2;
        this.payoutFormFields = payoutFormFields2;
        if (payoutMethodType2 == null) {
            throw new NullPointerException("Null payoutMethodType");
        }
        this.payoutMethodType = payoutMethodType2;
    }

    @JsonProperty("payout_method_display_text")
    public String displayName() {
        return this.displayName;
    }

    @JsonProperty("processing_time_text")
    public String timelinessInfo() {
        return this.timelinessInfo;
    }

    @JsonProperty("processing_fees_text")
    public String transactionFeeInfo() {
        return this.transactionFeeInfo;
    }

    @JsonProperty("currencies")
    public List<String> currencies() {
        return this.currencies;
    }

    @JsonProperty("extra_payout_attributes")
    public List<ExtraPayoutAttribute> extraPayoutAttributes() {
        return this.extraPayoutAttributes;
    }

    @JsonProperty("payout_field_infos")
    public List<PayoutFormField> payoutFormFields() {
        return this.payoutFormFields;
    }

    @JsonProperty("info_type")
    public PayoutInfoFormType payoutMethodType() {
        return this.payoutMethodType;
    }

    public String toString() {
        return "PayoutInfoForm{displayName=" + this.displayName + ", timelinessInfo=" + this.timelinessInfo + ", transactionFeeInfo=" + this.transactionFeeInfo + ", currencies=" + this.currencies + ", extraPayoutAttributes=" + this.extraPayoutAttributes + ", payoutFormFields=" + this.payoutFormFields + ", payoutMethodType=" + this.payoutMethodType + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PayoutInfoForm)) {
            return false;
        }
        PayoutInfoForm that = (PayoutInfoForm) o;
        if (!this.displayName.equals(that.displayName()) || !this.timelinessInfo.equals(that.timelinessInfo()) || !this.transactionFeeInfo.equals(that.transactionFeeInfo()) || !this.currencies.equals(that.currencies()) || (this.extraPayoutAttributes != null ? !this.extraPayoutAttributes.equals(that.extraPayoutAttributes()) : that.extraPayoutAttributes() != null) || (this.payoutFormFields != null ? !this.payoutFormFields.equals(that.payoutFormFields()) : that.payoutFormFields() != null) || !this.payoutMethodType.equals(that.payoutMethodType())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((((((1 * 1000003) ^ this.displayName.hashCode()) * 1000003) ^ this.timelinessInfo.hashCode()) * 1000003) ^ this.transactionFeeInfo.hashCode()) * 1000003) ^ this.currencies.hashCode()) * 1000003) ^ (this.extraPayoutAttributes == null ? 0 : this.extraPayoutAttributes.hashCode())) * 1000003;
        if (this.payoutFormFields != null) {
            i = this.payoutFormFields.hashCode();
        }
        return ((h ^ i) * 1000003) ^ this.payoutMethodType.hashCode();
    }
}
