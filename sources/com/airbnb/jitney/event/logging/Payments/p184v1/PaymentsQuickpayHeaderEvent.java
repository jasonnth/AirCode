package com.airbnb.jitney.event.logging.Payments.p184v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.QuickpayConfig.p216v1.C2597QuickpayConfig;
import com.airbnb.jitney.event.logging.SchedulableInfo.p023v2.C0973SchedulableInfo;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Payments.v1.PaymentsQuickpayHeaderEvent */
public final class PaymentsQuickpayHeaderEvent implements Struct {
    public static final Adapter<PaymentsQuickpayHeaderEvent, Object> ADAPTER = new PaymentsQuickpayHeaderEventAdapter();
    public final Context context;
    public final String event_name;
    public final C2451Operation operation;
    public final String page;
    public final C2597QuickpayConfig quickpay_config;
    public final C0973SchedulableInfo schedulable_info;
    public final String schema;
    public final String target;

    /* renamed from: com.airbnb.jitney.event.logging.Payments.v1.PaymentsQuickpayHeaderEvent$PaymentsQuickpayHeaderEventAdapter */
    private static final class PaymentsQuickpayHeaderEventAdapter implements Adapter<PaymentsQuickpayHeaderEvent, Object> {
        private PaymentsQuickpayHeaderEventAdapter() {
        }

        public void write(Protocol protocol, PaymentsQuickpayHeaderEvent struct) throws IOException {
            protocol.writeStructBegin("PaymentsQuickpayHeaderEvent");
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
            protocol.writeFieldBegin("quickpay_config", 6, 8);
            protocol.writeI32(struct.quickpay_config.value);
            protocol.writeFieldEnd();
            if (struct.schedulable_info != null) {
                protocol.writeFieldBegin("schedulable_info", 7, PassportService.SF_DG12);
                C0973SchedulableInfo.ADAPTER.write(protocol, struct.schedulable_info);
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
        if (!(other instanceof PaymentsQuickpayHeaderEvent)) {
            return false;
        }
        PaymentsQuickpayHeaderEvent that = (PaymentsQuickpayHeaderEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && (this.quickpay_config == that.quickpay_config || this.quickpay_config.equals(that.quickpay_config)))))))) {
            if (this.schedulable_info == that.schedulable_info) {
                return true;
            }
            if (this.schedulable_info != null && this.schedulable_info.equals(that.schedulable_info)) {
                return true;
            }
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
        int code = (((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.quickpay_config.hashCode()) * -2128831035;
        if (this.schedulable_info != null) {
            i = this.schedulable_info.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "PaymentsQuickpayHeaderEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", target=" + this.target + ", operation=" + this.operation + ", quickpay_config=" + this.quickpay_config + ", schedulable_info=" + this.schedulable_info + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
