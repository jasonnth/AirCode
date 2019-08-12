package com.airbnb.jitney.event.logging.Payouts.p195v1;

import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.jitney.event.logging.PayoutMethodSelectAction.p192v1.C2547PayoutMethodSelectAction;
import com.airbnb.jitney.event.logging.PayoutMethodType.p194v1.C2549PayoutMethodType;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Payouts.v1.PayoutsPayoutMethodSelectNativeEvent */
public final class PayoutsPayoutMethodSelectNativeEvent implements Struct {
    public static final Adapter<PayoutsPayoutMethodSelectNativeEvent, Builder> ADAPTER = new PayoutsPayoutMethodSelectNativeEventAdapter();
    public final String billing_country;
    public final Context context;
    public final String currency;
    public final String event_name;
    public final C2547PayoutMethodSelectAction payout_method_select_action;
    public final C2549PayoutMethodType payout_method_type;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.Payouts.v1.PayoutsPayoutMethodSelectNativeEvent$Builder */
    public static final class Builder implements StructBuilder<PayoutsPayoutMethodSelectNativeEvent> {
        /* access modifiers changed from: private */
        public String billing_country;
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String currency;
        /* access modifiers changed from: private */
        public String event_name = "payouts_payout_method_select_native";
        /* access modifiers changed from: private */
        public C2547PayoutMethodSelectAction payout_method_select_action;
        /* access modifiers changed from: private */
        public C2549PayoutMethodType payout_method_type;
        /* access modifiers changed from: private */
        public String schema = "com.airbnb.jitney.event.logging.Payouts:PayoutsPayoutMethodSelectNativeEvent:1.0.0";

        private Builder() {
        }

        public Builder(Context context2, C2547PayoutMethodSelectAction payout_method_select_action2) {
            this.context = context2;
            this.payout_method_select_action = payout_method_select_action2;
        }

        public PayoutsPayoutMethodSelectNativeEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.payout_method_select_action != null) {
                return new PayoutsPayoutMethodSelectNativeEvent(this);
            } else {
                throw new IllegalStateException("Required field 'payout_method_select_action' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Payouts.v1.PayoutsPayoutMethodSelectNativeEvent$PayoutsPayoutMethodSelectNativeEventAdapter */
    private static final class PayoutsPayoutMethodSelectNativeEventAdapter implements Adapter<PayoutsPayoutMethodSelectNativeEvent, Builder> {
        private PayoutsPayoutMethodSelectNativeEventAdapter() {
        }

        public void write(Protocol protocol, PayoutsPayoutMethodSelectNativeEvent struct) throws IOException {
            protocol.writeStructBegin("PayoutsPayoutMethodSelectNativeEvent");
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
            protocol.writeFieldBegin("payout_method_select_action", 4, 8);
            protocol.writeI32(struct.payout_method_select_action.value);
            protocol.writeFieldEnd();
            if (struct.payout_method_type != null) {
                protocol.writeFieldBegin("payout_method_type", 5, 8);
                protocol.writeI32(struct.payout_method_type.value);
                protocol.writeFieldEnd();
            }
            if (struct.currency != null) {
                protocol.writeFieldBegin(AirbnbConstants.PREFS_CURRENCY, 6, PassportService.SF_DG11);
                protocol.writeString(struct.currency);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private PayoutsPayoutMethodSelectNativeEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.billing_country = builder.billing_country;
        this.payout_method_select_action = builder.payout_method_select_action;
        this.payout_method_type = builder.payout_method_type;
        this.currency = builder.currency;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof PayoutsPayoutMethodSelectNativeEvent)) {
            return false;
        }
        PayoutsPayoutMethodSelectNativeEvent that = (PayoutsPayoutMethodSelectNativeEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.billing_country == that.billing_country || (this.billing_country != null && this.billing_country.equals(that.billing_country))) && ((this.payout_method_select_action == that.payout_method_select_action || this.payout_method_select_action.equals(that.payout_method_select_action)) && (this.payout_method_type == that.payout_method_type || (this.payout_method_type != null && this.payout_method_type.equals(that.payout_method_type)))))))) {
            if (this.currency == that.currency) {
                return true;
            }
            if (this.currency != null && this.currency.equals(that.currency)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ (this.billing_country == null ? 0 : this.billing_country.hashCode())) * -2128831035) ^ this.payout_method_select_action.hashCode()) * -2128831035) ^ (this.payout_method_type == null ? 0 : this.payout_method_type.hashCode())) * -2128831035;
        if (this.currency != null) {
            i = this.currency.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "PayoutsPayoutMethodSelectNativeEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", billing_country=" + this.billing_country + ", payout_method_select_action=" + this.payout_method_select_action + ", payout_method_type=" + this.payout_method_type + ", currency=" + this.currency + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
