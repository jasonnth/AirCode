package com.airbnb.jitney.event.logging.Itinerary.p018v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.SchedulableInfo.p023v2.C0973SchedulableInfo;
import com.airbnb.jitney.event.logging.SchedulablePlaceType.p245v1.C2718SchedulablePlaceType;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Itinerary.v1.ItineraryClickCancelReservationEvent */
public final class ItineraryClickCancelReservationEvent implements Struct {
    public static final Adapter<ItineraryClickCancelReservationEvent, Object> ADAPTER = new ItineraryClickCancelReservationEventAdapter();
    public final Context context;
    public final String event_name;
    public final C2451Operation operation;
    public final String page;
    public final C0973SchedulableInfo parent_schedulable_info;
    public final C0973SchedulableInfo schedulable_info;
    public final C2718SchedulablePlaceType schedulable_place_type;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.Itinerary.v1.ItineraryClickCancelReservationEvent$ItineraryClickCancelReservationEventAdapter */
    private static final class ItineraryClickCancelReservationEventAdapter implements Adapter<ItineraryClickCancelReservationEvent, Object> {
        private ItineraryClickCancelReservationEventAdapter() {
        }

        public void write(Protocol protocol, ItineraryClickCancelReservationEvent struct) throws IOException {
            protocol.writeStructBegin("ItineraryClickCancelReservationEvent");
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
            protocol.writeFieldBegin("schedulable_place_type", 5, 8);
            protocol.writeI32(struct.schedulable_place_type.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("schedulable_info", 6, PassportService.SF_DG12);
            C0973SchedulableInfo.ADAPTER.write(protocol, struct.schedulable_info);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("parent_schedulable_info", 7, PassportService.SF_DG12);
            C0973SchedulableInfo.ADAPTER.write(protocol, struct.parent_schedulable_info);
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
        if (!(other instanceof ItineraryClickCancelReservationEvent)) {
            return false;
        }
        ItineraryClickCancelReservationEvent that = (ItineraryClickCancelReservationEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.schedulable_place_type == that.schedulable_place_type || this.schedulable_place_type.equals(that.schedulable_place_type)) && ((this.schedulable_info == that.schedulable_info || this.schedulable_info.equals(that.schedulable_info)) && (this.parent_schedulable_info == that.parent_schedulable_info || this.parent_schedulable_info.equals(that.parent_schedulable_info))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.schedulable_place_type.hashCode()) * -2128831035) ^ this.schedulable_info.hashCode()) * -2128831035) ^ this.parent_schedulable_info.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ItineraryClickCancelReservationEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", operation=" + this.operation + ", schedulable_place_type=" + this.schedulable_place_type + ", schedulable_info=" + this.schedulable_info + ", parent_schedulable_info=" + this.parent_schedulable_info + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
