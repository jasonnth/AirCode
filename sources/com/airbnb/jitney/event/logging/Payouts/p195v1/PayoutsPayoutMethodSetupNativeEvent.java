package com.airbnb.jitney.event.logging.Payouts.p195v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.PaymentInstrumentType.p183v1.C2504PaymentInstrumentType;
import com.airbnb.jitney.event.logging.PayoutMethodAction.p190v1.C2545PayoutMethodAction;
import com.airbnb.jitney.event.logging.PayoutMethodSetupPage.p193v1.C2548PayoutMethodSetupPage;
import com.airbnb.jitney.event.logging.PayoutMethodType.p194v1.C2549PayoutMethodType;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Payouts.v1.PayoutsPayoutMethodSetupNativeEvent */
public final class PayoutsPayoutMethodSetupNativeEvent implements Struct {
    public static final Adapter<PayoutsPayoutMethodSetupNativeEvent, Builder> ADAPTER = new PayoutsPayoutMethodSetupNativeEventAdapter();
    public final String billing_country;
    public final Context context;
    public final String currency;
    public final String event_name;
    public final String gibraltar_instrument_token;
    public final C2451Operation operation;
    public final C2504PaymentInstrumentType payment_instrument_type;
    public final C2545PayoutMethodAction payout_method_action;
    public final C2548PayoutMethodSetupPage payout_method_setup_page;
    public final C2549PayoutMethodType payout_method_type;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.Payouts.v1.PayoutsPayoutMethodSetupNativeEvent$Builder */
    public static final class Builder implements StructBuilder<PayoutsPayoutMethodSetupNativeEvent> {
        /* access modifiers changed from: private */
        public String billing_country;
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String currency;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public String gibraltar_instrument_token;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public C2504PaymentInstrumentType payment_instrument_type;
        /* access modifiers changed from: private */
        public C2545PayoutMethodAction payout_method_action;
        /* access modifiers changed from: private */
        public C2548PayoutMethodSetupPage payout_method_setup_page;
        /* access modifiers changed from: private */
        public C2549PayoutMethodType payout_method_type;
        /* access modifiers changed from: private */
        public String schema;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Payouts:PayoutsPayoutMethodSetupNativeEvent:1.0.0";
            this.event_name = "payouts_payout_method_setup_native";
            this.operation = C2451Operation.Click;
        }

        public Builder(Context context2, String currency2, C2548PayoutMethodSetupPage payout_method_setup_page2, C2549PayoutMethodType payout_method_type2, C2545PayoutMethodAction payout_method_action2) {
            this.schema = "com.airbnb.jitney.event.logging.Payouts:PayoutsPayoutMethodSetupNativeEvent:1.0.0";
            this.event_name = "payouts_payout_method_setup_native";
            this.context = context2;
            this.currency = currency2;
            this.payout_method_setup_page = payout_method_setup_page2;
            this.payout_method_type = payout_method_type2;
            this.payout_method_action = payout_method_action2;
            this.operation = C2451Operation.Click;
        }

        public PayoutsPayoutMethodSetupNativeEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.currency == null) {
                throw new IllegalStateException("Required field 'currency' is missing");
            } else if (this.payout_method_setup_page == null) {
                throw new IllegalStateException("Required field 'payout_method_setup_page' is missing");
            } else if (this.payout_method_type == null) {
                throw new IllegalStateException("Required field 'payout_method_type' is missing");
            } else if (this.payout_method_action == null) {
                throw new IllegalStateException("Required field 'payout_method_action' is missing");
            } else if (this.operation != null) {
                return new PayoutsPayoutMethodSetupNativeEvent(this);
            } else {
                throw new IllegalStateException("Required field 'operation' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Payouts.v1.PayoutsPayoutMethodSetupNativeEvent$PayoutsPayoutMethodSetupNativeEventAdapter */
    private static final class PayoutsPayoutMethodSetupNativeEventAdapter implements Adapter<PayoutsPayoutMethodSetupNativeEvent, Builder> {
        private PayoutsPayoutMethodSetupNativeEventAdapter() {
        }

        public void write(Protocol protocol, PayoutsPayoutMethodSetupNativeEvent struct) throws IOException {
            protocol.writeStructBegin("PayoutsPayoutMethodSetupNativeEvent");
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
            protocol.writeFieldBegin("payout_method_setup_page", 6, 8);
            protocol.writeI32(struct.payout_method_setup_page.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("payout_method_type", 7, 8);
            protocol.writeI32(struct.payout_method_type.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("payout_method_action", 8, 8);
            protocol.writeI32(struct.payout_method_action.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 9, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            if (struct.gibraltar_instrument_token != null) {
                protocol.writeFieldBegin("gibraltar_instrument_token", 10, PassportService.SF_DG11);
                protocol.writeString(struct.gibraltar_instrument_token);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private PayoutsPayoutMethodSetupNativeEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.billing_country = builder.billing_country;
        this.payment_instrument_type = builder.payment_instrument_type;
        this.currency = builder.currency;
        this.payout_method_setup_page = builder.payout_method_setup_page;
        this.payout_method_type = builder.payout_method_type;
        this.payout_method_action = builder.payout_method_action;
        this.operation = builder.operation;
        this.gibraltar_instrument_token = builder.gibraltar_instrument_token;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof PayoutsPayoutMethodSetupNativeEvent)) {
            return false;
        }
        PayoutsPayoutMethodSetupNativeEvent that = (PayoutsPayoutMethodSetupNativeEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.billing_country == that.billing_country || (this.billing_country != null && this.billing_country.equals(that.billing_country))) && ((this.payment_instrument_type == that.payment_instrument_type || (this.payment_instrument_type != null && this.payment_instrument_type.equals(that.payment_instrument_type))) && ((this.currency == that.currency || this.currency.equals(that.currency)) && ((this.payout_method_setup_page == that.payout_method_setup_page || this.payout_method_setup_page.equals(that.payout_method_setup_page)) && ((this.payout_method_type == that.payout_method_type || this.payout_method_type.equals(that.payout_method_type)) && ((this.payout_method_action == that.payout_method_action || this.payout_method_action.equals(that.payout_method_action)) && (this.operation == that.operation || this.operation.equals(that.operation))))))))))) {
            if (this.gibraltar_instrument_token == that.gibraltar_instrument_token) {
                return true;
            }
            if (this.gibraltar_instrument_token != null && this.gibraltar_instrument_token.equals(that.gibraltar_instrument_token)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        int code = (((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ (this.billing_country == null ? 0 : this.billing_country.hashCode())) * -2128831035;
        if (this.payment_instrument_type == null) {
            hashCode = 0;
        } else {
            hashCode = this.payment_instrument_type.hashCode();
        }
        int code2 = (((((((((((code ^ hashCode) * -2128831035) ^ this.currency.hashCode()) * -2128831035) ^ this.payout_method_setup_page.hashCode()) * -2128831035) ^ this.payout_method_type.hashCode()) * -2128831035) ^ this.payout_method_action.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035;
        if (this.gibraltar_instrument_token != null) {
            i = this.gibraltar_instrument_token.hashCode();
        }
        return (code2 ^ i) * -2128831035;
    }

    public String toString() {
        return "PayoutsPayoutMethodSetupNativeEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", billing_country=" + this.billing_country + ", payment_instrument_type=" + this.payment_instrument_type + ", currency=" + this.currency + ", payout_method_setup_page=" + this.payout_method_setup_page + ", payout_method_type=" + this.payout_method_type + ", payout_method_action=" + this.payout_method_action + ", operation=" + this.operation + ", gibraltar_instrument_token=" + this.gibraltar_instrument_token + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
