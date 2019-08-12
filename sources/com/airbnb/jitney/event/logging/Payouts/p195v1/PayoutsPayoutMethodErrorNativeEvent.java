package com.airbnb.jitney.event.logging.Payouts.p195v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.PaymentInstrumentType.p183v1.C2504PaymentInstrumentType;
import com.airbnb.jitney.event.logging.PayoutMethodError.p191v1.C2546PayoutMethodError;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Payouts.v1.PayoutsPayoutMethodErrorNativeEvent */
public final class PayoutsPayoutMethodErrorNativeEvent implements Struct {
    public static final Adapter<PayoutsPayoutMethodErrorNativeEvent, Object> ADAPTER = new PayoutsPayoutMethodErrorNativeEventAdapter();
    public final String billing_country;
    public final Context context;
    public final String currency;
    public final String event_name;
    public final C2451Operation operation;
    public final C2504PaymentInstrumentType payment_instrument_type;
    public final C2546PayoutMethodError payout_method_error;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.Payouts.v1.PayoutsPayoutMethodErrorNativeEvent$PayoutsPayoutMethodErrorNativeEventAdapter */
    private static final class PayoutsPayoutMethodErrorNativeEventAdapter implements Adapter<PayoutsPayoutMethodErrorNativeEvent, Object> {
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
            protocol.writeFieldBegin("payment_instrument_type", 4, 8);
            protocol.writeI32(struct.payment_instrument_type.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("billing_country", 5, PassportService.SF_DG11);
            protocol.writeString(struct.billing_country);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(AirbnbConstants.PREFS_CURRENCY, 6, PassportService.SF_DG11);
            protocol.writeString(struct.currency);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 7, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
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
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.payout_method_error == that.payout_method_error || this.payout_method_error.equals(that.payout_method_error)) && ((this.payment_instrument_type == that.payment_instrument_type || this.payment_instrument_type.equals(that.payment_instrument_type)) && ((this.billing_country == that.billing_country || this.billing_country.equals(that.billing_country)) && ((this.currency == that.currency || this.currency.equals(that.currency)) && (this.operation == that.operation || this.operation.equals(that.operation))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.payout_method_error.hashCode()) * -2128831035) ^ this.payment_instrument_type.hashCode()) * -2128831035) ^ this.billing_country.hashCode()) * -2128831035) ^ this.currency.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035;
    }

    public String toString() {
        return "PayoutsPayoutMethodErrorNativeEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", payout_method_error=" + this.payout_method_error + ", payment_instrument_type=" + this.payment_instrument_type + ", billing_country=" + this.billing_country + ", currency=" + this.currency + ", operation=" + this.operation + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
