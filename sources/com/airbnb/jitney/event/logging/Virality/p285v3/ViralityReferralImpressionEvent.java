package com.airbnb.jitney.event.logging.Virality.p285v3;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.ViralityEntryPoint.p287v2.C2804ViralityEntryPoint;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Virality.v3.ViralityReferralImpressionEvent */
public final class ViralityReferralImpressionEvent implements Struct {
    public static final Adapter<ViralityReferralImpressionEvent, Object> ADAPTER = new ViralityReferralImpressionEventAdapter();
    public final Context context;
    public final String event_name;
    public final C2451Operation operation;
    public final String schema;
    public final C2804ViralityEntryPoint virality_entry_point;

    /* renamed from: com.airbnb.jitney.event.logging.Virality.v3.ViralityReferralImpressionEvent$ViralityReferralImpressionEventAdapter */
    private static final class ViralityReferralImpressionEventAdapter implements Adapter<ViralityReferralImpressionEvent, Object> {
        private ViralityReferralImpressionEventAdapter() {
        }

        public void write(Protocol protocol, ViralityReferralImpressionEvent struct) throws IOException {
            protocol.writeStructBegin("ViralityReferralImpressionEvent");
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
            protocol.writeFieldBegin("virality_entry_point", 3, 8);
            protocol.writeI32(struct.virality_entry_point.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 4, 8);
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
        if (!(other instanceof ViralityReferralImpressionEvent)) {
            return false;
        }
        ViralityReferralImpressionEvent that = (ViralityReferralImpressionEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.virality_entry_point == that.virality_entry_point || this.virality_entry_point.equals(that.virality_entry_point)) && (this.operation == that.operation || this.operation.equals(that.operation)))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.virality_entry_point.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ViralityReferralImpressionEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", virality_entry_point=" + this.virality_entry_point + ", operation=" + this.operation + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
