package com.airbnb.jitney.event.logging.Virality.p283v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.intents.ShareActivityIntents;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Virality.v1.ViralityReferralImpressionEvent */
public final class ViralityReferralImpressionEvent implements Struct {
    public static final Adapter<ViralityReferralImpressionEvent, Object> ADAPTER = new ViralityReferralImpressionEventAdapter();
    public final Context context;
    public final String entry_point;
    public final String event_name;
    public final C2451Operation operation;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.Virality.v1.ViralityReferralImpressionEvent$ViralityReferralImpressionEventAdapter */
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
            protocol.writeFieldBegin(ShareActivityIntents.ARG_ENTRY_POINT, 3, PassportService.SF_DG11);
            protocol.writeString(struct.entry_point);
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
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.entry_point == that.entry_point || this.entry_point.equals(that.entry_point)) && (this.operation == that.operation || this.operation.equals(that.operation)))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.entry_point.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ViralityReferralImpressionEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", entry_point=" + this.entry_point + ", operation=" + this.operation + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
