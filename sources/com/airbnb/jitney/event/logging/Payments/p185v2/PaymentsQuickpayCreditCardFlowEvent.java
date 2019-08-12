package com.airbnb.jitney.event.logging.Payments.p185v2;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.QuickpayAddCcSection.p215v1.C2596QuickpayAddCcSection;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Payments.v2.PaymentsQuickpayCreditCardFlowEvent */
public final class PaymentsQuickpayCreditCardFlowEvent implements Struct {
    public static final Adapter<PaymentsQuickpayCreditCardFlowEvent, Builder> ADAPTER = new PaymentsQuickpayCreditCardFlowEventAdapter();
    public final Context context;
    public final String error_message;
    public final String event_name;
    public final C2451Operation operation;
    public final String page;
    public final Long payment_instrument_id;
    public final C2596QuickpayAddCcSection quickpay_add_cc_section;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.Payments.v2.PaymentsQuickpayCreditCardFlowEvent$Builder */
    public static final class Builder implements StructBuilder<PaymentsQuickpayCreditCardFlowEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String error_message;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public Long payment_instrument_id;
        /* access modifiers changed from: private */
        public C2596QuickpayAddCcSection quickpay_add_cc_section;
        /* access modifiers changed from: private */
        public String schema;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Payments:PaymentsQuickpayCreditCardFlowEvent:2.0.0";
            this.event_name = "payments_quickpay_credit_card_flow";
            this.page = "quickpay_cc_add";
            this.operation = C2451Operation.Click;
        }

        public Builder(Context context2, C2596QuickpayAddCcSection quickpay_add_cc_section2) {
            this.schema = "com.airbnb.jitney.event.logging.Payments:PaymentsQuickpayCreditCardFlowEvent:2.0.0";
            this.event_name = "payments_quickpay_credit_card_flow";
            this.context = context2;
            this.page = "quickpay_cc_add";
            this.quickpay_add_cc_section = quickpay_add_cc_section2;
            this.operation = C2451Operation.Click;
        }

        public PaymentsQuickpayCreditCardFlowEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.page == null) {
                throw new IllegalStateException("Required field 'page' is missing");
            } else if (this.quickpay_add_cc_section == null) {
                throw new IllegalStateException("Required field 'quickpay_add_cc_section' is missing");
            } else if (this.operation != null) {
                return new PaymentsQuickpayCreditCardFlowEvent(this);
            } else {
                throw new IllegalStateException("Required field 'operation' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Payments.v2.PaymentsQuickpayCreditCardFlowEvent$PaymentsQuickpayCreditCardFlowEventAdapter */
    private static final class PaymentsQuickpayCreditCardFlowEventAdapter implements Adapter<PaymentsQuickpayCreditCardFlowEvent, Builder> {
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
            protocol.writeFieldBegin("quickpay_add_cc_section", 4, 8);
            protocol.writeI32(struct.quickpay_add_cc_section.value);
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

    private PaymentsQuickpayCreditCardFlowEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.quickpay_add_cc_section = builder.quickpay_add_cc_section;
        this.error_message = builder.error_message;
        this.operation = builder.operation;
        this.payment_instrument_id = builder.payment_instrument_id;
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
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.quickpay_add_cc_section == that.quickpay_add_cc_section || this.quickpay_add_cc_section.equals(that.quickpay_add_cc_section)) && ((this.error_message == that.error_message || (this.error_message != null && this.error_message.equals(that.error_message))) && (this.operation == that.operation || this.operation.equals(that.operation)))))))) {
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
        int code = (((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.quickpay_add_cc_section.hashCode()) * -2128831035) ^ (this.error_message == null ? 0 : this.error_message.hashCode())) * -2128831035) ^ this.operation.hashCode()) * -2128831035;
        if (this.payment_instrument_id != null) {
            i = this.payment_instrument_id.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "PaymentsQuickpayCreditCardFlowEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", quickpay_add_cc_section=" + this.quickpay_add_cc_section + ", error_message=" + this.error_message + ", operation=" + this.operation + ", payment_instrument_id=" + this.payment_instrument_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
