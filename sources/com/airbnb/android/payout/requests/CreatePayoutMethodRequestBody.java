package com.airbnb.android.payout.requests;

import com.airbnb.android.core.models.AirAddress;
import com.airbnb.android.payout.models.PayoutFormFieldInputWrapper;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;

public abstract class CreatePayoutMethodRequestBody {

    public static abstract class Builder {
        @JsonUnwrapped
        public abstract Builder accountAddress(AirAddress airAddress);

        @JsonAnyGetter
        public abstract Builder accountInputs(Map<String, String> map);

        public abstract CreatePayoutMethodRequestBody build();

        @JsonProperty("type")
        public abstract Builder payoutInfoFormType(String str);

        @JsonProperty("target_currency")
        public abstract Builder targetCurrency(String str);
    }

    @JsonUnwrapped
    public abstract AirAddress accountAddress();

    @JsonAnyGetter
    public abstract Map<String, String> accountInputs();

    @JsonProperty("type")
    public abstract String payoutInfoFormType();

    @JsonProperty("target_currency")
    public abstract String targetCurrency();

    public static Builder builder() {
        return new Builder();
    }

    public static Map<String, String> getAccountInputs(List<PayoutFormFieldInputWrapper> payoutFormFieldInputWrappers) {
        Map<String, String> accountInputs = Maps.newHashMap();
        for (PayoutFormFieldInputWrapper wrapper : payoutFormFieldInputWrappers) {
            if (!wrapper.payoutFormField().confirmField()) {
                accountInputs.put(wrapper.payoutFormField().name(), wrapper.inputValue());
            }
        }
        return accountInputs;
    }
}
