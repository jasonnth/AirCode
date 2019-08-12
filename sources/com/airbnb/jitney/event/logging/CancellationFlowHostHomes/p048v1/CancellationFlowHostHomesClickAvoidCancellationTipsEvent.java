package com.airbnb.jitney.event.logging.CancellationFlowHostHomes.p048v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.CancellationFlowHostAvoidTipType.p047v1.C1886CancellationFlowHostAvoidTipType;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.CancellationFlowHostHomes.v1.CancellationFlowHostHomesClickAvoidCancellationTipsEvent */
public final class CancellationFlowHostHomesClickAvoidCancellationTipsEvent implements Struct {
    public static final Adapter<CancellationFlowHostHomesClickAvoidCancellationTipsEvent, Object> ADAPTER = new CancellationFlowHostHomesClickAvoidCancellationTipsEventAdapter();
    public final C1886CancellationFlowHostAvoidTipType cancellation_flow_host_avoid_tip_type;
    public final Context context;
    public final String event_name;
    public final Long listing_id;
    public final C2451Operation operation;
    public final Long reservation_id;
    public final String schema;
    public final Long user_id;

    /* renamed from: com.airbnb.jitney.event.logging.CancellationFlowHostHomes.v1.CancellationFlowHostHomesClickAvoidCancellationTipsEvent$CancellationFlowHostHomesClickAvoidCancellationTipsEventAdapter */
    private static final class CancellationFlowHostHomesClickAvoidCancellationTipsEventAdapter implements Adapter<CancellationFlowHostHomesClickAvoidCancellationTipsEvent, Object> {
        private CancellationFlowHostHomesClickAvoidCancellationTipsEventAdapter() {
        }

        public void write(Protocol protocol, CancellationFlowHostHomesClickAvoidCancellationTipsEvent struct) throws IOException {
            protocol.writeStructBegin("CancellationFlowHostHomesClickAvoidCancellationTipsEvent");
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
            protocol.writeFieldBegin("listing_id", 3, 10);
            protocol.writeI64(struct.listing_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("user_id", 4, 10);
            protocol.writeI64(struct.user_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("reservation_id", 5, 10);
            protocol.writeI64(struct.reservation_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("cancellation_flow_host_avoid_tip_type", 6, 8);
            protocol.writeI32(struct.cancellation_flow_host_avoid_tip_type.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 7, 8);
            protocol.writeI32(struct.operation.value);
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
        if (!(other instanceof CancellationFlowHostHomesClickAvoidCancellationTipsEvent)) {
            return false;
        }
        CancellationFlowHostHomesClickAvoidCancellationTipsEvent that = (CancellationFlowHostHomesClickAvoidCancellationTipsEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.listing_id == that.listing_id || this.listing_id.equals(that.listing_id)) && ((this.user_id == that.user_id || this.user_id.equals(that.user_id)) && ((this.reservation_id == that.reservation_id || this.reservation_id.equals(that.reservation_id)) && ((this.cancellation_flow_host_avoid_tip_type == that.cancellation_flow_host_avoid_tip_type || this.cancellation_flow_host_avoid_tip_type.equals(that.cancellation_flow_host_avoid_tip_type)) && (this.operation == that.operation || this.operation.equals(that.operation))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.listing_id.hashCode()) * -2128831035) ^ this.user_id.hashCode()) * -2128831035) ^ this.reservation_id.hashCode()) * -2128831035) ^ this.cancellation_flow_host_avoid_tip_type.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035;
    }

    public String toString() {
        return "CancellationFlowHostHomesClickAvoidCancellationTipsEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", listing_id=" + this.listing_id + ", user_id=" + this.user_id + ", reservation_id=" + this.reservation_id + ", cancellation_flow_host_avoid_tip_type=" + this.cancellation_flow_host_avoid_tip_type + ", operation=" + this.operation + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
