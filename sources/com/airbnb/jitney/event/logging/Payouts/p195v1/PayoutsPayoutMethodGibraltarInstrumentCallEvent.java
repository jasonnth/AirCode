package com.airbnb.jitney.event.logging.Payouts.p195v1;

import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.jitney.event.logging.GibraltarInstrumentResponse.p103v1.C2144GibraltarInstrumentResponse;
import com.airbnb.jitney.event.logging.PaymentInstrumentType.p183v1.C2504PaymentInstrumentType;
import com.airbnb.jitney.event.logging.PayoutMethodType.p194v1.C2549PayoutMethodType;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Payouts.v1.PayoutsPayoutMethodGibraltarInstrumentCallEvent */
public final class PayoutsPayoutMethodGibraltarInstrumentCallEvent implements Struct {
    public static final Adapter<PayoutsPayoutMethodGibraltarInstrumentCallEvent, Builder> ADAPTER = new PayoutsPayoutMethodGibraltarInstrumentCallEventAdapter();
    public final String billing_country;
    public final Context context;
    public final String currency;
    public final String event_name;
    public final String gibraltar_instrument_error_message;
    public final C2144GibraltarInstrumentResponse gibraltar_instrument_response;
    public final String gibraltar_instrument_token;
    public final C2504PaymentInstrumentType payment_instrument_type;
    public final C2549PayoutMethodType payout_method_type;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.Payouts.v1.PayoutsPayoutMethodGibraltarInstrumentCallEvent$Builder */
    public static final class Builder implements StructBuilder<PayoutsPayoutMethodGibraltarInstrumentCallEvent> {
        /* access modifiers changed from: private */
        public String billing_country;
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String currency;
        /* access modifiers changed from: private */
        public String event_name = "payouts_payout_method_gibraltar_instrument_call";
        /* access modifiers changed from: private */
        public String gibraltar_instrument_error_message;
        /* access modifiers changed from: private */
        public C2144GibraltarInstrumentResponse gibraltar_instrument_response;
        /* access modifiers changed from: private */
        public String gibraltar_instrument_token;
        /* access modifiers changed from: private */
        public C2504PaymentInstrumentType payment_instrument_type;
        /* access modifiers changed from: private */
        public C2549PayoutMethodType payout_method_type;
        /* access modifiers changed from: private */
        public String schema = "com.airbnb.jitney.event.logging.Payouts:PayoutsPayoutMethodGibraltarInstrumentCallEvent:1.0.0";

        private Builder() {
        }

        public Builder(Context context2, String currency2, C2549PayoutMethodType payout_method_type2, C2144GibraltarInstrumentResponse gibraltar_instrument_response2) {
            this.context = context2;
            this.currency = currency2;
            this.payout_method_type = payout_method_type2;
            this.gibraltar_instrument_response = gibraltar_instrument_response2;
        }

