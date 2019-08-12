package com.airbnb.jitney.event.logging.Payments.p185v2;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
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

/* renamed from: com.airbnb.jitney.event.logging.Payments.v2.PaymentsConfirmAndPaySuccessEvent */
public final class PaymentsConfirmAndPaySuccessEvent implements Struct {
    public static final Adapter<PaymentsConfirmAndPaySuccessEvent, Builder> ADAPTER = new PaymentsConfirmAndPaySuccessEventAdapter();
    public final Context context;
    public final String event_name;
    public final C2451Operation operation;
    public final String page;
    public final C2543PaymentsContext payments_context;
    public final C2597QuickpayConfig quickpay_config;
    public final String reservation_code;
    public final String schema;
    public final String target;

    /* renamed from: com.airbnb.jitney.event.logging.Payments.v2.PaymentsConfirmAndPaySuccessEvent$Builder */
    public static final class Builder implements StructBuilder<PaymentsConfirmAndPaySuccessEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public C2543PaymentsContext payments_context;
        /* access modifiers changed from: private */
        public C2597QuickpayConfig quickpay_config;
        /* access modifiers changed from: private */
        public String reservation_code;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public String target;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Payments:PaymentsConfirmAndPaySuccessEvent:2.0.0";
            this.event_name = "payments_confirm_and_pay_success";
            this.page = "quickpay";
            this.target = "confirm_and_pay";
            this.operation = C2451Operation.Click;
        }

        public Builder(Context context2, C2543PaymentsContext payments_context2, C2597QuickpayConfig quickpay_config2) {
            this.schema = "com.airbnb.jitney.event.logging.Payments:PaymentsConfirmAndPaySuccessEvent:2.0.0";
            this.event_name = "payments_confirm_and_pay_success";
            this.context = context2;
            this.page = "quickpay";
            this.target = "confirm_and_pay";
            this.operation = C2451Operation.Click;
            this.payments_context = payments_context2;
            this.quickpay_config = quickpay_config2;
        }

        public Builder reservation_code(String reservation_code2) {
            this.reservation_code = reservation_code2;
            return this;
        }

        public PaymentsConfirmAndPaySuccessEvent build() {
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
            } else if (this.payments_context == null) {
                throw new IllegalStateException("Required field 'payments_context' is missing");
            } else if (this.quickpay_config != null) {
                return new PaymentsConfirmAndPaySuccessEvent(this);
            } else {
                throw new IllegalStateException("Required field 'quickpay_config' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Payments.v2.PaymentsConfirmAndPaySuccessEvent$PaymentsConfirmAndPaySuccessEventAdapter */
    private static final class PaymentsConfirmAndPaySuccessEventAdapter implements Adapter<PaymentsConfirmAndPaySuccessEvent, Builder> {
        private PaymentsConfirmAndPaySuccessEventAdapter() {
        }

        public void write(Protocol protocol, PaymentsConfirmAndPaySuccessEvent struct) throws IOException {
            protocol.writeStructBegin("PaymentsConfirmAndPaySuccessEvent");
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
            protocol.writeFieldBegin("payments_context", 6, PassportService.SF_DG12);
            C2543PaymentsContext.ADAPTER.write(protocol, struct.payments_context);
            protocol.writeFieldEnd();
            if (struct.reservation_code != null) {
                protocol.writeFieldBegin("reservation_code", 7, PassportService.SF_DG11);
                protocol.writeString(struct.reservation_code);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin("quickpay_config", 8, 8);
            protocol.writeI32(struct.quickpay_config.value);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private PaymentsConfirmAndPaySuccessEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.target = builder.target;
        this.operation = builder.operation;
        this.payments_context = builder.payments_context;
        this.reservation_code = builder.reservation_code;
        this.quickpay_config = builder.quickpay_config;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof PaymentsConfirmAndPaySuccessEvent)) {
            return false;
        }
        PaymentsConfirmAndPaySuccessEvent that = (PaymentsConfirmAndPaySuccessEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.payments_context == that.payments_context || this.payments_context.equals(that.payments_context)) && ((this.reservation_code == that.reservation_code || (this.reservation_code != null && this.reservation_code.equals(that.reservation_code))) && (this.quickpay_config == that.quickpay_config || this.quickpay_config.equals(that.quickpay_config)))))))))) {
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
        int code = (((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.payments_context.hashCode()) * -2128831035;
        if (this.reservation_code != null) {
            i = this.reservation_code.hashCode();
        }
        return (((code ^ i) * -2128831035) ^ this.quickpay_config.hashCode()) * -2128831035;
    }

    public String toString() {
        return "PaymentsConfirmAndPaySuccessEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", target=" + this.target + ", operation=" + this.operation + ", payments_context=" + this.payments_context + ", reservation_code=" + this.reservation_code + ", quickpay_config=" + this.quickpay_config + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
