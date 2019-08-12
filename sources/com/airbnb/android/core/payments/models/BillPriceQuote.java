package com.airbnb.android.core.payments.models;

import android.os.Parcelable;
import com.airbnb.android.core.models.CurrencyAmount;
import com.airbnb.android.core.models.Price;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;

@JsonDeserialize(builder = Builder.class)
public abstract class BillPriceQuote implements Parcelable {

    public static abstract class Builder {
        public abstract BillPriceQuote build();

        @JsonProperty("applicable_airbnb_credit")
        public abstract Builder setApplicableAirbnbCredit(CurrencyAmount currencyAmount);

        @JsonProperty("cancellation_info")
        public abstract Builder setCancellationInfo(CancellationInfo cancellationInfo);

        @JsonProperty("cancellation_refund_policy")
        public abstract Builder setCancellationRefundPolicy(LinkableLegalText linkableLegalText);

        @JsonProperty("fx_message")
        public abstract Builder setFxMessage(String str);

        @JsonProperty("installments")
        public abstract Builder setInstallments(List<Price> list);

        @JsonProperty("price")
        public abstract Builder setPrice(Price price);

        @JsonProperty("quote_key")
        public abstract Builder setQuoteKey(String str);

        @JsonProperty("terms_and_conditions")
        public abstract Builder setTermsAndConditions(LinkableLegalText linkableLegalText);
    }

    @JsonDeserialize(builder = Builder.class)
    public static abstract class CancellationInfo implements Parcelable {

        public static abstract class Builder {
            public abstract CancellationInfo build();

            @JsonProperty("subtitles")
            public abstract Builder subtitles(List<String> list);

            @JsonProperty("title")
            public abstract Builder title(String str);
        }

        public abstract List<String> subtitles();

        public abstract String title();

        public static Builder builder() {
            return new Builder();
        }
    }

    @JsonDeserialize(builder = Builder.class)
    public static abstract class Link implements Parcelable {

        public static abstract class Builder {
            public abstract Link build();

            @JsonProperty("text")
            public abstract Builder text(String str);

            @JsonProperty("url")
            public abstract Builder url(String str);
        }

        public abstract String text();

        public abstract String url();

        public static Builder builder() {
            return new Builder();
        }
    }

    @JsonDeserialize(builder = Builder.class)
    public static abstract class LinkableLegalText implements Parcelable {

        public static abstract class Builder {
            public abstract LinkableLegalText build();

            @JsonProperty("links")
            public abstract Builder links(List<Link> list);

            @JsonProperty("text")
            public abstract Builder text(String str);

            @JsonProperty("title")
            public abstract Builder title(String str);
        }

        public abstract List<Link> links();

        public abstract String text();

        public abstract String title();

        public static Builder builder() {
            return new Builder();
        }
    }

    public abstract CurrencyAmount getApplicableAirbnbCredit();

    public abstract CancellationInfo getCancellationInfo();

    public abstract LinkableLegalText getCancellationRefundPolicy();

    public abstract String getFxMessage();

    public abstract List<Price> getInstallments();

    public abstract Price getPrice();

    public abstract String getQuoteKey();

    public abstract LinkableLegalText getTermsAndConditions();

    public static Builder builder() {
        return new Builder();
    }
}