        public PayoutsPayoutMethodGibraltarInstrumentCallEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.currency == null) {
                throw new IllegalStateException("Required field 'currency' is missing");
            } else if (this.payout_method_type == null) {
                throw new IllegalStateException("Required field 'payout_method_type' is missing");
            } else if (this.gibraltar_instrument_response != null) {
                return new PayoutsPayoutMethodGibraltarInstrumentCallEvent(this);
            } else {
                throw new IllegalStateException("Required field 'gibraltar_instrument_response' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Payouts.v1.PayoutsPayoutMethodGibraltarInstrumentCallEvent$PayoutsPayoutMethodGibraltarInstrumentCallEventAdapter */
    private static final class PayoutsPayoutMethodGibraltarInstrumentCallEventAdapter implements Adapter<PayoutsPayoutMethodGibraltarInstrumentCallEvent, Builder> {
        private PayoutsPayoutMethodGibraltarInstrumentCallEventAdapter() {
        }

        public void write(Protocol protocol, PayoutsPayoutMethodGibraltarInstrumentCallEvent struct) throws IOException {
            protocol.writeStructBegin("PayoutsPayoutMethodGibraltarInstrumentCallEvent");
            if (struct.schema != null) {
                protocol.writeFieldBegin("schema", 31337, PassportService.SF_DG11);
                protocol.writeString(struct.schema);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin("event_name", 1, PassportService.SF_DG11);
            protocol.writeString(struct.event_name);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(PlaceFields.CONTEXT, 2, PassportService.SF_DG12);
            Context.ADAPTER.write(protocol, struct.context);
            protocol.writeFieldEnd();
            if (struct.billing_country != null) {
                protocol.writeFieldBegin("billing_country", 3, PassportService.SF_DG11);
                protocol.writeString(struct.billing_country);
                protocol.writeFieldEnd();
            }
            if (struct.payment_instrument_type != null) {
                protocol.writeFieldBegin("payment_instrument_type", 4, 8);
                protocol.writeI32(struct.payment_instrument_type.value);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin(AirbnbConstants.PREFS_CURRENCY, 5, PassportService.SF_DG11);
            protocol.writeString(struct.currency);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("payout_method_type", 6, 8);
            protocol.writeI32(struct.payout_method_type.value);
            protocol.writeFieldEnd();
            if (struct.gibraltar_instrument_token != null) {
                protocol.writeFieldBegin("gibraltar_instrument_token", 7, PassportService.SF_DG11);
                protocol.writeString(struct.gibraltar_instrument_token);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin("gibraltar_instrument_response", 8, 8);
            protocol.writeI32(struct.gibraltar_instrument_response.value);
            protocol.writeFieldEnd();
            if (struct.gibraltar_instrument_error_message != null) {
                protocol.writeFieldBegin("gibraltar_instrument_error_message", 9, PassportService.SF_DG11);
                protocol.writeString(struct.gibraltar_instrument_error_message);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private PayoutsPayoutMethodGibraltarInstrumentCallEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.billing_country = builder.billing_country;
        this.payment_instrument_type = builder.payment_instrument_type;
        this.currency = builder.currency;
        this.payout_method_type = builder.payout_method_type;
        this.gibraltar_instrument_token = builder.gibraltar_instrument_token;
        this.gibraltar_instrument_response = builder.gibraltar_instrument_response;
        this.gibraltar_instrument_error_message = builder.gibraltar_instrument_error_message;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof PayoutsPayoutMethodGibraltarInstrumentCallEvent)) {
            return false;
        }
        PayoutsPayoutMethodGibraltarInstrumentCallEvent that = (PayoutsPayoutMethodGibraltarInstrumentCallEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.billing_country == that.billing_country || (this.billing_country != null && this.billing_country.equals(that.billing_country))) && ((this.payment_instrument_type == that.payment_instrument_type || (this.payment_instrument_type != null && this.payment_instrument_type.equals(that.payment_instrument_type))) && ((this.currency == that.currency || this.currency.equals(that.currency)) && ((this.payout_method_type == that.payout_method_type || this.payout_method_type.equals(that.payout_method_type)) && ((this.gibraltar_instrument_token == that.gibraltar_instrument_token || (this.gibraltar_instrument_token != null && this.gibraltar_instrument_token.equals(that.gibraltar_instrument_token))) && (this.gibraltar_instrument_response == that.gibraltar_instrument_response || this.gibraltar_instrument_response.equals(that.gibraltar_instrument_response)))))))))) {
            if (this.gibraltar_instrument_error_message == that.gibraltar_instrument_error_message) {
                return true;
            }
            if (this.gibraltar_instrument_error_message != null && this.gibraltar_instrument_error_message.equals(that.gibraltar_instrument_error_message)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ (this.billing_country == null ? 0 : this.billing_country.hashCode())) * -2128831035) ^ (this.payment_instrument_type == null ? 0 : this.payment_instrument_type.hashCode())) * -2128831035) ^ this.currency.hashCode()) * -2128831035) ^ this.payout_method_type.hashCode()) * -2128831035) ^ (this.gibraltar_instrument_token == null ? 0 : this.gibraltar_instrument_token.hashCode())) * -2128831035) ^ this.gibraltar_instrument_response.hashCode()) * -2128831035;
        if (this.gibraltar_instrument_error_message != null) {
            i = this.gibraltar_instrument_error_message.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "PayoutsPayoutMethodGibraltarInstrumentCallEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", billing_country=" + this.billing_country + ", payment_instrument_type=" + this.payment_instrument_type + ", currency=" + this.currency + ", payout_method_type=" + this.payout_method_type + ", gibraltar_instrument_token=" + this.gibraltar_instrument_token + ", gibraltar_instrument_response=" + this.gibraltar_instrument_response + ", gibraltar_instrument_error_message=" + this.gibraltar_instrument_error_message + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
