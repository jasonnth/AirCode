package com.airbnb.jitney.event.logging.Payments.p184v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.BpqFetchReason.p042v1.C1806BpqFetchReason;
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

/* renamed from: com.airbnb.jitney.event.logging.Payments.v1.PaymentsBillPriceQuoteFetchErrorEvent */
public final class PaymentsBillPriceQuoteFetchErrorEvent implements Struct {
    public static final Adapter<PaymentsBillPriceQuoteFetchErrorEvent, Object> ADAPTER = new PaymentsBillPriceQuoteFetchErrorEventAdapter();
    public final String billing_error_key;
    public final C1806BpqFetchReason bpq_fetch_reason;
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

    /* renamed from: com.airbnb.jitney.event.logging.Payments.v1.PaymentsBillPriceQuoteFetchErrorEvent$PaymentsBillPriceQuoteFetchErrorEventAdapter */
    private static final class PaymentsBillPriceQuoteFetchErrorEventAdapter implements Adapter<PaymentsBillPriceQuoteFetchErrorEvent, Object> {
        private PaymentsBillPriceQuoteFetchErrorEventAdapter() {
        }

        public void write(Protocol protocol, PaymentsBillPriceQuoteFetchErrorEvent struct) throws IOException {
            protocol.writeStructBegin("PaymentsBillPriceQuoteFetchErrorEvent");
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
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 4, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("payments_context", 5, PassportService.SF_DG12);
            C2543PaymentsContext.ADAPTER.write(protocol, struct.payments_context);
            protocol.writeFieldEnd();
            if (struct.reservation_code != null) {
                protocol.writeFieldBegin("reservation_code", 6, PassportService.SF_DG11);
                protocol.writeString(struct.reservation_code);
                protocol.writeFieldEnd();
            }
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
            protocol.writeFieldBegin("billing_error_key", 11, PassportService.SF_DG11);
            protocol.writeString(struct.billing_error_key);
            protocol.writeFieldEnd();
            if (struct.bpq_fetch_reason != null) {
                protocol.writeFieldBegin("bpq_fetch_reason", 12, 8);
                protocol.writeI32(struct.bpq_fetch_reason.value);
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
        if (!(other instanceof PaymentsBillPriceQuoteFetchErrorEvent)) {
            return false;
        }
        PaymentsBillPriceQuoteFetchErrorEvent that = (PaymentsBillPriceQuoteFetchErrorEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.payments_context == that.payments_context || this.payments_context.equals(that.payments_context)) && ((this.reservation_code == that.reservation_code || (this.reservation_code != null && this.reservation_code.equals(that.reservation_code))) && ((this.quickpay_config == that.quickpay_config || this.quickpay_config.equals(that.quickpay_config)) && ((this.quickpay_error_message == that.quickpay_error_message || this.quickpay_error_message.equals(that.quickpay_error_message)) && ((this.quickpay_error_code == that.quickpay_error_code || this.quickpay_error_code.equals(that.quickpay_error_code)) && ((this.quickpay_error_detail == that.quickpay_error_detail || this.quickpay_error_detail.equals(that.quickpay_error_detail)) && (this.billing_error_key == that.billing_error_key || this.billing_error_key.equals(that.billing_error_key))))))))))))) {
            if (this.bpq_fetch_reason == that.bpq_fetch_reason) {
                return true;
            }
            if (this.bpq_fetch_reason != null && this.bpq_fetch_reason.equals(that.bpq_fetch_reason)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int hashCode2;
        int i = 0;
        if (this.schema == null) {
            hashCode = 0;
        } else {
            hashCode = this.schema.hashCode();
        }
        int code = (((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.payments_context.hashCode()) * -2128831035;
        if (this.reservation_code == null) {
            hashCode2 = 0;
        } else {
            hashCode2 = this.reservation_code.hashCode();
        }
        int code2 = (((((((((((code ^ hashCode2) * -2128831035) ^ this.quickpay_config.hashCode()) * -2128831035) ^ this.quickpay_error_message.hashCode()) * -2128831035) ^ this.quickpay_error_code.hashCode()) * -2128831035) ^ this.quickpay_error_detail.hashCode()) * -2128831035) ^ this.billing_error_key.hashCode()) * -2128831035;
        if (this.bpq_fetch_reason != null) {
            i = this.bpq_fetch_reason.hashCode();
        }
        return (code2 ^ i) * -2128831035;
    }

    public String toString() {
        return "PaymentsBillPriceQuoteFetchErrorEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", operation=" + this.operation + ", payments_context=" + this.payments_context + ", reservation_code=" + this.reservation_code + ", quickpay_config=" + this.quickpay_config + ", quickpay_error_message=" + this.quickpay_error_message + ", quickpay_error_code=" + this.quickpay_error_code + ", quickpay_error_detail=" + this.quickpay_error_detail + ", billing_error_key=" + this.billing_error_key + ", bpq_fetch_reason=" + this.bpq_fetch_reason + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
