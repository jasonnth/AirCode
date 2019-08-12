package com.airbnb.jitney.event.logging.Payments.p184v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Payments.v1.PaymentsQuickpayCreditCardFlowEvent */
public final class PaymentsQuickpayCreditCardFlowEvent implements Struct {
    public static final Adapter<PaymentsQuickpayCreditCardFlowEvent, Object> ADAPTER = new PaymentsQuickpayCreditCardFlowEventAdapter();
    public final Context context;
    public final String error_message;
    public final String event_name;
    public final C2451Operation operation;
    public final String page;
    public final Long payment_instrument_id;
    public final String schema;
    public final String section;

    /* renamed from: com.airbnb.jitney.event.logging.Payments.v1.PaymentsQuickpayCreditCardFlowEvent$PaymentsQuickpayCreditCardFlowEventAdapter */
    private static final class PaymentsQuickpayCreditCardFlowEventAdapter implements Adapter<PaymentsQuickpayCreditCardFlowEvent, Object> {
        private PaymentsQuickpayCreditCardFlowEventAdapter() {
        }

        public void write(Protocol protocol, PaymentsQuickpayCreditCardFlowEvent struct) throws IOException {
            protocol.writeStructBegin("PaymentsQuickpayCreditCardFlowEvent");
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
            protocol.writeFieldBegin("page", 3, PassportService.SF_DG11);
            protocol.writeString(struct.page);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.SECTION, 4, PassportService.SF_DG11);
            protocol.writeString(struct.section);
            protocol.writeFieldEnd();
            if (struct.error_message != null) {
                protocol.writeFieldBegin("error_message", 5, PassportService.SF_DG11);
                protocol.writeString(struct.error_message);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 6, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            if (struct.payment_instrument_id != null) {
                protocol.writeFieldBegin("payment_instrument_id", 7, 10);
                protocol.writeI64(struct.payment_instrument_id.longValue());
                protocol.writeFieldEnd();
            }
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
        if (!(other instanceof PaymentsQuickpayCreditCardFlowEvent)) {
            return false;
        }
        PaymentsQuickpayCreditCardFlowEvent that = (PaymentsQuickpayCreditCardFlowEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.section == that.section || this.section.equals(that.section)) && ((this.error_message == that.error_message || (this.error_message != null && this.error_message.equals(that.error_message))) && (this.operation == that.operation || this.operation.equals(that.operation)))))))) {
            if (this.payment_instrument_id == that.payment_instrument_id) {
                return true;
            }
            if (this.payment_instrument_id != null && this.payment_instrument_id.equals(that.payment_instrument_id)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.section.hashCode()) * -2128831035) ^ (this.error_message == null ? 0 : this.error_message.hashCode())) * -2128831035) ^ this.operation.hashCode()) * -2128831035;
        if (this.payment_instrument_id != null) {
            i = this.payment_instrument_id.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "PaymentsQuickpayCreditCardFlowEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", section=" + this.section + ", error_message=" + this.error_message + ", operation=" + this.operation + ", payment_instrument_id=" + this.payment_instrument_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
