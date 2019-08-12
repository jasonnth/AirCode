package com.airbnb.jitney.event.logging.Payments.p185v2;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.PaymentInstrumentRowSection.p182v1.C2503PaymentInstrumentRowSection;
import com.airbnb.jitney.event.logging.PaymentsContext.p189v1.C2543PaymentsContext;
import com.airbnb.jitney.event.logging.QuickpayConfig.p216v1.C2597QuickpayConfig;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Payments.v2.PaymentsQuickpayPaymentInstrumentRowEvent */
public final class PaymentsQuickpayPaymentInstrumentRowEvent implements Struct {
    public static final Adapter<PaymentsQuickpayPaymentInstrumentRowEvent, Builder> ADAPTER = new PaymentsQuickpayPaymentInstrumentRowEventAdapter();
    public final Context context;
    public final String event_name;
    public final C2451Operation operation;
    public final String page;
    public final C2503PaymentInstrumentRowSection payment_instrument_row_section;
    public final C2543PaymentsContext payments_context;
    public final C2597QuickpayConfig quickpay_config;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.Payments.v2.PaymentsQuickpayPaymentInstrumentRowEvent$Builder */
    public static final class Builder implements StructBuilder<PaymentsQuickpayPaymentInstrumentRowEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public C2503PaymentInstrumentRowSection payment_instrument_row_section;
        /* access modifiers changed from: private */
        public C2543PaymentsContext payments_context;
        /* access modifiers changed from: private */
        public C2597QuickpayConfig quickpay_config;
        /* access modifiers changed from: private */
        public String schema;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Payments:PaymentsQuickpayPaymentInstrumentRowEvent:2.0.0";
            this.event_name = "payments_quickpay_payment_instrument_row";
            this.page = "quickpay";
            this.operation = C2451Operation.Click;
        }

        public Builder(Context context2, C2503PaymentInstrumentRowSection payment_instrument_row_section2, C2543PaymentsContext payments_context2, C2597QuickpayConfig quickpay_config2) {
            this.schema = "com.airbnb.jitney.event.logging.Payments:PaymentsQuickpayPaymentInstrumentRowEvent:2.0.0";
            this.event_name = "payments_quickpay_payment_instrument_row";
            this.context = context2;
            this.page = "quickpay";
            this.payment_instrument_row_section = payment_instrument_row_section2;
            this.operation = C2451Operation.Click;
            this.payments_context = payments_context2;
            this.quickpay_config = quickpay_config2;
        }

        public PaymentsQuickpayPaymentInstrumentRowEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.page == null) {
                throw new IllegalStateException("Required field 'page' is missing");
            } else if (this.payment_instrument_row_section == null) {
                throw new IllegalStateException("Required field 'payment_instrument_row_section' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.payments_context == null) {
                throw new IllegalStateException("Required field 'payments_context' is missing");
            } else if (this.quickpay_config != null) {
                return new PaymentsQuickpayPaymentInstrumentRowEvent(this);
            } else {
                throw new IllegalStateException("Required field 'quickpay_config' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Payments.v2.PaymentsQuickpayPaymentInstrumentRowEvent$PaymentsQuickpayPaymentInstrumentRowEventAdapter */
    private static final class PaymentsQuickpayPaymentInstrumentRowEventAdapter implements Adapter<PaymentsQuickpayPaymentInstrumentRowEvent, Builder> {
        private PaymentsQuickpayPaymentInstrumentRowEventAdapter() {
        }

        public void write(Protocol protocol, PaymentsQuickpayPaymentInstrumentRowEvent struct) throws IOException {
            protocol.writeStructBegin("PaymentsQuickpayPaymentInstrumentRowEvent");
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
            protocol.writeFieldBegin("payment_instrument_row_section", 4, 8);
            protocol.writeI32(struct.payment_instrument_row_section.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 5, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("payments_context", 6, PassportService.SF_DG12);
            C2543PaymentsContext.ADAPTER.write(protocol, struct.payments_context);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("quickpay_config", 7, 8);
            protocol.writeI32(struct.quickpay_config.value);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private PaymentsQuickpayPaymentInstrumentRowEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.payment_instrument_row_section = builder.payment_instrument_row_section;
        this.operation = builder.operation;
        this.payments_context = builder.payments_context;
        this.quickpay_config = builder.quickpay_config;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof PaymentsQuickpayPaymentInstrumentRowEvent)) {
            return false;
        }
        PaymentsQuickpayPaymentInstrumentRowEvent that = (PaymentsQuickpayPaymentInstrumentRowEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.payment_instrument_row_section == that.payment_instrument_row_section || this.payment_instrument_row_section.equals(that.payment_instrument_row_section)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.payments_context == that.payments_context || this.payments_context.equals(that.payments_context)) && (this.quickpay_config == that.quickpay_config || this.quickpay_config.equals(that.quickpay_config))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.payment_instrument_row_section.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.payments_context.hashCode()) * -2128831035) ^ this.quickpay_config.hashCode()) * -2128831035;
    }

    public String toString() {
        return "PaymentsQuickpayPaymentInstrumentRowEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", payment_instrument_row_section=" + this.payment_instrument_row_section + ", operation=" + this.operation + ", payments_context=" + this.payments_context + ", quickpay_config=" + this.quickpay_config + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
