package com.airbnb.android.payout.requests;

import com.airbnb.android.core.models.AirAddress;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import java.util.Map;

final class AutoValue_CreatePayoutMethodRequestBody extends CreatePayoutMethodRequestBody {
    private final AirAddress accountAddress;
    private final Map<String, String> accountInputs;
    private final String payoutInfoFormType;
    private final String targetCurrency;

    static final class Builder extends com.airbnb.android.payout.requests.CreatePayoutMethodRequestBody.Builder {
        private AirAddress accountAddress;
        private Map<String, String> accountInputs;
        private String payoutInfoFormType;
        private String targetCurrency;

        Builder() {
        }

        public com.airbnb.android.payout.requests.CreatePayoutMethodRequestBody.Builder accountInputs(Map<String, String> accountInputs2) {
            this.accountInputs = accountInputs2;
            return this;
        }

        public com.airbnb.android.payout.requests.CreatePayoutMethodRequestBody.Builder accountAddress(AirAddress accountAddress2) {
            if (accountAddress2 == null) {
                throw new NullPointerException("Null accountAddress");
            }
            this.accountAddress = accountAddress2;
            return this;
        }

        public com.airbnb.android.payout.requests.CreatePayoutMethodRequestBody.Builder payoutInfoFormType(String payoutInfoFormType2) {
            if (payoutInfoFormType2 == null) {
                throw new NullPointerException("Null payoutInfoFormType");
            }
            this.payoutInfoFormType = payoutInfoFormType2;
            return this;
        }

        public com.airbnb.android.payout.requests.CreatePayoutMethodRequestBody.Builder targetCurrency(String targetCurrency2) {
            if (targetCurrency2 == null) {
                throw new NullPointerException("Null targetCurrency");
            }
            this.targetCurrency = targetCurrency2;
            return this;
        }

        public CreatePayoutMethodRequestBody build() {
            String missing = "";
            if (this.accountAddress == null) {
                missing = missing + " accountAddress";
            }
            if (this.payoutInfoFormType == null) {
                missing = missing + " payoutInfoFormType";
            }
            if (this.targetCurrency == null) {
                missing = missing + " targetCurrency";
            }
            if (missing.isEmpty()) {
                return new AutoValue_CreatePayoutMethodRequestBody(this.accountInputs, this.accountAddress, this.payoutInfoFormType, this.targetCurrency);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    private AutoValue_CreatePayoutMethodRequestBody(Map<String, String> accountInputs2, AirAddress accountAddress2, String payoutInfoFormType2, String targetCurrency2) {
        this.accountInputs = accountInputs2;
        this.accountAddress = accountAddress2;
        this.payoutInfoFormType = payoutInfoFormType2;
        this.targetCurrency = targetCurrency2;
    }

    @JsonAnyGetter
    public Map<String, String> accountInputs() {
        return this.accountInputs;
    }

    @JsonUnwrapped
    public AirAddress accountAddress() {
        return this.accountAddress;
    }

    @JsonProperty("type")
    public String payoutInfoFormType() {
        return this.payoutInfoFormType;
    }

    @JsonProperty("target_currency")
    public String targetCurrency() {
        return this.targetCurrency;
    }

    public String toString() {
        return "CreatePayoutMethodRequestBody{accountInputs=" + this.accountInputs + ", accountAddress=" + this.accountAddress + ", payoutInfoFormType=" + this.payoutInfoFormType + ", targetCurrency=" + this.targetCurrency + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CreatePayoutMethodRequestBody)) {
            return false;
        }
        CreatePayoutMethodRequestBody that = (CreatePayoutMethodRequestBody) o;
        if (this.accountInputs != null ? this.accountInputs.equals(that.accountInputs()) : that.accountInputs() == null) {
            if (this.accountAddress.equals(that.accountAddress()) && this.payoutInfoFormType.equals(that.payoutInfoFormType()) && this.targetCurrency.equals(that.targetCurrency())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (((((((1 * 1000003) ^ (this.accountInputs == null ? 0 : this.accountInputs.hashCode())) * 1000003) ^ this.accountAddress.hashCode()) * 1000003) ^ this.payoutInfoFormType.hashCode()) * 1000003) ^ this.targetCurrency.hashCode();
    }
}
