package com.airbnb.jitney.event.logging.Payments.p185v2;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.PaymentsContext.p189v1.C2543PaymentsContext;
import com.airbnb.jitney.event.logging.QuickpayConfig.p216v1.C2597QuickpayConfig;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Payments.v2.PaymentsConfirmAndPayErrorEvent */
public final class PaymentsConfirmAndPayErrorEvent implements Struct {
    public static final Adapter<PaymentsConfirmAndPayErrorEvent, Object> ADAPTER = new PaymentsConfirmAndPayErrorEventAdapter();
    public final Context context;
    public final String event_name;
    public final C2451Operation operation;
    public final String page;
    public final C2543PaymentsContext payments_context;
    public final C2597QuickpayConfig quickpay_config;
    public final Long quickpay_error_code;
    public final String quickpay_error_detail;
    public final String quickpay_error_message;
    public final String schema;
    public final String target;

    /* renamed from: com.airbnb.jitney.event.logging.Payments.v2.PaymentsConfirmAndPayErrorEvent$PaymentsConfirmAndPayErrorEventAdapter */
    private static final class PaymentsConfirmAndPayErrorEventAdapter implements Adapter<PaymentsConfirmAndPayErrorEvent, Object> {
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
            protocol.writeFieldBegin("quickpay_config", 7, 8);
            protocol.writeI32(struct.quickpay_config.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("quickpay_error_message", 8, PassportService.SF_DG11);
            protocol.writeString(struct.quickpay_error_message);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("quickpay_error_code", 9, 10);
            protocol.writeI64(struct.quickpay_error_code.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("quickpay_error_detail", 10, PassportService.SF_DG11);
            protocol.writeString(struct.quickpay_error_detail);
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
        if (!(other instanceof PaymentsConfirmAndPayErrorEvent)) {
            return false;
        }
        PaymentsConfirmAndPayErrorEvent that = (PaymentsConfirmAndPayErrorEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.payments_context == that.payments_context || this.payments_context.equals(that.payments_context)) && ((this.quickpay_config == that.quickpay_config || this.quickpay_config.equals(that.quickpay_config)) && ((this.quickpay_error_message == that.quickpay_error_message || this.quickpay_error_message.equals(that.quickpay_error_message)) && ((this.quickpay_error_code == that.quickpay_error_code || this.quickpay_error_code.equals(that.quickpay_error_code)) && (this.quickpay_error_detail == that.quickpay_error_detail || this.quickpay_error_detail.equals(that.quickpay_error_detail)))))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.payments_context.hashCode()) * -2128831035) ^ this.quickpay_config.hashCode()) * -2128831035) ^ this.quickpay_error_message.hashCode()) * -2128831035) ^ this.quickpay_error_code.hashCode()) * -2128831035) ^ this.quickpay_error_detail.hashCode()) * -2128831035;
    }

    public String toString() {
        return "PaymentsConfirmAndPayErrorEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", target=" + this.target + ", operation=" + this.operation + ", payments_context=" + this.payments_context + ", quickpay_config=" + this.quickpay_config + ", quickpay_error_message=" + this.quickpay_error_message + ", quickpay_error_code=" + this.quickpay_error_code + ", quickpay_error_detail=" + this.quickpay_error_detail + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
