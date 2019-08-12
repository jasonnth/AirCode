package com.airbnb.jitney.event.logging.Payments.p184v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.PaymentInstrumentType.p183v1.C2504PaymentInstrumentType;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Payments.v1.PaymentsSelectPaymentMethodEvent */
public final class PaymentsSelectPaymentMethodEvent implements Struct {
    public static final Adapter<PaymentsSelectPaymentMethodEvent, Builder> ADAPTER = new PaymentsSelectPaymentMethodEventAdapter();
    public final Context context;
    public final String event_name;
    public final C2451Operation operation;
    public final String page;
    public final C2504PaymentInstrumentType payment_instrument_type;
    public final String schema;
    public final String target;

    /* renamed from: com.airbnb.jitney.event.logging.Payments.v1.PaymentsSelectPaymentMethodEvent$Builder */
    public static final class Builder implements StructBuilder<PaymentsSelectPaymentMethodEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public C2504PaymentInstrumentType payment_instrument_type;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public String target;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Payments:PaymentsSelectPaymentMethodEvent:1.0.0";
            this.event_name = "payments_select_payment_method";
            this.page = "quickpay";
            this.target = "select_method";
            this.operation = C2451Operation.Click;
        }

        public Builder(Context context2, C2504PaymentInstrumentType payment_instrument_type2) {
            this.schema = "com.airbnb.jitney.event.logging.Payments:PaymentsSelectPaymentMethodEvent:1.0.0";
            this.event_name = "payments_select_payment_method";
            this.context = context2;
            this.page = "quickpay";
            this.target = "select_method";
            this.operation = C2451Operation.Click;
            this.payment_instrument_type = payment_instrument_type2;
        }

        public PaymentsSelectPaymentMethodEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.page == null) {
                throw new IllegalStateException("Required field 'page' is missing");
            } else if (this.target == null) {
                throw new IllegalStateException("Required field 'target' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.payment_instrument_type != null) {
                return new PaymentsSelectPaymentMethodEvent(this);
            } else {
                throw new IllegalStateException("Required field 'payment_instrument_type' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Payments.v1.PaymentsSelectPaymentMethodEvent$PaymentsSelectPaymentMethodEventAdapter */
    private static final class PaymentsSelectPaymentMethodEventAdapter implements Adapter<PaymentsSelectPaymentMethodEvent, Builder> {
        private PaymentsSelectPaymentMethodEventAdapter() {
        }

        public void write(Protocol protocol, PaymentsSelectPaymentMethodEvent struct) throws IOException {
            protocol.writeStructBegin("PaymentsSelectPaymentMethodEvent");
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
            protocol.writeFieldBegin(BaseAnalytics.TARGET, 4, PassportService.SF_DG11);
            protocol.writeString(struct.target);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 5, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("payment_instrument_type", 6, 8);
            protocol.writeI32(struct.payment_instrument_type.value);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private PaymentsSelectPaymentMethodEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.target = builder.target;
        this.operation = builder.operation;
        this.payment_instrument_type = builder.payment_instrument_type;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof PaymentsSelectPaymentMethodEvent)) {
            return false;
        }
        PaymentsSelectPaymentMethodEvent that = (PaymentsSelectPaymentMethodEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && (this.payment_instrument_type == that.payment_instrument_type || this.payment_instrument_type.equals(that.payment_instrument_type)))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.payment_instrument_type.hashCode()) * -2128831035;
    }

    public String toString() {
        return "PaymentsSelectPaymentMethodEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", target=" + this.target + ", operation=" + this.operation + ", payment_instrument_type=" + this.payment_instrument_type + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
