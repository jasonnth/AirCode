package com.airbnb.jitney.event.logging.Itinerary.p019v2;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Itinerary.v2.ItineraryClickPendingActionButtonEvent */
public final class ItineraryClickPendingActionButtonEvent implements Struct {
    public static final Adapter<ItineraryClickPendingActionButtonEvent, Object> ADAPTER = new ItineraryClickPendingActionButtonEventAdapter();
    public final Context context;
    public final String event_name;
    public final Boolean needs_mt_tos;
    public final Boolean needs_verification;
    public final C2451Operation operation;
    public final String page;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.Itinerary.v2.ItineraryClickPendingActionButtonEvent$ItineraryClickPendingActionButtonEventAdapter */
    private static final class ItineraryClickPendingActionButtonEventAdapter implements Adapter<ItineraryClickPendingActionButtonEvent, Object> {
        private ItineraryClickPendingActionButtonEventAdapter() {
        }

        public void write(Protocol protocol, ItineraryClickPendingActionButtonEvent struct) throws IOException {
            protocol.writeStructBegin("ItineraryClickPendingActionButtonEvent");
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
            protocol.writeFieldBegin("needs_verification", 5, 2);
            protocol.writeBool(struct.needs_verification.booleanValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("needs_mt_tos", 6, 2);
            protocol.writeBool(struct.needs_mt_tos.booleanValue());
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
        if (!(other instanceof ItineraryClickPendingActionButtonEvent)) {
            return false;
        }
        ItineraryClickPendingActionButtonEvent that = (ItineraryClickPendingActionButtonEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.needs_verification == that.needs_verification || this.needs_verification.equals(that.needs_verification)) && (this.needs_mt_tos == that.needs_mt_tos || this.needs_mt_tos.equals(that.needs_mt_tos)))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.needs_verification.hashCode()) * -2128831035) ^ this.needs_mt_tos.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ItineraryClickPendingActionButtonEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", operation=" + this.operation + ", needs_verification=" + this.needs_verification + ", needs_mt_tos=" + this.needs_mt_tos + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
