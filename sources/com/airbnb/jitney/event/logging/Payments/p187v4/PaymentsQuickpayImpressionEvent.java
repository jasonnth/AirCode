package com.airbnb.jitney.event.logging.Payments.p187v4;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.airbnb.android.lib.cancellation.CancellationAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.PaymentsContext.p189v1.C2543PaymentsContext;
import com.airbnb.jitney.event.logging.QuickpayConfig.p216v1.C2597QuickpayConfig;
import com.airbnb.jitney.event.logging.SchedulableInfo.p023v2.C0973SchedulableInfo;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.List;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Payments.v4.PaymentsQuickpayImpressionEvent */
public final class PaymentsQuickpayImpressionEvent implements Struct {
    public static final Adapter<PaymentsQuickpayImpressionEvent, Object> ADAPTER = new PaymentsQuickpayImpressionEventAdapter();
    public final Context context;
    public final List<String> dates;
    public final String event_name;
    public final Long guests;
    public final C2451Operation operation;
    public final String page;
    public final C2543PaymentsContext payments_context;
    public final C2597QuickpayConfig quickpay_config;
    public final C0973SchedulableInfo schedulable_info;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.Payments.v4.PaymentsQuickpayImpressionEvent$PaymentsQuickpayImpressionEventAdapter */
    private static final class PaymentsQuickpayImpressionEventAdapter implements Adapter<PaymentsQuickpayImpressionEvent, Object> {
        private PaymentsQuickpayImpressionEventAdapter() {
        }

        public void write(Protocol protocol, PaymentsQuickpayImpressionEvent struct) throws IOException {
            protocol.writeStructBegin("PaymentsQuickpayImpressionEvent");
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
            if (struct.guests != null) {
                protocol.writeFieldBegin(FindTweenAnalytics.GUESTS, 6, 10);
                protocol.writeI64(struct.guests.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.schedulable_info != null) {
                protocol.writeFieldBegin("schedulable_info", 7, PassportService.SF_DG12);
                C0973SchedulableInfo.ADAPTER.write(protocol, struct.schedulable_info);
                protocol.writeFieldEnd();
            }
            if (struct.dates != null) {
                protocol.writeFieldBegin(CancellationAnalytics.VALUE_PAGE_DATES, 8, 15);
                protocol.writeListBegin(PassportService.SF_DG11, struct.dates.size());
                for (String item0 : struct.dates) {
                    protocol.writeString(item0);
                }
                protocol.writeListEnd();
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin("quickpay_config", 9, 8);
            protocol.writeI32(struct.quickpay_config.value);
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
        if (!(other instanceof PaymentsQuickpayImpressionEvent)) {
            return false;
        }
        PaymentsQuickpayImpressionEvent that = (PaymentsQuickpayImpressionEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.payments_context == that.payments_context || this.payments_context.equals(that.payments_context)) && ((this.guests == that.guests || (this.guests != null && this.guests.equals(that.guests))) && ((this.schedulable_info == that.schedulable_info || (this.schedulable_info != null && this.schedulable_info.equals(that.schedulable_info))) && ((this.dates == that.dates || (this.dates != null && this.dates.equals(that.dates))) && (this.quickpay_config == that.quickpay_config || this.quickpay_config.equals(that.quickpay_config))))))))))) {
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
        int code = (((((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.payments_context.hashCode()) * -2128831035) ^ (this.guests == null ? 0 : this.guests.hashCode())) * -2128831035) ^ (this.schedulable_info == null ? 0 : this.schedulable_info.hashCode())) * -2128831035;
        if (this.dates != null) {
            i = this.dates.hashCode();
        }
        return (((code ^ i) * -2128831035) ^ this.quickpay_config.hashCode()) * -2128831035;
    }

    public String toString() {
        return "PaymentsQuickpayImpressionEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", operation=" + this.operation + ", payments_context=" + this.payments_context + ", guests=" + this.guests + ", schedulable_info=" + this.schedulable_info + ", dates=" + this.dates + ", quickpay_config=" + this.quickpay_config + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
