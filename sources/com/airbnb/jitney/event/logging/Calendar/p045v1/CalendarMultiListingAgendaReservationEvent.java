package com.airbnb.jitney.event.logging.Calendar.p045v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Calendar.v1.CalendarMultiListingAgendaReservationEvent */
public final class CalendarMultiListingAgendaReservationEvent implements Struct {
    public static final Adapter<CalendarMultiListingAgendaReservationEvent, Builder> ADAPTER = new CalendarMultiListingAgendaReservationEventAdapter();
    public final String confirmation_code;
    public final Context context;
    public final String event_name;
    public final Long listing_id;
    public final String operation;
    public final String page;
    public final String schema;
    public final String target;

    /* renamed from: com.airbnb.jitney.event.logging.Calendar.v1.CalendarMultiListingAgendaReservationEvent$Builder */
    public static final class Builder implements StructBuilder<CalendarMultiListingAgendaReservationEvent> {
        /* access modifiers changed from: private */
        public String confirmation_code;
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public Long listing_id;
        /* access modifiers changed from: private */
        public String operation;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public String target;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Calendar:CalendarMultiListingAgendaReservationEvent:1.0.0";
            this.event_name = "calendar_multi_listing_agenda_reservation";
            this.page = "calendar_multi_listing_agenda";
            this.target = "reservation";
            this.operation = "click";
        }

        public Builder(Context context2, Long listing_id2, String confirmation_code2) {
            this.schema = "com.airbnb.jitney.event.logging.Calendar:CalendarMultiListingAgendaReservationEvent:1.0.0";
            this.event_name = "calendar_multi_listing_agenda_reservation";
            this.context = context2;
            this.page = "calendar_multi_listing_agenda";
            this.target = "reservation";
            this.operation = "click";
            this.listing_id = listing_id2;
            this.confirmation_code = confirmation_code2;
        }

        public CalendarMultiListingAgendaReservationEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.page == null) {
                throw new IllegalStateException("Required field 'page' is missing");
            } else if (this.target == null) {
                throw new IllegalStateException("Required field 'target' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.listing_id == null) {
                throw new IllegalStateException("Required field 'listing_id' is missing");
            } else if (this.confirmation_code != null) {
                return new CalendarMultiListingAgendaReservationEvent(this);
            } else {
                throw new IllegalStateException("Required field 'confirmation_code' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Calendar.v1.CalendarMultiListingAgendaReservationEvent$CalendarMultiListingAgendaReservationEventAdapter */
    private static final class CalendarMultiListingAgendaReservationEventAdapter implements Adapter<CalendarMultiListingAgendaReservationEvent, Builder> {
        private CalendarMultiListingAgendaReservationEventAdapter() {
        }

        public void write(Protocol protocol, CalendarMultiListingAgendaReservationEvent struct) throws IOException {
            protocol.writeStructBegin("CalendarMultiListingAgendaReservationEvent");
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
            protocol.writeFieldBegin("confirmation_code", 7, PassportService.SF_DG11);
            protocol.writeString(struct.confirmation_code);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private CalendarMultiListingAgendaReservationEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.target = builder.target;
        this.operation = builder.operation;
        this.listing_id = builder.listing_id;
        this.confirmation_code = builder.confirmation_code;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof CalendarMultiListingAgendaReservationEvent)) {
            return false;
        }
        CalendarMultiListingAgendaReservationEvent that = (CalendarMultiListingAgendaReservationEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.listing_id == that.listing_id || this.listing_id.equals(that.listing_id)) && (this.confirmation_code == that.confirmation_code || this.confirmation_code.equals(that.confirmation_code))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.listing_id.hashCode()) * -2128831035) ^ this.confirmation_code.hashCode()) * -2128831035;
    }

    public String toString() {
        return "CalendarMultiListingAgendaReservationEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", target=" + this.target + ", operation=" + this.operation + ", listing_id=" + this.listing_id + ", confirmation_code=" + this.confirmation_code + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
