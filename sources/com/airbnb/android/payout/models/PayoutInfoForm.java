package com.airbnb.android.payout.models;

import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.collect.FluentIterable;
import java.util.List;

@JsonDeserialize(builder = Builder.class)
public abstract class PayoutInfoForm implements Parcelable {

    public static abstract class Builder {
        public abstract PayoutInfoForm build();

        @JsonProperty("currencies")
        public abstract Builder currencies(List<String> list);

        @JsonProperty("payout_method_display_text")
        public abstract Builder displayName(String str);

        @JsonProperty("extra_payout_attributes")
        public abstract Builder extraPayoutAttributes(List<ExtraPayoutAttribute> list);

        @JsonProperty("payout_field_infos")
        public abstract Builder payoutFormFields(List<PayoutFormField> list);

        @JsonProperty("info_type")
        public abstract Builder payoutMethodType(PayoutInfoFormType payoutInfoFormType);

        @JsonProperty("processing_time_text")
        public abstract Builder timelinessInfo(String str);

        @JsonProperty("processing_fees_text")
        public abstract Builder transactionFeeInfo(String str);
    }

    @JsonProperty("currencies")
    public abstract List<String> currencies();

    @JsonProperty("payout_method_display_text")
    public abstract String displayName();

    @JsonProperty("extra_payout_attributes")
    public abstract List<ExtraPayoutAttribute> extraPayoutAttributes();

    @JsonProperty("payout_field_infos")
    public abstract List<PayoutFormField> payoutFormFields();

    @JsonProperty("info_type")
    public abstract PayoutInfoFormType payoutMethodType();

    @JsonProperty("processing_time_text")
    public abstract String timelinessInfo();

    @JsonProperty("processing_fees_text")
    public abstract String transactionFeeInfo();

    public static Builder builder() {
        return new Builder();
    }

    public boolean isBankDeposit() {
        return PayoutInfoFormType.getBankDepositPayoutMethodTypes().contains(payoutMethodType());
    }

    public boolean shouldShowAccountType() {
        return extraPayoutAttributes() != null && FluentIterable.from((Iterable<E>) extraPayoutAttributes()).anyMatch(PayoutInfoForm$$Lambda$1.lambdaFactory$());
    }

    static /* synthetic */ boolean lambda$shouldShowAccountType$0(ExtraPayoutAttribute attribute) {
        return attribute.getExtraPayoutAttributeType().equals(ExtraPayoutAttributeType.SHOW_ACCOUNT_TYPE) && attribute.getBooleanValue();
    }

    public boolean isURLRedirect() {
        return PayoutInfoFormType.getRedirectPayoutMethodTypes().contains(payoutMethodType());
    }
}
