package com.airbnb.jitney.event.logging.Payouts.p197v3;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.PaymentInstrumentType.p183v1.C2504PaymentInstrumentType;
import com.airbnb.jitney.event.logging.PayoutMethodError.p191v1.C2546PayoutMethodError;
import com.airbnb.jitney.event.logging.PayoutMethodType.p194v1.C2549PayoutMethodType;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Payouts.v3.PayoutsPayoutMethodErrorNativeEvent */
public final class PayoutsPayoutMethodErrorNativeEvent implements Struct {
    public static final Adapter<PayoutsPayoutMethodErrorNativeEvent, Builder> ADAPTER = new PayoutsPayoutMethodErrorNativeEventAdapter();
    public final String billing_country;
    public final Context context;
    public final String currency;
    public final String event_name;
    public final C2451Operation operation;
    public final C2504PaymentInstrumentType payment_instrument_type;
    public final C2546PayoutMethodError payout_method_error;
    public final String payout_method_setup_section;
    public final C2549PayoutMethodType payout_method_type;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.Payouts.v3.PayoutsPayoutMethodErrorNativeEvent$Builder */
    public static final class Builder implements StructBuilder<PayoutsPayoutMethodErrorNativeEvent> {
        /* access modifiers changed from: private */
        public String billing_country;
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String currency;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public C2504PaymentInstrumentType payment_instrument_type;
        /* access modifiers changed from: private */
        public C2546PayoutMethodError payout_method_error;
        /* access modifiers changed from: private */
        public String payout_method_setup_section;
        /* access modifiers changed from: private */
        public C2549PayoutMethodType payout_method_type;
        /* access modifiers changed from: private */
        public String schema;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Payouts:PayoutsPayoutMethodErrorNativeEvent:3.0.0";
            this.event_name = "payouts_payout_method_error_native";
            this.operation = C2451Operation.Impression;
        }

        public Builder(Context context2, C2546PayoutMethodError payout_method_error2, String payout_method_setup_section2, C2549PayoutMethodType payout_method_type2, String billing_country2, String currency2) {
            this.schema = "com.airbnb.jitney.event.logging.Payouts:PayoutsPayoutMethodErrorNativeEvent:3.0.0";
            this.event_name = "payouts_payout_method_error_native";
            this.context = context2;
            this.payout_method_error = payout_method_error2;
            this.payout_method_setup_section = payout_method_setup_section2;
            this.payout_method_type = payout_method_type2;
            this.billing_country = billing_country2;
            this.currency = currency2;
            this.operation = C2451Operation.Impression;
        }

        public PayoutsPayoutMethodErrorNativeEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.payout_method_error == null) {
                throw new IllegalStateException("Required field 'payout_method_error' is missing");
            } else if (this.payout_method_setup_section == null) {
                throw new IllegalStateException("Required field 'payout_method_setup_section' is missing");
            } else if (this.payout_method_type == null) {
                throw new IllegalStateException("Required field 'payout_method_type' is missing");
            } else if (this.billing_country == null) {
                throw new IllegalStateException("Required field 'billing_country' is missing");
            } else if (this.currency == null) {
                throw new IllegalStateException("Required field 'currency' is missing");
            } else if (this.operation != null) {
                return new PayoutsPayoutMethodErrorNativeEvent(this);
            } else {
                throw new IllegalStateException("Required field 'operation' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Payouts.v3.PayoutsPayoutMethodErrorNativeEvent$PayoutsPayoutMethodErrorNativeEventAdapter */
    private static final class PayoutsPayoutMethodErrorNativeEventAdapter implements Adapter<PayoutsPayoutMethodErrorNativeEvent, Builder> {
        private PayoutsPayoutMethodErrorNativeEventAdapter() {
        }

        public void write(Protocol protocol, PayoutsPayoutMethodErrorNativeEvent struct) throws IOException {
            protocol.writeStructBegin("PayoutsPayoutMethodErrorNativeEvent");
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
            protocol.writeFieldBegin("payout_method_error", 3, 8);
            protocol.writeI32(struct.payout_method_error.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("payout_method_setup_section", 4, PassportService.SF_DG11);
            protocol.writeString(struct.payout_method_setup_section);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("payout_method_type", 5, 8);
            protocol.writeI32(struct.payout_method_type.value);
            protocol.writeFieldEnd();
            if (struct.payment_instrument_type != null) {
                protocol.writeFieldBegin("payment_instrument_type", 6, 8);
                protocol.writeI32(struct.payment_instrument_type.value);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin("billing_country", 7, PassportService.SF_DG11);
            protocol.writeString(struct.billing_country);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(AirbnbConstants.PREFS_CURRENCY, 8, PassportService.SF_DG11);
            protocol.writeString(struct.currency);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 9, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private PayoutsPayoutMethodErrorNativeEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.payout_method_error = builder.payout_method_error;
        this.payout_method_setup_section = builder.payout_method_setup_section;
        this.payout_method_type = builder.payout_method_type;
        this.payment_instrument_type = builder.payment_instrument_type;
        this.billing_country = builder.billing_country;
        this.currency = builder.currency;
        this.operation = builder.operation;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof PayoutsPayoutMethodErrorNativeEvent)) {
            return false;
        }
        PayoutsPayoutMethodErrorNativeEvent that = (PayoutsPayoutMethodErrorNativeEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.payout_method_error == that.payout_method_error || this.payout_method_error.equals(that.payout_method_error)) && ((this.payout_method_setup_section == that.payout_method_setup_section || this.payout_method_setup_section.equals(that.payout_method_setup_section)) && ((this.payout_method_type == that.payout_method_type || this.payout_method_type.equals(that.payout_method_type)) && ((this.payment_instrument_type == that.payment_instrument_type || (this.payment_instrument_type != null && this.payment_instrument_type.equals(that.payment_instrument_type))) && ((this.billing_country == that.billing_country || this.billing_country.equals(that.billing_country)) && ((this.currency == that.currency || this.currency.equals(that.currency)) && (this.operation == that.operation || this.operation.equals(that.operation))))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (this.schema == null) {
            hashCode = 0;
        } else {
            hashCode = this.schema.hashCode();
        }
        int code = (((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.payout_method_error.hashCode()) * -2128831035) ^ this.payout_method_setup_section.hashCode()) * -2128831035) ^ this.payout_method_type.hashCode()) * -2128831035;
        if (this.payment_instrument_type != null) {
            i = this.payment_instrument_type.hashCode();
        }
        return (((((((code ^ i) * -2128831035) ^ this.billing_country.hashCode()) * -2128831035) ^ this.currency.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035;
    }

    public String toString() {
        return "PayoutsPayoutMethodErrorNativeEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", payout_method_error=" + this.payout_method_error + ", payout_method_setup_section=" + this.payout_method_setup_section + ", payout_method_type=" + this.payout_method_type + ", payment_instrument_type=" + this.payment_instrument_type + ", billing_country=" + this.billing_country + ", currency=" + this.currency + ", operation=" + this.operation + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
