package com.airbnb.jitney.event.logging.Payments.p186v3;

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

/* renamed from: com.airbnb.jitney.event.logging.Payments.v3.PaymentsConfirmAndPayErrorEvent */
public final class PaymentsConfirmAndPayErrorEvent implements Struct {
    public static final Adapter<PaymentsConfirmAndPayErrorEvent, Builder> ADAPTER = new PaymentsConfirmAndPayErrorEventAdapter();
    public final String billing_error_key;
    public final Context context;
    public final String event_name;
    public final C2451Operation operation;
    public final String page;
    public final C2543PaymentsContext payments_context;
    public final C2597QuickpayConfig quickpay_config;
    public final Long quickpay_error_code;
    public final String quickpay_error_detail;
    public final String quickpay_error_message;
    public final String reservation_code;
    public final String schema;
    public final String target;

    /* renamed from: com.airbnb.jitney.event.logging.Payments.v3.PaymentsConfirmAndPayErrorEvent$Builder */
    public static final class Builder implements StructBuilder<PaymentsConfirmAndPayErrorEvent> {
        /* access modifiers changed from: private */
        public String billing_error_key;
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
        public Long quickpay_error_code;
        /* access modifiers changed from: private */
        public String quickpay_error_detail;
        /* access modifiers changed from: private */
        public String quickpay_error_message;
        /* access modifiers changed from: private */
        public String reservation_code;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public String target;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Payments:PaymentsConfirmAndPayErrorEvent:3.0.0";
            this.event_name = "payments_confirm_and_pay_error";
            this.page = "quickpay";
            this.target = "confirm_and_pay";
            this.operation = C2451Operation.Click;
        }

        public Builder(Context context2, C2543PaymentsContext payments_context2, C2597QuickpayConfig quickpay_config2, String quickpay_error_message2, Long quickpay_error_code2, String quickpay_error_detail2, String billing_error_key2) {
            this.schema = "com.airbnb.jitney.event.logging.Payments:PaymentsConfirmAndPayErrorEvent:3.0.0";
            this.event_name = "payments_confirm_and_pay_error";
            this.context = context2;
            this.page = "quickpay";
            this.target = "confirm_and_pay";
            this.operation = C2451Operation.Click;
            this.payments_context = payments_context2;
            this.quickpay_config = quickpay_config2;
            this.quickpay_error_message = quickpay_error_message2;
            this.quickpay_error_code = quickpay_error_code2;
            this.quickpay_error_detail = quickpay_error_detail2;
            this.billing_error_key = billing_error_key2;
        }

        public PaymentsConfirmAndPayErrorEvent build() {
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
            } else if (this.quickpay_config == null) {
                throw new IllegalStateException("Required field 'quickpay_config' is missing");
            } else if (this.quickpay_error_message == null) {
                throw new IllegalStateException("Required field 'quickpay_error_message' is missing");
            } else if (this.quickpay_error_code == null) {
                throw new IllegalStateException("Required field 'quickpay_error_code' is missing");
            } else if (this.quickpay_error_detail == null) {
                throw new IllegalStateException("Required field 'quickpay_error_detail' is missing");
            } else if (this.billing_error_key != null) {
                return new PaymentsConfirmAndPayErrorEvent(this);
            } else {
                throw new IllegalStateException("Required field 'billing_error_key' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Payments.v3.PaymentsConfirmAndPayErrorEvent$PaymentsConfirmAndPayErrorEventAdapter */
    private static final class PaymentsConfirmAndPayErrorEventAdapter implements Adapter<PaymentsConfirmAndPayErrorEvent, Builder> {
        private PaymentsConfirmAndPayErrorEventAdapter() {
        }

        public void write(Protocol protocol, PaymentsConfirmAndPayErrorEvent struct) throws IOException {
            protocol.writeStructBegin("PaymentsConfirmAndPayErrorEvent");
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
            protocol.writeFieldBegin("quickpay_error_message", 9, PassportService.SF_DG11);
            protocol.writeString(struct.quickpay_error_message);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("quickpay_error_code", 10, 10);
            protocol.writeI64(struct.quickpay_error_code.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("quickpay_error_detail", 11, PassportService.SF_DG11);
            protocol.writeString(struct.quickpay_error_detail);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("billing_error_key", 12, PassportService.SF_DG11);
            protocol.writeString(struct.billing_error_key);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private PaymentsConfirmAndPayErrorEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.target = builder.target;
        this.operation = builder.operation;
        this.payments_context = builder.payments_context;
        this.reservation_code = builder.reservation_code;
        this.quickpay_config = builder.quickpay_config;
        this.quickpay_error_message = builder.quickpay_error_message;
        this.quickpay_error_code = builder.quickpay_error_code;
        this.quickpay_error_detail = builder.quickpay_error_detail;
        this.billing_error_key = builder.billing_error_key;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof PaymentsConfirmAndPayErrorEvent)) {
            return false;
        }
        PaymentsConfirmAndPayErrorEvent that = (PaymentsConfirmAndPayErrorEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.payments_context == that.payments_context || this.payments_context.equals(that.payments_context)) && ((this.reservation_code == that.reservation_code || (this.reservation_code != null && this.reservation_code.equals(that.reservation_code))) && ((this.quickpay_config == that.quickpay_config || this.quickpay_config.equals(that.quickpay_config)) && ((this.quickpay_error_message == that.quickpay_error_message || this.quickpay_error_message.equals(that.quickpay_error_message)) && ((this.quickpay_error_code == that.quickpay_error_code || this.quickpay_error_code.equals(that.quickpay_error_code)) && ((this.quickpay_error_detail == that.quickpay_error_detail || this.quickpay_error_detail.equals(that.quickpay_error_detail)) && (this.billing_error_key == that.billing_error_key || this.billing_error_key.equals(that.billing_error_key)))))))))))))) {
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
        return (((((((((((code ^ i) * -2128831035) ^ this.quickpay_config.hashCode()) * -2128831035) ^ this.quickpay_error_message.hashCode()) * -2128831035) ^ this.quickpay_error_code.hashCode()) * -2128831035) ^ this.quickpay_error_detail.hashCode()) * -2128831035) ^ this.billing_error_key.hashCode()) * -2128831035;
    }

    public String toString() {
        return "PaymentsConfirmAndPayErrorEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", target=" + this.target + ", operation=" + this.operation + ", payments_context=" + this.payments_context + ", reservation_code=" + this.reservation_code + ", quickpay_config=" + this.quickpay_config + ", quickpay_error_message=" + this.quickpay_error_message + ", quickpay_error_code=" + this.quickpay_error_code + ", quickpay_error_detail=" + this.quickpay_error_detail + ", billing_error_key=" + this.billing_error_key + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
