package com.airbnb.android.core.payments.models.clientparameters;

import com.airbnb.android.core.models.CurrencyAmount;
import com.airbnb.android.core.payments.models.BillProductConstants;
import com.airbnb.android.core.payments.models.BillProductType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = Builder.class)
public abstract class GiftCardClientParameters extends QuickPayParameters {

    public static abstract class Builder {
        /* access modifiers changed from: 0000 */
        public abstract GiftCardClientParameters autoBuild();

        @JsonProperty("category_type")
        public abstract Builder categoryType(String str);

        @JsonProperty("gift_amount")
        public abstract Builder giftCreditCurrencyAmount(CurrencyAmount currencyAmount);

        @JsonProperty("locale")
        public abstract Builder locale(String str);

        @JsonProperty("overlay_id")
        public abstract Builder overlayId(long j);

        /* access modifiers changed from: 0000 */
        public abstract Builder productType(BillProductType billProductType);

        @JsonProperty("recipient_email")
        public abstract Builder recipientEmail(String str);

        @JsonProperty("recipient_message")
        public abstract Builder recipientMessage(String str);

        @JsonProperty("recipient_name")
        public abstract Builder recipientName(String str);

        @JsonProperty("video_id")
        public abstract Builder videoId(long j);

        public GiftCardClientParameters build() {
            productType(BillProductType.GiftCredit);
            return autoBuild();
        }
    }

    public abstract String categoryType();

    public abstract CurrencyAmount giftCreditCurrencyAmount();

    public abstract String locale();

    public abstract long overlayId();

    public abstract String recipientEmail();

    public abstract String recipientMessage();

    public abstract String recipientName();

    public abstract long videoId();

    public static Builder builder() {
        return new Builder();
    }

    public long getAmountMicros() {
        return giftCreditCurrencyAmount().getAmount().multiply(BillProductConstants.ONE_MILLION).longValue();
    }
}
