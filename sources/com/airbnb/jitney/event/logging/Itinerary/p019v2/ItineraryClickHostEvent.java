package com.airbnb.jitney.event.logging.Itinerary.p019v2;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.SchedulableInfo.p023v2.C0973SchedulableInfo;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Itinerary.v2.ItineraryClickHostEvent */
public final class ItineraryClickHostEvent implements Struct {
    public static final Adapter<ItineraryClickHostEvent, Object> ADAPTER = new ItineraryClickHostEventAdapter();
    public final Context context;
    public final String event_name;
    public final C2451Operation operation;
    public final String page;
    public final C0973SchedulableInfo parent_schedulable_info;
    public final C0973SchedulableInfo schedulable_info;
    public final String schema;
    public final Long user_id;

    /* renamed from: com.airbnb.jitney.event.logging.Itinerary.v2.ItineraryClickHostEvent$ItineraryClickHostEventAdapter */
    private static final class ItineraryClickHostEventAdapter implements Adapter<ItineraryClickHostEvent, Object> {
        private ItineraryClickHostEventAdapter() {
        }

        public void write(Protocol protocol, ItineraryClickHostEvent struct) throws IOException {
            protocol.writeStructBegin("ItineraryClickHostEvent");
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
            protocol.writeFieldBegin("user_id", 5, 10);
            protocol.writeI64(struct.user_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("parent_schedulable_info", 6, PassportService.SF_DG12);
            C0973SchedulableInfo.ADAPTER.write(protocol, struct.parent_schedulable_info);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("schedulable_info", 7, PassportService.SF_DG12);
            C0973SchedulableInfo.ADAPTER.write(protocol, struct.schedulable_info);
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
        if (!(other instanceof ItineraryClickHostEvent)) {
            return false;
        }
        ItineraryClickHostEvent that = (ItineraryClickHostEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.user_id == that.user_id || this.user_id.equals(that.user_id)) && ((this.parent_schedulable_info == that.parent_schedulable_info || this.parent_schedulable_info.equals(that.parent_schedulable_info)) && (this.schedulable_info == that.schedulable_info || this.schedulable_info.equals(that.schedulable_info))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.user_id.hashCode()) * -2128831035) ^ this.parent_schedulable_info.hashCode()) * -2128831035) ^ this.schedulable_info.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ItineraryClickHostEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", operation=" + this.operation + ", user_id=" + this.user_id + ", parent_schedulable_info=" + this.parent_schedulable_info + ", schedulable_info=" + this.schedulable_info + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
