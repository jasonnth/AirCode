package com.airbnb.jitney.event.logging.ReservationObject.p226v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.ReservationObject.v1.ReservationObjectPreapproveEvent */
public final class ReservationObjectPreapproveEvent implements Struct {
    public static final Adapter<ReservationObjectPreapproveEvent, Object> ADAPTER = new ReservationObjectPreapproveEventAdapter();
    public final Context context;
    public final String event_name;
    public final Long listing_id;
    public final String operation;
    public final String page;
    public final Long reservation_id;
    public final String schema;
    public final String target;

    /* renamed from: com.airbnb.jitney.event.logging.ReservationObject.v1.ReservationObjectPreapproveEvent$ReservationObjectPreapproveEventAdapter */
    private static final class ReservationObjectPreapproveEventAdapter implements Adapter<ReservationObjectPreapproveEvent, Object> {
        private ReservationObjectPreapproveEventAdapter() {
        }

        public void write(Protocol protocol, ReservationObjectPreapproveEvent struct) throws IOException {
            protocol.writeStructBegin("ReservationObjectPreapproveEvent");
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
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 5, PassportService.SF_DG11);
            protocol.writeString(struct.operation);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("listing_id", 6, 10);
            protocol.writeI64(struct.listing_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("reservation_id", 7, 10);
            protocol.writeI64(struct.reservation_id.longValue());
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
        if (!(other instanceof ReservationObjectPreapproveEvent)) {
            return false;
        }
        ReservationObjectPreapproveEvent that = (ReservationObjectPreapproveEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.listing_id == that.listing_id || this.listing_id.equals(that.listing_id)) && (this.reservation_id == that.reservation_id || this.reservation_id.equals(that.reservation_id))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.listing_id.hashCode()) * -2128831035) ^ this.reservation_id.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ReservationObjectPreapproveEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", target=" + this.target + ", operation=" + this.operation + ", listing_id=" + this.listing_id + ", reservation_id=" + this.reservation_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}