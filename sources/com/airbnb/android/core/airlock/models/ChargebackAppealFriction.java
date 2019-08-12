package com.airbnb.android.core.airlock.models;

import android.os.Bundle;
import android.os.Parcelable;
import com.airbnb.android.core.airlock.enums.AirlockStatus;
import com.airbnb.android.utils.BundleBuilder;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.miteksystems.misnap.params.MiSnapAPI;
import java.util.List;

@JsonDeserialize(builder = Builder.class)
public abstract class ChargebackAppealFriction extends BaseAirlockFriction implements Parcelable {

    public static abstract class Builder {
        @JsonProperty("airlock_status")
        public abstract Builder airlockStatus(AirlockStatus airlockStatus);

        public abstract ChargebackAppealFriction build();

        @JsonProperty("cc_last_four")
        public abstract Builder ccLastFourDigits(String str);

        @JsonProperty("cc_type")
        public abstract Builder ccType(String str);

        @JsonProperty("email")
        public abstract Builder emails(List<String> list);

        @JsonProperty("hosting_id")
        public abstract Builder hostingId(long j);

        @JsonProperty("hosting_name")
        public abstract Builder hostingName(String str);

        @JsonProperty("payment_instrument_id")
        public abstract Builder paymentInstrumentId(long j);

        @JsonProperty("reservation_id")
        public abstract Builder reservationId(long j);

        @JsonProperty("version")
        public abstract Builder version(double d);
    }

    public abstract AirlockStatus airlockStatus();

    public abstract String ccLastFourDigits();

    public abstract String ccType();

    public abstract List<String> emails();

    public abstract long hostingId();

    public abstract String hostingName();

    public abstract long paymentInstrumentId();

    public abstract long reservationId();

    public static Builder builder() {
        return new Builder();
    }

    public Bundle toBundle() {
        return ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) new BundleBuilder().putString("email", (String) emails().get(0))).putString(MiSnapAPI.CREDIT_CARD_TYPE, ccType())).putString("cc_last_four", ccLastFourDigits())).putInt("airlock_status", airlockStatus().getStatusCode())).putLong("reservation_id", reservationId())).putLong("payment_instrument_id", paymentInstrumentId())).toBundle();
    }
}
